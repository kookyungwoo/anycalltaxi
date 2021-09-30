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

        Notify notify = new Notify();
        notify.setToPhoneNumber(taxiAssigned.getPhoneNumber());
        notify.setFromPhoneNumber("02-1234-5678");
        notify.setMessage("택시가 배정되었습니다 : "+taxiAssigned.getTaxiInfo());
        notifyRepository.save(notify);

        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiServiceStarted_ServiceStart(@Payload TaxiServiceStarted taxiServiceStarted){

        if(!taxiServiceStarted.validate()) return;

        System.out.println("\n\n##### listener ServiceStart : " + taxiServiceStarted.toJson() + "\n\n");

        Notify notify = new Notify();
        notify.setToPhoneNumber(taxiServiceStarted.getPhoneNumber());
        notify.setFromPhoneNumber("02-1234-5678");
        notify.setMessage("택시운행이 시작되었습니다 : "+taxiServiceStarted.getTaxiInfo());
        notifyRepository.save(notify);

        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTaxiServiceEnded_ServiceEnd(@Payload TaxiServiceEnded taxiServiceEnded){

        if(!taxiServiceEnded.validate()) return;

        System.out.println("\n\n##### listener ServiceEnd : " + taxiServiceEnded.toJson() + "\n\n");

        Notify notify = new Notify();
        notify.setToPhoneNumber(taxiServiceEnded.getPhoneNumber());
        notify.setFromPhoneNumber("02-1234-5678");
        notify.setMessage("택시운행이 종료되었습니다 : "+taxiServiceEnded.getTaxiInfo());
        notifyRepository.save(notify);

        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_PayApprove(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener PayApprove : " + paymentApproved.toJson() + "\n\n");

        Notify notify = new Notify();
        notify.setToPhoneNumber(paymentApproved.getPhoneNumber());
        notify.setFromPhoneNumber("02-1234-5678");
        notify.setMessage("택시비용 결제요청 되었습니다");
        notifyRepository.save(notify);

        // Sample Logic //
        // Notify notify = new Notify();
        // notifyRepository.save(notify);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}