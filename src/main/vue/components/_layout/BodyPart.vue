<template>
  <board-categories/>
  <div class="body-container">
    <router-view></router-view>
  </div>
</template>

<script>
import BoardCategories from "@/components/category/BoardCategoryList.vue";

export default {
  name: "BodyPart",
  components: {BoardCategories}
}
</script>

<style scoped>
.body-container {
  padding: 0px 15px;
  background-color:#f9f4d8;
}
</style>