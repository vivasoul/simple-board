<template>
  <div class="footer-container">
    <q-breadcrumbs gutter="sm" active-color="black" class="text-black" align="center">
      <q-breadcrumbs-el icon="home" to="/" />
      <q-breadcrumbs-el :style="{fontWeight:900}" label="개인정보처리방침" to="/privite-cond" />
      <q-breadcrumbs-el label="이용약관" to="/terms-cond" />
    </q-breadcrumbs>
    <div class="footer-desc">
      <div class="footer-desc-item">Copyright © Outddo. All rights reserved</div>
      <div class="footer-desc-item">Email : webmaster@outddo.com</div>
    </div>
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

<style scoped lang="scss">
.footer-container {
  padding: 20px;
  height: 150px;
  color: white;
}
.footer-desc {
  text-align:center;
  margin-top:20px;
  .footer-desc-item{
    display:block;
    color:#3E3E3E;
    font-size:12px;
  }
}
</style>