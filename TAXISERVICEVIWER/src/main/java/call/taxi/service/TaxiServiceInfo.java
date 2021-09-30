package call.taxi.service;

import javax.persistence.*;

@Entity
@Table(name="TaxiServiceInfo_table")
public class TaxiServiceInfo {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long callId;
   
   
  
   
     
        private String memberId;
        private String taxiNumber;
        private String taxiInfo;
        private String memberName;
        private String startAddress;
        private String endAddress;
        private String phoneNumber;
        private String friendPhoneNumber;
        private String taxiStatus;
        private String creditCardNumber;
        private Long payAmt;
     
     


        public String getTaxiStatus() {
            return taxiStatus;
        }

        public void setTaxiStatus(String taxiStatus) {
            this.taxiStatus = taxiStatus;
        }

        public String getCreditCardNumber() {
            return creditCardNumber;
        }

        public void setCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
        }

        public Long getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(Long payAmt) {
            this.payAmt = payAmt;
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
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
        public String getFriendPhoneNumber() {
            return friendPhoneNumber;
        }

        public void setFriendPhoneNumber(String friendPhoneNumber) {
            this.friendPhoneNumber = friendPhoneNumber;
        }
        

}