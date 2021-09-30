package call.taxi.service;

import call.taxi.service.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired NotifyRepository notifyRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiAssigned_TaxiAssign(@Payload TaxiAssigned taxiAssigned){

        if(!taxiAssigned.validate()) return;

        System.out.println("\n\n##### listener TaxiAssign : " + taxiAssigned.toJson() + "\n\n");



        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiServiceStarted_ServiceStart(@Payload TaxiServiceStarted taxiServiceStarted){

        if(!taxiServiceStarted.validate()) return;

        System.out.println("\n\n##### listener ServiceStart : " + taxiServiceStarted.toJson() + "\n\n");



        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiServiceEnded_ServiceEnd(@Payload TaxiServiceEnded taxiServiceEnded){

        if(!taxiServiceEnded.validate()) return;

        System.out.println("\n\n##### listener ServiceEnd : " + taxiServiceEnded.toJson() + "\n\n");



        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_PayApprove(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener PayApprove : " + paymentApproved.toJson() + "\n\n");



        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}