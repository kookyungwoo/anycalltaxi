package call.taxi.service;

public class TaxiCallReceived extends AbstractEvent {

    private Long callId;
    private String memberId;

    public TaxiCallReceived(){
        super();
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
}