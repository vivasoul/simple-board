<template>
  <div style="background:#152E4A;">
    <div class="header-container">
      <router-link :active-class="false" to="/">
          <img class="logo-image" src="@/assets/image/outddo_logo_m5.png" style=""/>
          <span class="logo-text">아우또</span>
      </router-link>
    </div>
  </div>
  <div style="background: rgb(21 46 74 / 75%)">
    <div class="header-container sub-header-container">
      현재게시판
    </div>
  </div>
</template>

<script>
export default {
  name: "MenuHeader"
}
</script>

<style scoped>

</style>