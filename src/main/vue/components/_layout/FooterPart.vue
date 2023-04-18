<template>
  <div class="footer-container">
    <q-breadcrumbs gutter="sm" active-color="white">
      <q-breadcrumbs-el icon="home" to="/" />
      <q-breadcrumbs-el label="개인정보처리방침" />
      <q-breadcrumbs-el label="이용약관" />
    </q-breadcrumbs>
    <q-ajax-bar :hijack-filter="myFilterFn" />
  </div>
</template>

<script>
export default {
  name: "FooterPart",
  methods:{
    myFilterFn(url){
      // console.log("filtered="+url)
      return /^http:\/\/localhost:8080/.test(url)
    }
  }
}
</script>

<style scoped>
.footer-container {
  padding: 20px;
  height: 150px;
  color: white;
}
</style>