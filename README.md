# **<u>AnycallTaxi</u>**

본 과제는 MSA/DDD/Event Storming/EDA 를 포괄하는 분석/설계/구현/운영 전체를 구성한 과제입니다. 이는 Cloud Native Application의 개발에 요구되는 체크포인트를 충족함을 수행결과 증적을 통해 제시합니다.



# 서비스 시나리오



언제 어디서나 애니콜택시 호출 시스템

##### 기능적 요구사항

0. 애니콜택시 회원가입을 하고 자동결제를 위한 신용카드와 안심서비스를 위한 친구 전화번호를 등록한다.
1. 현재위치와 목적지를 입력하고 택시를 호출한다.
2. 주변의 택시는 호출정보를 보고 배차를 신청한다.
3. 배정이 되지 않는 상태에서는 고객이 호출을 취소할 수 있다.
4. 배차현황을 고객에게 알린다, 배차가 안될경우 주기적으로 배차상황을 알린다
5. 택시정보와 위치를 실시간으로 고객에게 알린다
6. 택시가 고객을 태우고 출발하면서 '운행시작'을 시스템에 알린다
7. 고객이 미리 등록한 친구에게 택시운행 정보를 제공한다(안심서비스)
8. 목적지에 도착하고 고객이 하차하면 '운행종료'를 시스템에 알린다.
9. 운행이 종료되면 미리 등록된 신용카드로 자동결제
10. 고객은 택시이용후 후기를 작성하여 서비스를 평가한다
11. 고객의 택시이용 패턴을 수집하여 택시이용에 관련한 정보를 제공하고 이벤트에 활용한다(할인행사등)



##### 비기능적요구사항

1. 트랜잭션
   1. 결제가 완료 되어야만 서비스를 종료할 수 있음 Sync. 호출
2. 장애격리
   1. 택시호출이 과중되면 호출을 잠시동안 받지 않고 잠시 후에 하도록 유도한다 Circuit breaker
3. 성능
   1. 고객은 택시호출/배정부터 서비스종료후 서비스평가까지 서비스의 전단계에 대한 정보를 확인할 수 있어야 한다.(CQRS)
   2. 택시서비스의 시작과 종료등 택시정보 및 운행정보를 등록된 지인에게 SMS로 알림(안심서비스) Event driven



# 체크포인트



- 체크포인트 : https://workflowy.com/s/assessment-check-po/T5YrzcMewfo4J6LW

- 평가항목

  - Saga

  - CQRS

  - Correlation

  - Req/Resp

  - Gateway

  - Deploy/Pipeline

  - Circuit Breaker

  - Autoscale(HPA)

  - Zero-downtime deploy (Readness Probe)

  - Config Map / Persistence Volume

  - Ployglot

  - Self-healing (Liveness Probe)

    

# 분석/설계

Event Storming 결과 : https://www.msaez.io/#/storming/Qjy9fDJTqoQmlkaS7SAQzrJd3Wt1/4ff6d1aeaa2917ab0a1ec45a52944b60

![CallTaxiService_modeling_20210926](https://user-images.githubusercontent.com/90515096/135374296-6d20f5de-b47c-407e-898c-85e3a2c46302.png)





# 구현

분석/설계단계에서 도출된 헥사고날 아키텍처에 따라, 각 BC별로 대변되는 마이크로서비스들을 스프링부트로 구현한다.

모든 시스템은 spring-boot로 구현하였고 mvn spring-boot:run 명령어로 실행한다.



# DDD 적용

각 서비스내에 도출된 핵심 Aggregate Root 객체를 Entity로 선언하고, 가능한 이해하기 쉬운 영어단어를 사용하였다.

MemberShip.java

![MEMBERSHIP](https://user-images.githubusercontent.com/90515096/135395599-0bc4a2da-5635-48f6-a883-9db85ded245c.png)



CALL.java

![CALL](https://user-images.githubusercontent.com/90515096/135389764-2cb7a251-b297-418a-aadf-e5df999a33f2.png)



Notify.java



Payment.java



Taxi.java









# Saga

- Taxi서비스를 중단한 상태에서 여러개의 택시호출을 처리후, Taxi서비스를 start하였을때, 택시호출을 Taxi에서 처리하는 것을 확인한다.

- Taxi 서비스를 중단한 상태에서 연속된 택시호출 서비스를 호출하고, 각 서비스의 데이터를 확인한다.

- Taxi서비스 중단

  ![Saga0](https://user-images.githubusercontent.com/90515096/135396601-3f09e350-2876-4a36-94f7-d1e3fd2643fa.png)

- http POST http://localhost:8081/calls memberId=1 phoneNumber="010-7202-2664" startAddress="경기도 분당구 정자동" endAddress="서울시 양천구 목동서로 400" callStatus="택시호출"

- 2회실행후 결과확인

- http get http://localhost:8081/calls

  ![Saga1](https://user-images.githubusercontent.com/90515096/135396277-a1fe10c1-8664-4676-bee5-2a623e623e70.png)

  

- Taxi서비스를 실행후 확인 : 호출내역 2건을 확인할 수 있다

- http get http://localhost:8082/taxis

  ![Saga2](https://user-images.githubusercontent.com/90515096/135399981-4076247c-9cc8-41eb-9d24-dee16495b9a2.png)









# CQRS

- TAXI 호출/배정 및 운행정보를 조회할 수 있도록 CQRS로 구현

- CALL,Taxi,Payment의 상태를 통합해서 조회하기 때문에 타 서비스들의 성능저하 문제를 해결할 수 있다.

- TaxiServiceInfoViewHandler.java

  ![CQRS](https://user-images.githubusercontent.com/90515096/135402345-057b7508-b060-4078-a888-a21273adaee5.png)



# Correlation

- 호출후 호출을 취소시 CALL, Taxi의 상태를 확인한다.

- 택시호출

- http POST http://localhost:8081/calls memberId=1 phoneNumber="010-7202-2664" startAddress="경기도 분당구 정자동" endAddress="서울시 양천구 목동서로 400" callStatus="택시호출"

- CALL서비스 확인 : http get http://localhost:8081/calls/1

  ![corell1](https://user-images.githubusercontent.com/90515096/135405464-afd2f765-70f1-42e9-9cf9-0b800e4655a5.png)

- Taxi서비스 확인 : http get http://localhost:8082/taxis/1

  ![corell2](https://user-images.githubusercontent.com/90515096/135405720-e83735b1-4473-407c-a98f-8bd0067f2c64.png)

  

- 택시호출 취소 : http PATCH http://localhost:8081/calls/1 callStatus="호출취소"

- CALL서비스 확인 : http get http://localhost:8081/calls/1

  ![corell3](https://user-images.githubusercontent.com/90515096/135406274-586f5cda-ddb5-4c25-9c5f-25963e4532e5.png)

- Taxi서비스 확인 : http get http://localhost:8082/taxis/1

![corell4](https://user-images.githubusercontent.com/90515096/135406434-f328c50f-db0d-4d1b-a8f7-fdb9fb0117c1.png)





# Req / resp

- 분석단계의 중요한 요건으로 서비스종료->결제간의 호출은 동기호출로 처리하였다. 호출 프로토콜은 FeignClient를 이용하여 호출하도록 한다.

- 결제서비스를 호출하기 위하여 FeignClient를 이용하여 Service대행 인터페이스 구현

  ![reqres0](https://user-images.githubusercontent.com/90515096/135479919-9b194913-7525-466b-9974-19d9809969b4.png)

- 서비스종료 직후 결제를 요청

  ![reqres2](https://user-images.githubusercontent.com/90515096/135412995-d7a042fb-a439-454f-b8b0-9f0ad9045089.png)

  

- 동기호출에서는 결제시스템이 장애가 나면 주문을 받지 못함을 확인.

  ![reqres3](https://user-images.githubusercontent.com/90515096/135415178-aceb60ad-f9e3-4783-8c45-ce91fce62859.png)

  

# 비동기식 호출

- kafka를 이용하여 택시호출(CALL)에서 택시(Taxi)로의 연동을 비동기식으로 구현

- CALL은 호출이 들어오면 카프카로 호출이 들어왔음을 알린다

  ![비동기1](https://user-images.githubusercontent.com/90515096/135415567-93ec335e-2a74-4abb-aaab-36d21a12a5ab.png)

  

- Taxi는 호출 이벤트에 대해서 수신하여 처리하도록 PolicyHandler를 구현

  ![비동기2](https://user-images.githubusercontent.com/90515096/135415842-1c197084-db1a-48c6-9f65-3b8b15ec255c.png)

  

- Taxi는 CALL과 분리되어 있으며, 이벤트 수신에 따라 처리되기 때문에, Taxi가 유지보수로 인해 잠시 내려간 상태라도 호출을 받는데 문제가 없다.

- #Taxi를 정지시킴

- #호출처리

  http POST http://localhost:8081/calls memberId=1 phoneNumber="010-7202-2664" startAddress="경기도 분당구 정자동" endAddress="서울시 양천구 목동서로 400" callStatus="택시호출"

  ![corell1](https://user-images.githubusercontent.com/90515096/135405464-afd2f765-70f1-42e9-9cf9-0b800e4655a5.png)

  #Success

  #호출상태확인 : 택시호출

  #CALL서비스 가동

  #Taxi 배정처리

  http PATCH http://localhost:8082/taxis/1 taxiNumber="114너7484" taxiInfo="서울택시(주) 기사명 : 홍길동 종류 : 일반택시" callStatus="배정됨" taxiStatus="배정됨"

  #상태확인

  ![비동기3](https://user-images.githubusercontent.com/90515096/135430227-d5269352-4ab5-461f-8ada-5c514f3c0972.png)

  

  # Gateway

  - Local테스트 환경에서는 localhost:8088에서 Gateway API 작동

  - application.yml 파일에 프로파일 별로 Gateway 설정

    ![gateway1](https://user-images.githubusercontent.com/90515096/135430576-4d7237cc-bb14-4820-b541-2cf529963406.png)

  - GateWay서비스 기동후, Gateway서비스의 port 8088로 CALL,Taxi,Notify 서비스가 접속 가능한지 확인

    http get http://localhost:8088/calls/1

    ![gateway2](https://user-images.githubusercontent.com/90515096/135430878-56547ebf-47be-40d6-91a4-be17c1117f24.png)

    

​              http get http://localhost:8088/taxis/1

​              ![gateway3](https://user-images.githubusercontent.com/90515096/135431222-5ea133e3-2965-495f-a730-eca9687e57a1.png)



# CI / CD 설정

- kafka를 이용하여 택시호출(CALL)에서 택시(Taxi)로의 연동을 비동기식으로 구현
- 

# 서킷브레이킹

- 서킷 브레이킹 프레임워크의 선택: Spring FeignClient + Hystrix 옵션을 사용하여 구현함
- 시나리오는 택시(Taxi)-->결제(payment) 호출 시의 연결을 RESTful Request/Response 로 연동하여 구현이 되어있고, 결제 요청이 과도할 경우 CB 를 통하여 장애격리

- Hystrix 를 설정: 요청처리 쓰레드에서 처리시간이 610 밀리가 넘어서기 시작하여 어느정도 유지되면 CB 회로가 닫히도록 (요청을 빠르게 실패처리, 차단) 설정

- application.yml

  ![서킷브레이커2](https://user-images.githubusercontent.com/90515096/135510972-8c8c21da-7b70-47c7-b48d-85d1d0dd1fa1.png)

Hystrix 를 설정: 요청처리 쓰레드에서 처리시간이 610 밀리가 넘어서기 시작하여 어느정도 유지되면 CB 회로가 닫히도록 (요청을 빠르게 실패처리, 차단) 설정

![서킷브레이커3](https://user-images.githubusercontent.com/90515096/135511277-ed36a66b-7650-4602-994b-4d839c683f2a.png)

- 부하테스터 siege 툴을 통한 서킷 브레이커 동작 확인: siege -v -c200 -t20s --content-type "application/json" 'http://localhost:8082/taxis POST {"memberId":"1", "callStatus":"택시호출"}'

- 동시사용자 200명
- 20초 동안 실시

![서킷브레이커0](https://user-images.githubusercontent.com/90515096/135511530-5780facf-9f1e-4d46-8c65-862553ca1c08.png)



Taxi서비스의 로그를 확인하여 Circuit이 Open된 것을 확인함.

![서킷브레이커1](https://user-images.githubusercontent.com/90515096/135511474-f3934a4b-991c-45b5-afc0-8654764d561b.png)



# 오토스케일 아웃

- 





# 무정지 재배포

- 











