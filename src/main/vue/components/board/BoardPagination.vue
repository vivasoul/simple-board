<template>
  <div class="q-pa-lg flex flex-center">
    <q-pagination
        v-model="curPage"
        color="green"
        :max="maxPage"
        :max-pages="5"
        boundary-numbers
        @update:model-value="handlePageChange"
    />
  </div>
</template>

<script>

export default {
  name: "BoardPagination",
  props:["maxPage"],
  emits:["pageChanged"],
  data() {
    return {
      curPage: 1
    }
  },
  methods: {
    handlePageChange() {
      this.$emit("pageChanged", this.curPage)
    }
  }
}
</script>

<style scoped>

</style>