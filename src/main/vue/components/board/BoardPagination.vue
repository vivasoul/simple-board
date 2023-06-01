<template>
  <div class="q-pa-lg flex flex-center">
    <q-pagination
        v-model="curPage"
        color="green"
        :max="maxPage"
        :max-pages="8"
        boundary-numbers
        @update:model-value="handlePageChange"
    />
  </div>
</template>

<script>

import useBoardSearch from "@/composables/useBoardSearch"

export default {
  name: "BoardPagination",
  setup() {
    const { sCurPage, maxPage, searchPage } = useBoardSearch()

    return {
      curPage: sCurPage,
      maxPage,
      searchPage
    }
  },
  methods: {
    handlePageChange() {
      this.searchPage()
    }
  }
}
</script>

<style scoped>

</style>