<template>
  <div class="body-container">
    <div>
      <board-categories/>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import BoardCategories from "@/components/BoardCategoryList.vue";

export default {
  name: "BodyPart",
  components: {BoardCategories}
}
</script>

<style scoped>
.body-container {
  padding: 0px 15px;
}
</style>