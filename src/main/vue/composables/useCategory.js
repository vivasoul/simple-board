import {getCategories} from "@/data/api/category"
import {ref} from "vue"

const categories = ref([])
const category = ref(null)
export default function useCategory() {

    const p = getCategories()
    if(!categories.value.length) {
        p.then((data) => {
            categories.value = data
        })
    } else {
        /* DO NOTHING */
    }

    return {
        categories,
        category
    }
}