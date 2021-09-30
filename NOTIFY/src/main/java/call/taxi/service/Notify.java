package call.taxi.service;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Notify_table")
public class Notify {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long notifyId;
     private String fromPhoneNumber;
    private String toPhoneNumber;
    private String message;

    @PostPersist
    public void onPostPersist(){
        AssignInfoNotified assignInfoNotified = new AssignInfoNotified();
        BeanUtils.copyProperties(this, assignInfoNotified);
        assignInfoNotified.publishAfterCommit();

        SafeMsgNotified safeMsgNotified = new SafeMsgNotified();
        BeanUtils.copyProperties(this, safeMsgNotified);
        safeMsgNotified.publishAfterCommit();

        EventInfoNotified eventInfoNotified = new EventInfoNotified();
        BeanUtils.copyProperties(this, eventInfoNotified);
        eventInfoNotified.publishAfterCommit();

        PaymentNotified paymentNotified = new PaymentNotified();
        BeanUtils.copyProperties(this, paymentNotified);
        paymentNotified.publishAfterCommit();

    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }
    public String getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }
    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}