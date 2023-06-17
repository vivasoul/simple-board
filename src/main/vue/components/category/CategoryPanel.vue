<template>
  <div class="row category-container">
    <div class="col">
      <div class="category-header" style="background: #91A27A">⛺등산</div>
      <div class="category-content">
        <div>전체글(등산)</div>
        <div v-for="{catNm, url} in mountainCats"><router-link :to="url">{{catNm}}</router-link></div>
      </div>
    </div>
    <div class="col">
      <div class="category-header" style="background: crimson">😱미스터리</div>
      <div class="category-content">
        <div v-for="{catNm, url} in mysteryCats"><router-link :to="url">{{catNm}}</router-link></div>
      </div>
    </div>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories"

export default {
  name: "CategoryPanel",
  setup() {
    const { mountainCats, mysteryCats } = useCategories()

    return {
      mountainCats,
      mysteryCats
    }
  }
}
</script>

<style scoped>
.category-container {
  border: 1px solid #AEAEAE;
}
.category-container>div:first-child {
  border-right: 1px solid #AEAEAE;
}
.category-header {
  padding: 5px 10px;
  border-bottom: 1px solid #AEAEAE;
  text-align: center;
  color:white;
  font-size:14px;
  font-weight: 900;
}
.category-content {
  padding: 10px;
  font-size:13px;
  line-height:25px;
}
.category-content a {
  color: #333;
  text-decoration: none;
}
.category-content a:hover {
  color: #333;
}
</style>