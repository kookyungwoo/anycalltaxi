<template>

<v-data-table
    :headers="headers"
    :items="taxiServiceInfo"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'TaxiServiceInfo',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "callId", value: "callId" },
            { text: "memberId", value: "memberId" },
            { text: "taxiNumber", value: "taxiNumber" },
            { text: "taxiInfo", value: "taxiInfo" },
            { text: "memberName", value: "memberName" },
            { text: "startAddress", value: "startAddress" },
            { text: "endAddress", value: "endAddress" },
            { text: "phoneNumber", value: "phoneNumber" },
            { text: "friendPhoneNumber", value: "friendPhoneNumber" },
            { text: "memberId", value: "memberId" },
            { text: "taxiNumber", value: "taxiNumber" },
            { text: "taxiInfo", value: "taxiInfo" },
            { text: "memberName", value: "memberName" },
            { text: "startAddress", value: "startAddress" },
            { text: "endAddress", value: "endAddress" },
            { text: "phoneNumber", value: "phoneNumber" },
            { text: "friendPhoneNumber", value: "friendPhoneNumber" },
            { text: "taxiStatus", value: "taxiStatus" },
            { text: "taxiStatus", value: "taxiStatus" },
            { text: "creditCardNumber", value: "creditCardNumber" },
            { text: "payAmt", value: "payAmt" },
            { text: "creditCardNumber", value: "creditCardNumber" },
            { text: "payAmt", value: "payAmt" },
        ],
        taxiServiceInfo : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/taxiserviceinfos')

      this.taxiServiceInfo = temp.data._embedded.taxiserviceinfos;

    },
    methods: {
    }
  }
</script>

