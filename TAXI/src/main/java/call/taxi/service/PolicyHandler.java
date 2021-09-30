package call.taxi.service;

import call.taxi.service.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired TaxiRepository taxiRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiCalled_TaxiCall(@Payload TaxiCalled taxiCalled)
    {
        System.out.println(this.getClass().getName()+" : wheneverTaxiCalled_TaxiCall   -----------------start------------------");

        //if(taxiCalled.isMe)
        //{
            if(!taxiCalled.validate()) return;
    
            System.out.println("\n\n##### listener TaxiCall : " + taxiCalled.toJson() + "\n\n");



            // Sample Logic //
            // Taxi taxi = new Taxi();
            // taxiRepository.save(taxi);
        //}
        System.out.println(this.getClass().getName()+" : wheneverTaxiCalled_TaxiCall   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiCallCanceled_CallCancel(@Payload TaxiCallCanceled taxiCallCanceled){

        System.out.println(this.getClass().getName()+" : wheneverTaxiCallCanceled_CallCancel   -----------------start------------------");
        //if(taxiCallCanceled.isMe())
        //{
            if(!taxiCallCanceled.validate()) return;

            System.out.println("\n\n##### listener CallCancel : " + taxiCallCanceled.toJson() + "\n\n");



            // Sample Logic //
            // Taxi taxi = new Taxi();
            // taxiRepository.save(taxi);
        //}
        System.out.println(this.getClass().getName()+" : wheneverTaxiCallCanceled_CallCancel   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverServiceEstimated_ServiceEstimate(@Payload ServiceEstimated serviceEstimated){
        System.out.println(this.getClass().getName()+" : wheneverServiceEstimated_ServiceEstimate   -----------------start------------------");
        //if(serviceEstimated.isMe())
        //{
            if(!serviceEstimated.validate()) return;

            System.out.println("\n\n##### listener ServiceEstimate : " + serviceEstimated.toJson() + "\n\n");



            // Sample Logic //
            // Taxi taxi = new Taxi();
            // taxiRepository.save(taxi);
        //}
        System.out.println(this.getClass().getName()+" : wheneverServiceEstimated_ServiceEstimate   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRequestMemberInfoArrived_AddMemberInfo(@Payload RequestMemberInfoArrived requestMemberInfoArrived){
        System.out.println(this.getClass().getName()+" : wheneverRequestMemberInfoArrived_AddMemberInfo   -----------------start------------------");

        //if(requestMemberInfoArrived.isMe())
        //{
            if(!requestMemberInfoArrived.validate()) return;

            System.out.println("\n\n##### listener AddMemberInfo : " + requestMemberInfoArrived.toJson() + "\n\n");



            // Sample Logic //
            // Taxi taxi = new Taxi();
            // taxiRepository.save(taxi);
       // }
        System.out.println(this.getClass().getName()+" : wheneverRequestMemberInfoArrived_AddMemberInfo   -----------------end------------------");
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}