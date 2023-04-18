<template>
  <div class="q-pa-xs">
    <q-select
        outlined dense
        v-model="catNo"
        :options="categories"
        label="카테고리"
        bg-color="lime-3"
        @update:model-value="handleCatNoChange"></q-select>
  </div>
</template>

<script>
import useCategory from "@/composables/useCategory";
import useCategories from "@/composables/useCategories";

export default {
  name: "BoardCatBox",
  props:["modelValue"],
  emits:["update:modelValue"],
  setup() {
    const { categories, categoryMap }= useCategories()
    return {
      items : categories,
      catMap: categoryMap
    }
  },
  data() {
    const value = this.modelValue

    return {
      catNo: { label: this.catMap[value], value }
    }
  },
  methods: {
    handleCatNoChange(val) {
      this.$emit("update:modelValue", val.value)
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