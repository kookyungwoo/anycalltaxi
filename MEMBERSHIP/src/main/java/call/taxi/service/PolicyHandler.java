package call.taxi.service;

import call.taxi.service.config.kafka.KafkaProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired MemberShipRepository memberShipRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiCallReceived_RequestMemberInfo(@Payload TaxiCallReceived taxiCallReceived){

        System.out.println(this.getClass().getName()+" : wheneverTaxiCallReceived_RequestMemberInfo   -----------------start------------------");
        if(!taxiCallReceived.validate()) return;

        System.out.println("\n\n##### listener RequestMemberInfo : " + taxiCallReceived.toJson() + "\n\n");

        java.util.Optional<MemberShip> optionalMemberShip = memberShipRepository.findById(taxiCallReceived.getCallId());
        MemberShip memberShip = optionalMemberShip.get();
        //memberShip.setEstimateScore(taxiCallReceived.getEstimateScore());
        memberShip.setStatus("고객정보요청도착");
        memberShipRepository.save(memberShip);

        // Sample Logic //
        // MemberShip memberShip = new MemberShip();
        // memberShipRepository.save(memberShip);

        System.out.println(this.getClass().getName()+" : wheneverTaxiCallReceived_RequestMemberInfo   -----------------end------------------");
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}