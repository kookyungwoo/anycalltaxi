package call.taxi.service;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @PrePersist
    public void onPrePersist(){  //결제이력을 저장한 후 적당한 시간 끌기

        try {
            System.out.println("++++++++++++++++onPrePersist++++++++++++++++++++");
            System.out.println("++++++++++++++++onPrePersist++++++++++++++++++++");
            System.out.println("++++++++++++++++onPrePersist++++++++++++++++++++");
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostPersist
    public void onPostPersist(){

        try {
            System.out.println("++++++++++++++++onPostPersist++++++++++++++++++++");
            System.out.println("++++++++++++++++onPostPersist++++++++++++++++++++");
            System.out.println("++++++++++++++++onPostPersist++++++++++++++++++++");
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        PaymentApproved paymentApproved = new PaymentApproved();
        BeanUtils.copyProperties(this, paymentApproved);
        paymentApproved.publishAfterCommit();

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