import { ref } from "vue";

export default function usePagination() {
    const maxPage = ref(1)
    const curPage = ref(1)

    return {
        curPage,
        maxPage
    }
}