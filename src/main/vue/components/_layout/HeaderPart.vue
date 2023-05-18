<template>
  <router-link to="/">
    <div class="header-container">
      OUTDD🌎
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
@media (min-width: 850px) {
  .header-container {
    width: 850px;
    margin:0 auto;
    font-size: 30px;
  }
}
</style>