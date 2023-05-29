<template>
  <div class="q-pa-xs text-center">
    <q-select
        v-model="searchMode"
        :options="modes"
        style="width:100px;"
    />
    <q-input
        v-model="searchText"
        filled
        dense
        style="width:200px;"
    />
  </div>
</template>

<script>
export default {
  name: "BoardSearchBox",
  data() {
    return {
      "modes": [
        {"label": "제목", "value": 0},
        {"label": "제목+내용", "value": 1},
        {"label": "내용", "value": 2}
      ],
      "searchMode": {"label": "제목", "value": 0},
      "searchText": ""
    }
  }
}
</script>

<style scoped>

</style>