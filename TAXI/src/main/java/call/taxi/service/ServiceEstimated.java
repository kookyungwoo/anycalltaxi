package call.taxi.service;

public class ServiceEstimated extends AbstractEvent {

    private Long id;
    private Long callId;
    private String memberId;
    private String phoneNumber;
    private String estimateScore;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String getEstimateScore() {
        return estimateScore;
    
    }

    public void setEstimateScore(String estimateScore) {
        this.estimateScore = estimateScore;
   
    }
}