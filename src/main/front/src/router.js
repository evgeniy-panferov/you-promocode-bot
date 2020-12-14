import Vue from 'vue'
import Router from 'vue-router'
import PartnershipsPrograms from "./views/PartnershipsPrograms";
import Coupons from "./views/Coupons";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: PartnershipsPrograms,
            name: 'PartnershipsPrograms'
        },
        {
            path: '/coupons/:id',
            component: Coupons,
            name: 'Coupons',
        },
        {
            path: '*',
            component: PartnershipsPrograms,
            name: 'PartnershipsPrograms'
        }
    ]
})