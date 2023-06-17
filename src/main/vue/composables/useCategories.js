import {getCategories as loadCategories} from "@/data/api/category"
import {ref} from "vue"

const categories = ref([])
const mountainCats = ref([])
const mysteryCats = ref([])
const categoryMap = ref({})
const getCategory = function(key) {
    return categoryMap.value[key] || {}
}
const getCategoryName = function(key) {

    return getCategory(key)["catNm"]
}
const getCategoryType = function(key) {
    return getCategory(key)["catType"]
}
const getCategories = function(catType) {
    if(catType == 1) {
        return mountainCats
    } else if(catType == 2) {
        return mysteryCats
    } else {
        return categories
    }
}

export default function useCategories() {

    if(!categories.value.length) {
        const p = loadCategories()

        p.then((data) => {
            categories.value = data
            data.forEach(({catNo, catType, catNm}) => {
                categoryMap.value[catNo] = {catNo, catType, catNm}
            })
            mountainCats.value = Array.prototype.filter.call(data, e => e.catType == 1) || []
            mysteryCats.value = Array.prototype.filter.call(data, e => e.catType == 2) || []
        })
        //console.log("fetch")
    } else {
        /* DO NOTHING */
        //console.log("cache")
    }

    return {
        mountainCats,
        mysteryCats,
        categoryMap,
        getCategories,
        getCategory,
        getCategoryName,
        getCategoryType
    }
}