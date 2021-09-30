package call.taxi.service;

public class TaxiAssigned extends AbstractEvent {

    private Integer callId;
    private String taxiNumber;
    private String taxiInfo;
    private String phoneNumber;

    public Integer getCallId() {
        return callId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
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
}