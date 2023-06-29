import {getCategories as loadCategories} from "@/data/api/category"
import {ref} from "vue"

const ALL_CATEGORY_TYPE = 0
const MOUNTAIN_CATEGORY_TYPE = 1
const MYSTERTY_CATEGORY_TYPE = 2

const pseudoCatMap = {
    "-2": {
        catNm: "베스트글(등산)",
        catType: MOUNTAIN_CATEGORY_TYPE
    },
    "-3": {
        catNm: "전체글(등산)",
        catType: MOUNTAIN_CATEGORY_TYPE
    },
    "-4": {
        catNm: "베스트글(미스터리)",
        catType: MYSTERTY_CATEGORY_TYPE
    },
    "-5": {
        catNm: "최신글",
        catType: ALL_CATEGORY_TYPE
    },
    "-6": {
        catNm: "베스트글",
        catType: ALL_CATEGORY_TYPE
    }
}
const categories = ref([])
const mountainCats = ref([])
const mysteryCats = ref([])
const categoryMap = ref({})
const getCategory = function(key) {
    let cat = null

    if(key < -1) {
        cat = pseudoCatMap[key]
    } else {
        cat = categoryMap.value[key]
    }

    return cat || {}
}
const getCategoryName = function(key) {

    return getCategory(key)["catNm"] || ""
}
const getCategoryType = function(key) {
    return getCategory(key)["catType"] || 0
}
const getCategories = function(catType) {
    if(catType == MOUNTAIN_CATEGORY_TYPE) {
        return mountainCats
    } else if(catType == MYSTERTY_CATEGORY_TYPE) {
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