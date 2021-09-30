package call.taxi.service;

import call.taxi.service.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class TaxiServiceInfoViewHandler {


    @Autowired
    private TaxiServiceInfoRepository taxiServiceInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiCallReceived_then_CREATE_1 (@Payload TaxiCallReceived taxiCallReceived) {
        try {

            if (!taxiCallReceived.validate()) return;

            // view 객체 생성
            TaxiServiceInfo taxiServiceInfo = new TaxiServiceInfo();
            // view 객체에 이벤트의 Value 를 set 함
            taxiServiceInfo.setCallId(taxiCallReceived.getCallId());
            taxiServiceInfo.setMemberId(taxiCallReceived.getMemberId());
            taxiServiceInfo.setMemberId(taxiCallReceived.getMemberId());
            // view 레파지 토리에 save
            taxiServiceInfoRepository.save(taxiServiceInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiAssigned_then_UPDATE_1(@Payload TaxiAssigned taxiAssigned) {
        try {
            if (!taxiAssigned.validate()) return;
                // view 객체 조회
            //Optional<TaxiServiceInfo> taxiServiceInfoOptional = taxiServiceInfoRepository.findByCallId(taxiAssigned.getCallId());

            /*if( taxiServiceInfoOptional.isPresent()) {
                 TaxiServiceInfo taxiServiceInfo = taxiServiceInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 taxiServiceInfo.setTaxiNumber(taxiAssigned.getTaxiNumber());
                 taxiServiceInfo.setTaxiInfo(taxiAssigned.getTaxiInfo());
                 taxiServiceInfo.setTaxiInfo(taxiAssigned.getTaxiInfo());
                // view 레파지 토리에 save
                 taxiServiceInfoRepository.save(taxiServiceInfo);
                }*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiServiceStarted_then_UPDATE_2(@Payload TaxiServiceStarted taxiServiceStarted) {
        try {
            if (!taxiServiceStarted.validate()) return;
                // view 객체 조회
            /*Optional<TaxiServiceInfo> taxiServiceInfoOptional = taxiServiceInfoRepository.findByCallId(taxiServiceStarted.getCallId());

            if( taxiServiceInfoOptional.isPresent()) 
            {
                 TaxiServiceInfo taxiServiceInfo = taxiServiceInfoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                 taxiServiceInfo.setMemberName(taxiServiceStarted.getMemberName());
                 taxiServiceInfo.setStartAddress(taxiServiceStarted.getStartAddress());
                 taxiServiceInfo.setEndAddress(taxiServiceStarted.getEndAddress());
                 taxiServiceInfo.setPhoneNumber(taxiServiceStarted.getPhoneNumber());
                 taxiServiceInfo.setFriendPhoneNumber(taxiServiceStarted.getFriendPhoneNumber());
                // view 레파지 토리에 save
                 taxiServiceInfoRepository.save(taxiServiceInfo);
            }*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiServiceStarted_then_UPDATE_3(@Payload TaxiServiceStarted taxiServiceStarted) {
        try {
            if (!taxiServiceStarted.validate()) return;
                // view 객체 조회
            /*Optional<TaxiServiceInfo> taxiServiceInfoOptional = taxiServiceInfoRepository.findByCallId(taxiServiceStarted.getCallId());

            if( taxiServiceInfoOptional.isPresent()) {
                 TaxiServiceInfo taxiServiceInfo = taxiServiceInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 taxiServiceInfo.setMemberName(taxiServiceStarted.getMemberName());
                 taxiServiceInfo.setStartAddress(taxiServiceStarted.getStartAddress());
                 taxiServiceInfo.setEndAddress(taxiServiceStarted.getEndAddress());
                 taxiServiceInfo.setPhoneNumber(taxiServiceStarted.getPhoneNumber());
                 taxiServiceInfo.setFriendPhoneNumber(taxiServiceStarted.getFriendPhoneNumber());
                 taxiServiceInfo.setTaxiStatus(taxiServiceStarted.getTaxiStatus());
                 taxiServiceInfo.setTaxiStatus(taxiServiceStarted.getTaxiStatus());
                // view 레파지 토리에 save
                 taxiServiceInfoRepository.save(taxiServiceInfo);
                }*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiServiceEnded_then_UPDATE_4(@Payload TaxiServiceEnded taxiServiceEnded) {
        try {
            if (!taxiServiceEnded.validate()) return;
                // view 객체 조회
            /*Optional<TaxiServiceInfo> taxiServiceInfoOptional = taxiServiceInfoRepository.findByCallId(taxiServiceEnded.getCallId());

            if( taxiServiceInfoOptional.isPresent()) {
                 TaxiServiceInfo taxiServiceInfo = taxiServiceInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 taxiServiceInfo.setCreditCardNumber(taxiServiceEnded.getCreditCardNumber());
                 taxiServiceInfo.setPayAmt(taxiServiceEnded.getPayAmt());
                 taxiServiceInfo.setTaxiStatus(taxiServiceEnded.getTaxiStatus());
                // view 레파지 토리에 save
                 taxiServiceInfoRepository.save(taxiServiceInfo);
                }*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenTaxiServiceEnded_then_UPDATE_5(@Payload TaxiServiceEnded taxiServiceEnded) {
        try {
            if (!taxiServiceEnded.validate()) return;
                // view 객체 조회
            /*Optional<TaxiServiceInfo> taxiServiceInfoOptional = taxiServiceInfoRepository.findByCallId(taxiServiceEnded.getCallId());

            if( taxiServiceInfoOptional.isPresent()) {
                 TaxiServiceInfo taxiServiceInfo = taxiServiceInfoOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 taxiServiceInfo.setCreditCardNumber(taxiServiceEnded.getCreditCardNumber());
                 taxiServiceInfo.setPayAmt(taxiServiceEnded.getPayAmt());
                 taxiServiceInfo.setTaxiStatus(taxiServiceEnded.getTaxiStatus());
                // view 레파지 토리에 save
                 taxiServiceInfoRepository.save(taxiServiceInfo);
                }*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

