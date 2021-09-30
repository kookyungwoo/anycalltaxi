package call.taxi.service;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Taxi_table")
public class Taxi {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long callId;
    private String memberId;
    private String memberName;
    private String startAddress;
    private String endAddress;
    private String callStatus;
    private String taxiNumber;
    private String taxiInfo;
    private String taxiStatus;
    private String phoneNumber;
    private String creditCardNumber;
    private String friendPhoneNumber;
    private Long payAmt;
    private String estimateScore;

    @PostPersist
    public void onPostPersist(){
        TaxiCallReceived taxiCallReceived = new TaxiCallReceived();
        BeanUtils.copyProperties(this, taxiCallReceived);
        taxiCallReceived.publishAfterCommit();

        TaxiAssigned taxiAssigned = new TaxiAssigned();
        BeanUtils.copyProperties(this, taxiAssigned);
        taxiAssigned.publishAfterCommit();

        TaxiServiceStarted taxiServiceStarted = new TaxiServiceStarted();
        BeanUtils.copyProperties(this, taxiServiceStarted);
        taxiServiceStarted.publishAfterCommit();

        TaxiServiceEnded taxiServiceEnded = new TaxiServiceEnded();
        BeanUtils.copyProperties(this, taxiServiceEnded);
        taxiServiceEnded.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        //call.taxi.service.external.Payment payment = new call.taxi.service.external.Payment();
        // mappings goes here
        //Application.applicationContext.getBean(call.taxi.service.external.PaymentService.class).pay(payment);

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
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
    public String getTaxiNumber() {
        return taxiNumber;
    }

    public void setTaxiNumber(String taxiNumber) {
        this.taxiNumber = taxiNumber;
    }
    public String getTaxiInfo() {
        return taxiInfo;
    }

    public void setTaxiInfo(String taxiInfo) {
        this.taxiInfo = taxiInfo;
    }
    public String getTaxiStatus() {
        return taxiStatus;
    }

    public void setTaxiStatus(String taxiStatus) {
        this.taxiStatus = taxiStatus;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }
    public Long getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(Long payAmt) {
        this.payAmt = payAmt;
    }
    public String getEstimateScore() {
        return estimateScore;
    }

    public void setEstimateScore(String estimateScore) {
        this.estimateScore = estimateScore;
    }




}