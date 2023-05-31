import {createRouter, createWebHistory} from 'vue-router';

import BoardList from "@/components/board/BoardList.vue"
import BoardDetail from "@/components/board/BoardDetail.vue"
import BoardRegist from "@/components/board/BoardRegist.vue"
import PriviteCondition from "@/components/info/PriviteCondition.vue"
import TermsCondition from "@/components/info/TermsCondition.vue"

const routes = [
    {path: "/", component: BoardList},
    {path: "/boards", component: BoardList, props: (route) => ({ catNo: route.query.catNo })},
    {path: "/boards-new", component: BoardRegist},
    {path: "/boards/:brdNo", component: BoardDetail, props: true},
    {path: "/cond/private", component: PriviteCondition},
    {path: "/cond/terms", component: TermsCondition }
]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(),
    routes, // short for `routes: routes`
})

export default router