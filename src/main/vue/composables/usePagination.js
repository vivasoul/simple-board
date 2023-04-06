import { ref } from "vue";

const ROW_PER_PAGE = 10;
const totPage = ref(1)
const curPage = ref(1)

export default function usePagination(cp, rpp) {

    return {
        curPage
    }
}