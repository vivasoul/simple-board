import {createRouter, createWebHistory} from 'vue-router';

import BoardList from "@/components/BoardList.vue";
import BoardDetail from "@/components/BoardDetail.vue";

const routes = [
    {path: "", component: BoardList},
    {path: "/board/:brdNo", component: BoardDetail, props: true},
]

const router = createRouter({
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: createWebHistory(),
    routes, // short for `routes: routes`
})

export default router