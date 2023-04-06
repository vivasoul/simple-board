import {createRouter, createWebHashHistory} from 'vue-router';

import BoardList from "@/components/BoardList.vue";
import BoardDetail from "@/components/BoardDetail.vue";
import BoardRegist from "@/components/BoardRegist.vue";
import BoardCategoryList from "@/components/BoardCategoryList.vue";

const routes = [
    {path: "/", component: BoardCategoryList},
    {path: "/board", component: BoardList},
    {path: "/board-new", component: BoardRegist},
    {path: "/board/:brdNo", component: BoardDetail, props: true},
]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
})

export default router