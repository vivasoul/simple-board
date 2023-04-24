<template>
  <div class="q-pa-xs">
    <q-select
        outlined dense
        v-model="catObj"
        :options="categories"
        label="카테고리"
        bg-color="lime-3"
        @update:model-value="handleCatNoChange"></q-select>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories"
import useBoardDetail from "@/composables/useBoardDetail"

export default {
  name: "BoardCatBox",
  emits:["updateCatNo"],
  setup() {
    const { categories, categoryMap }= useCategories()
    const { catNo } = useBoardDetail()
    return {
      items : categories,
      catMap: categoryMap,
      catNo
    }
  },
  data() {
    const value = this.catNo

    return {
      catObj: { label: this.catMap[value], value }
    }
  },
  methods: {
    handleCatNoChange(val) {
      this.catNo = val.value
      //this.$emit("updateCatNo", val.value)
    }
  },
  computed: {
    categories() {
      return this.items.map( e => ({ "label": e.catNm, "value": e.catNo }) )
    }
  }
}
</script>

<style scoped>

</style>