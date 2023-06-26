<template>
  <div style="background:#152E4A;">
    <div class="header-container">
      <router-link :active-class="false" to="/">
          <img class="logo-image" src="@/assets/image/outddo_logo_m5.png" style=""/>
          <span class="logo-text">아우또</span>
      </router-link>
    </div>
  </div>
  <div v-if="boardTitle" style="background: rgb(21 46 74 / 75%)">
    <div class="header-container sub-header-container">
      {{ boardTitle }}
    </div>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories"
import useBoardSearch from "@/composables/useBoardSearch"

export default {
  name: "MenuHeader",
  setup() {
    const { getCategoryName } = useCategories()
    const { sCatNo } = useBoardSearch()

    return {
      getCategoryName,
      sCatNo
    }
  },
  computed: {
    boardTitle() {
      return this.getCategoryName(this.sCatNo)
    }
  }
}
</script>

<style scoped>

</style>