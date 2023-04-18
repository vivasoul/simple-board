import {getCategories} from "@/data/api/category"
import {ref} from "vue"

const categories = ref([])
const categoryMap = ref({})
export default function useCategories() {

    if(!categories.value.length) {
        const p = getCategories()

        p.then((data) => {
            categories.value = data
            data.forEach(({catNo, catNm}) => {
                categoryMap.value[catNo] = catNm
            })
        })
        console.log("fetch")
    } else {
        /* DO NOTHING */
        console.log("cache")
    }

    return {
        categories,
        categoryMap
    }
}