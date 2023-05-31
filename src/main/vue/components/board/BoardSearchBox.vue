<template>
  <div class="q-pa-xs text-center">
    <q-select
        v-model="searchMode"
        :options="modes"
        filled
        dense
        bg-color="white"
        color="black"
        class="search-input"
        style="width:120px;margin-right:5px;"
    />
    <q-input
        v-model="searchText"
        filled
        dense
        bg-color="white"
        color="black"
        class="search-input"
        type="search"
        style="width:200px;"
        @keyup="handleSearchEnter"
    >
      <template v-slot:append>
        <q-icon name="search" @click="search"/>
      </template>
    </q-input>
  </div>
</template>

<script>
export default {
  name: "BoardSearchBox",
  emits: ["postSearch"],
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
  },
  methods: {
    handleSearchEnter(e) {
      if(e.keyCode === 13) {
        this.search()
      }
    },
    search() {
      this.$emit("postSearch", this.searchMode.value, this.searchText)
    }
  }
}
</script>

<style scoped>
.search-input {
  display:inline-block;
  vertical-align: top;
  font-size:12px;
  line-height: 12px;
}
</style>