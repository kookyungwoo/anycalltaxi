package call.taxi.service;

import javax.persistence.*;
import java.util.Optional;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Call_table")
public class Call {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long callId;
    private String memberId;
    private String phoneNumber;
    private String startAddress;
    private String endAddress;
    private String callStatus;

    @PostPersist
    public void onPostPersist()
    {
        TaxiCalled taxiCalled = new TaxiCalled();
        BeanUtils.copyProperties(this, taxiCalled);
        taxiCalled.publishAfterCommit();
     }

    @PostUpdate
    private void onPostUpdate()
    {
        if( "호출취소".equals(this.getCallStatus()))
        {
            
            CallRepository callRepository = CallApplication.applicationContext.getBean(CallRepository.class);
            Optional<Call> orderOptional = callRepository.findById(this.getCallId());
            Call call = orderOptional.get();

            //kafka에 발송하는 함수 호출
            TaxiCallCanceled taxiCallCanceled = new TaxiCallCanceled();
            BeanUtils.copyProperties(call, taxiCallCanceled);
            taxiCallCanceled.publishAfterCommit();
        }
        else if( "서비스평가".equals(this.getCallStatus()))
        {

            ServiceEstimated serviceEstimated = new ServiceEstimated();
            BeanUtils.copyProperties(this, serviceEstimated);
            serviceEstimated.publishAfterCommit();
         
        }
    }


    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }
    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }
    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }




}