<template>
  <div class="q-pa-md category-container">
    <board-category title="전체" url="/boards" cat-no="-1"/>
    <board-category v-for="({catNo, catNm, url, color}) in items"
                   :key="catNo"
                   :cat-no="catNo"
                   :title="catNm"
                   :color="color"
                   :url="url"/>
  </div>
</template>

<script>
import BoardCategory from "@/components/category/BoardCategory.vue";
import {getCategories} from "@/data/api/category"
import useCategory from "@/composables/useCategory";
import useCategories from "@/composables/useCategories";

export default {
  name: "BoardCategories",
  components: {BoardCategory},
  setup(){
    const { categories }= useCategories()
    return {
      items : categories
    }
  },
  data() {
    return {
      //items: []
    }
  },
  methods: {
    async loadCategories() {
      //const data = await getCategories()
      //console.log(data)
      //this.items = data;
    }
  },
  mounted() {
    //this.loadCategories()
  }
}
</script>

<style scoped>
.category-container {
  margin: 10px 0px;
  /*background-color:#81C4B6;*/
  background-color:#6C785F;
}
</style>