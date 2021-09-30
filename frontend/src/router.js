
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CallManager from "./components/CallManager"

import TaxiManager from "./components/TaxiManager"

import NotifyManager from "./components/NotifyManager"


import PaymentManager from "./components/PaymentManager"

import MemberShipManager from "./components/MemberShipManager"


import TaxiServiceInfo from "./components/TaxiServiceInfo"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/calls',
                name: 'CallManager',
                component: CallManager
            },

            {
                path: '/taxis',
                name: 'TaxiManager',
                component: TaxiManager
            },

            {
                path: '/notifies',
                name: 'NotifyManager',
                component: NotifyManager
            },


            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/memberShips',
                name: 'MemberShipManager',
                component: MemberShipManager
            },


            {
                path: '/taxiServiceInfos',
                name: 'TaxiServiceInfo',
                component: TaxiServiceInfo
            },


    ]
})
