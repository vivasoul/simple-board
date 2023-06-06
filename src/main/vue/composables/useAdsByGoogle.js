import {ref} from "vue"

const adsByGoogle = ref([])

export default function useAdsByGoogle() {

    return {
        adsByGoogle
    }
}