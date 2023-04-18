import {ref} from "vue"

const category = ref(null)
export default function useCategory() {

    return {
        category
    }
}