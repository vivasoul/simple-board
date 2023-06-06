<template>
  <board-categories/>
  <div class="body-container">
    <content-ad
        ad-slot="8168605109"
        top="10px"
        height="150px"
    />
    <router-view></router-view>
    <content-ad
        ad-slot="9290115084"
        height="200px"
    />
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
import ContentAd from "@/components/_ads/ContentAd.vue"

export default {
  name: "BodyPart",
  components: {ContentAd, SideAd, BoardCategories}
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