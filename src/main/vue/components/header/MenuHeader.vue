<template>
  <div class="" style="background:#91A27A;">
    <div class="header-container" style="">
      <router-link :active-class="false" to="/">
          <img src="@/assets/image/outddo_logo_m4.png" style="padding:10px 0px;width:250px;vertical-align: top"/>
      </router-link>

    </div>
  </div>
  <div style="background: #6c785f">
    <div class="header-container sub-header-container">
      <q-breadcrumbs active-color="white" class="text-white" align="left">
        <q-breadcrumbs-el label="최신글"/>
        <q-breadcrumbs-el label="인기글"/>
      </q-breadcrumbs>
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