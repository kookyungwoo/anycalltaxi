package call.taxi.service;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name="MemberShip_table")
public class MemberShip {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long memberId;
    private String memberName;
    private String phoneNumber;
    private String creditCardNumber;
    private String friendPhoneNumber;

    @PostPersist
    public void onPostPersist(){
        MemberShipRegistered memberShipRegistered = new MemberShipRegistered();
        BeanUtils.copyProperties(this, memberShipRegistered);
        memberShipRegistered.publishAfterCommit();

        RequestMemberInfoArrived requestMemberInfoArrived = new RequestMemberInfoArrived();
        BeanUtils.copyProperties(this, requestMemberInfoArrived);
        requestMemberInfoArrived.publishAfterCommit();

    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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




}