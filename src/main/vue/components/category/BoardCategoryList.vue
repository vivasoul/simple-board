<template>
  <div class="q-pa-md">
    <board-category v-for="({catNo, catNm, url}) in items"
                   :key="catNo"
                   :catNo="catNo"
                   :title="catNm"
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

</style>