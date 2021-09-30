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

        if(!taxiCallReceived.validate()) return;

        System.out.println("\n\n##### listener RequestMemberInfo : " + taxiCallReceived.toJson() + "\n\n");



        // Sample Logic //
        // MemberShip memberShip = new MemberShip();
        // memberShipRepository.save(memberShip);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}