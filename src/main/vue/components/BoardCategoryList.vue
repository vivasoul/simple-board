<template>
  <div class="q-pa-md">
    <BoardCategory v-for="({catNo, catNm, url}) in items"
                   :key="catNo"
                   :title="catNm"
                   :url="url"/>
  </div>
</template>

<script>
import BoardCategory from "@/components/BoardCategory.vue";
import {getCategories} from "@/data/api/category"

export default {
  name: "BoardCategories",
  components: {BoardCategory},
  data() {
    return {
      items: []
    }
  },
  methods: {
    async loadCategories() {
      const data = await getCategories()
      console.log(data)
      this.items = data;
    }
  },
  mounted() {
    this.loadCategories()
  }
}
</script>

<style scoped>

</style>