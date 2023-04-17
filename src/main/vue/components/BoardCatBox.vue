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

export default {
  name: "BoardCatBox",
  props:["modelValue"],
  emits:["update:modelValue"],
  setup() {
    const { categories }= useCategory()
    return {
      items : categories
    }
  },
  data() {
    const value = this.modelValue

    return {
      catNo: { label: this.getLabel(value), value }
    }
  },
  methods: {
    handleCatNoChange(val) {
      console.log("c="+val.value)
      this.$emit("update:modelValue", val.value)
    },
    getLabel(val) {
      const cat = this.items.find((e) => e.catNo == val)
      return cat ? cat.catNm : ""
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