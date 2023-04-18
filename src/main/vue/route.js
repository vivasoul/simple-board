import {createRouter, createWebHashHistory} from 'vue-router';

import BoardList from "@/components/board/BoardList.vue"
import BoardDetail from "@/components/board/BoardDetail.vue"
import BoardRegist from "@/components/board/BoardRegist.vue"
import BoardCategoryList from "@/components/category/BoardCategoryList.vue"

const routes = [
    {path: "/", component: BoardList},
    {path: "/board", component: BoardList, props: (route) => ({ catNo: route.query.catNo })},
    {path: "/board-new", component: BoardRegist},
    {path: "/board/:brdNo", component: BoardDetail, props: true},
]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

export default router