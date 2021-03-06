package call.taxi.service;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@Entity
@Table(name="MemberShip_table")
public class MemberShip {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long memberId;
    private String memberName;
    private String phoneNumber;
    private String creditCardNumber;
    private String friendPhoneNumber;
    private String status;

    @PostPersist
    public void onPostPersist(){
        MemberShipRegistered memberShipRegistered = new MemberShipRegistered();
        BeanUtils.copyProperties(this, memberShipRegistered);
        memberShipRegistered.publishAfterCommit();

        RequestMemberInfoArrived requestMemberInfoArrived = new RequestMemberInfoArrived();
        BeanUtils.copyProperties(this, requestMemberInfoArrived);
        requestMemberInfoArrived.publishAfterCommit();
   }

   @PostUpdate
   private void onPostUpdate(){
       if( "고객정보요청도착".equals(this.getStatus())){
            System.out.println(this.getClass().getName()+" :   고객정보요청도착   #########################");
            System.out.println(this.getClass().getName()+" :   고객정보요청도착   #########################");
            System.out.println(this.getClass().getName()+" :   고객정보요청도착   #########################");
            System.out.println(this.getClass().getName()+" :   고객정보요청도착   #########################");
           
           MemberShipRepository memberShipRepository = MembershipApplication.applicationContext.getBean(MemberShipRepository.class);
           Optional<MemberShip> orderOptional = memberShipRepository.findById(this.getMemberId());
           MemberShip memberShip = orderOptional.get();

           //kafka에 발송하는 함수 호출
           RequestMemberInfoArrived requestMemberInfoArrived = new RequestMemberInfoArrived();
           BeanUtils.copyProperties(memberShip, requestMemberInfoArrived);
           requestMemberInfoArrived.publishAfterCommit();
       }
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