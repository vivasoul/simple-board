<template>
  <board-categories/>
  <div class="body-container">
    <router-view></router-view>
    <side-ad
        ad-slot="3006959528"
        top="0px"
        left="-220px"
    />
    <side-ad
        ad-slot="2208883172"
        top="0px"
        right="-220px"
    />
  </div>

</template>

<script>
import BoardCategories from "@/components/category/BoardCategoryList.vue"
import SideAd from "@/components/_ads/SideAd.vue"

export default {
  name: "BodyPart",
  components: {SideAd, BoardCategories}
}
</script>

<style scoped>
.body-container {
  padding: 0px 15px;
  background-color:#f9f4d8;
  color:black;
}
@media(max-width: 850px) {
  .body-container {
    padding: 0px 5px;
  }
}
</style>