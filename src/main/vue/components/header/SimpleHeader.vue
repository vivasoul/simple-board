<template>
  <router-link to="/">
    <div class="header-container" @click="searchAll">
      <img class="logo-image" src="@/assets/image/outddo_logo_m4.png" />
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

export default {
  name: "SimpleHeader",
  setup() {
    const { searchAll } = useBoardSearch()

    return {
      searchAll
    }
  }
}
</script>