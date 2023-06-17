<template>
  <div class="q-pa-xs">
    <q-select
        outlined dense
        v-model="catObj"
        :options="categories"
        label="카테고리"
        bg-color="lime-3"
        color="dark"
        :popup-content-style="{ color: '#000000' }"
        @update:model-value="handleCatNoChange"></q-select>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories"
import useBoardDetail from "@/composables/useBoardDetail"
import useBoardSearch from "@/composables/useBoardSearch"

export default {
  name: "BoardCatBox",
  setup() {
    const { getCategories, getCategoryType, categoryMap }= useCategories()
    const { catNo } = useBoardDetail()

    const catType = getCategoryType(catNo.value)
    const categories = getCategories(catType)

    return {
      items: categories,
      categoryMap,
      catNo
    }
  },
  data() {
    const value = this.catNo
    const cat = this.categoryMap[value] || {}

    return {
      catObj: { label: cat["catNm"] , value }
    }
  },
  computed: {
    categories() {
      return Array.prototype.map.call(this.items, e => ({ "label": e.catNm, "value": e.catNo }))
    }
  },
  methods: {
    handleCatNoChange(val) {
      this.catNo = val.value
    }
  }
}
</script>

<style scoped>

</style>