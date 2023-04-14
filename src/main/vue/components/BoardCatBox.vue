<template>
  <div class="q-pa-xs">
    <q-select
        outlined dense
        v-model="catNo"
        :options="[]"
        label="태그 선택"
        bg-color="lime-3"
        on-update:model-value="handleCatNoChange"></q-select>
  </div>
</template>

<script>
export default {
  name: "BoardCatBox",
  props:["modelValue"],
  emits:["update:modelValue"],
  data() {
    return {
      catNo: 0
    }
  },
  methods: {
    handleCatNoChange(catNo) {
      this.$emit("update:modelValue", catNo)
    }
  }
}
</script>

<style scoped>

</style>