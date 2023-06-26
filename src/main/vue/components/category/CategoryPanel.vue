<template>
  <div class="row category-container">
    <div class="col">
      <div class="category-header" style="background: #152E4A">등산</div>
      <div class="category-content">
        <category-item url="/boards?catNo=-2" title="베스트글(등산)" enhanced/>
        <category-item v-for="{catNm, url} in mountainCats" :url="url" :title="catNm"/>
      </div>
    </div>
    <div class="col">
      <div class="category-header" style="background: #9F3434">미스터리</div>
      <div class="category-content">
        <category-item url="/boards?catNo=-4" title="베스트글(미스터리)" enhanced/>
        <category-item v-for="{catNm, url} in mysteryCats" :url="url" :title="catNm"/>
      </div>
    </div>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories"
import CategoryItem from "@/components/category/CategoryItem.vue"

export default {
  name: "CategoryPanel",
  components: {CategoryItem},
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

</style>