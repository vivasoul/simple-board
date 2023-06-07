<template>
  <router-link to="/">
    <div class="header-container" @click="searchAll">
      <img src="@/assets/image/outddo_logo_m4.png" style="padding:10px 0px;"/>
    </div>
  </router-link>
<!--  <div class="header-container ad-container">
    <content-ad
        ad-slot="8168605109"
        format="horizontal"
    />
  </div>-->
</template>

<script>
import useBoardSearch from "@/composables/useBoardSearch"
import ContentAd from "@/components/_ads/ContentAd.vue"

export default {
  name: "HeaderPart",
  components: {ContentAd},
  setup() {
    const { searchAll } = useBoardSearch()

    return {
      searchAll
    }
  }
}
</script>

<style scoped>
.header-container {
  height: 70px;
  line-height: 70px;
  padding: 0px 20px;
  color: #EBC447;
  font-size:23px;
  font-weight: 700;
}
.header-container.ad-container {
  padding: 0px;
  margin-top:20px;
}

@media (min-width: 850px) {
  .header-container {
    width: 850px;
    margin:0 auto;
    font-size: 30px;
  }
}
</style>