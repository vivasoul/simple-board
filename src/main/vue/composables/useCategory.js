import {ref} from "vue"

const name = ref("")
const key = ref("")
export default function useCategory() {

    return {
        name,
        key
    }
}