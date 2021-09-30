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

