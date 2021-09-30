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
            if(!taxiCalled.validate()) return;
    
            System.out.println("\n\n##### listener TaxiCall : " + taxiCalled.toJson() + "\n\n");

             Taxi taxi = new Taxi();
             taxi.setCallId(taxiCalled.getCallId());
             taxi.setMemberId(taxiCalled.getMemberId());
             taxi.setStartAddress(taxiCalled.getStartAddress());
             taxi.setEndAddress(taxiCalled.getEndAddress());
             taxi.setCallStatus("택시호출");
             taxiRepository.save(taxi);
        System.out.println(this.getClass().getName()+" : wheneverTaxiCalled_TaxiCall   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiCallCanceled_CallCancel(@Payload TaxiCallCanceled taxiCallCanceled){

        System.out.println(this.getClass().getName()+" : wheneverTaxiCallCanceled_CallCancel   -----------------start------------------");
            
            if(!taxiCallCanceled.validate()) return;

            System.out.println("\n\n##### listener CallCancel : " + taxiCallCanceled.toJson() + "\n\n");

            //taxiRepository.findById(taxiCallCanceled.getCallId()).ifPresent(Taxi->{taxiRepository.save(Taxi);});
            
            java.util.Optional<Taxi> optionalOrder = taxiRepository.findById(taxiCallCanceled.getCallId());
            Taxi taxi = optionalOrder.get();
            //order.setDeliveryId(String.valueOf(deleveryStarted.getId()));
            taxi.setCallStatus("택시호출취소");
            taxiRepository.save(taxi);

        System.out.println(this.getClass().getName()+" : wheneverTaxiCallCanceled_CallCancel   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverServiceEstimated_ServiceEstimate(@Payload ServiceEstimated serviceEstimated){
        System.out.println(this.getClass().getName()+" : wheneverServiceEstimated_ServiceEstimate   -----------------start------------------");

            if(!serviceEstimated.validate()) return;

            System.out.println("\n\n##### listener ServiceEstimate : " + serviceEstimated.toJson() + "\n\n");

            java.util.Optional<Taxi> optionalOrder = taxiRepository.findById(serviceEstimated.getCallId());
            Taxi taxi = optionalOrder.get();
            taxi.setEstimateScore(serviceEstimated.getEstimateScore());
            taxiRepository.save(taxi);


        System.out.println(this.getClass().getName()+" : wheneverServiceEstimated_ServiceEstimate   -----------------end------------------");
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRequestMemberInfoArrived_AddMemberInfo(@Payload RequestMemberInfoArrived requestMemberInfoArrived){
        System.out.println(this.getClass().getName()+" : wheneverRequestMemberInfoArrived_AddMemberInfo   -----------------start------------------");

            if(!requestMemberInfoArrived.validate()) return;

            System.out.println("\n\n##### listener AddMemberInfo : " + requestMemberInfoArrived.toJson() + "\n\n");

            java.util.Optional<Taxi> optionalOrder = taxiRepository.findById(requestMemberInfoArrived.getMemberId());
            Taxi taxi = optionalOrder.get();
            taxi.setMemberName(requestMemberInfoArrived.getMemberName());
            taxi.setPhoneNumber(requestMemberInfoArrived.getPhoneNumber());
            taxi.setCreditCardNumber(requestMemberInfoArrived.getCreditCardNumber());
            taxi.setFriendPhoneNumber(requestMemberInfoArrived.getFriendPhoneNumber());
            taxiRepository.save(taxi);


        System.out.println(this.getClass().getName()+" : wheneverRequestMemberInfoArrived_AddMemberInfo   -----------------end------------------");
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}