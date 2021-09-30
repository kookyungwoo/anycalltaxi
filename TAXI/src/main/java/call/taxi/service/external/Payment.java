package call.taxi.service.external;

public class Payment {

    private Long paymentId;
    private Integer callId;
    private String payDate;
    private Long payAmt;
    private String phoneNumber;
    private String payStatus;
    private String creditCardNumber;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Integer getCallId() {
        return callId;
    }
    public void setCallId(Integer callId) {
        this.callId = callId;
    }
    public String getPayDate() {
        return payDate;
    }
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public Long getPayAmt() {
        return payAmt;
    }
    public void setPayAmt(Long payAmt) {
        this.payAmt = payAmt;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

}
