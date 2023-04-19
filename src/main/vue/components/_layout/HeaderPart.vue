<template>
  <router-link to="/">
    <div class="header-container">
      OUTDO🌎RS
    </div>
  </router-link>
</template>

<script>
export default {
  name: "HeaderPart"
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
@media (min-width: 1024px) {
  .header-container {
    width:1024px;
    margin:0 auto;
    font-size: 30px;
  }
}
</style>