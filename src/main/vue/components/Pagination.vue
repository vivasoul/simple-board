<template>
  <div class="pagination-widget">
    <div class="pagination-stub" :class="{ active: curPage == n }" v-for="n in maxPage" @click="$emit('pageClick', n)">{{ n }}</div>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: ["curPage", "maxPage"],
  emits: ["pageClick"]
}
</script>

<style scoped>
.pagination-widget {
  margin: 0px auto;
  padding:20px 0px;
  text-align:center;

}
.pagination-widget .pagination-stub {
  display:inline-block;
  width:20px;
  height:25px;
  border:1px solid gray;
  cursor:pointer;
}

.pagination-widget .pagination-stub.active {
  background-color: #d7e7a7;
  color:#ffffff;
}

.pagination-widget .pagination-stub:hover {
  background-color: #429fdb;
  color:#ffffff;
}
</style>