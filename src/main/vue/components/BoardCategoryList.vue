<template>
  <div class="board-category-list">
    <BoardCategory v-for="({title, background, path}) in items"
                   :key="title"
                   :width="itemWidth"
                   :title="title"
                   :path="path"
                   :background="background"/>
  </div>
</template>

<script>
import BoardCategory from "@/components/BoardCategory.vue";

export default {
  name: "BoardCategories",
  components: {BoardCategory},
  data() {
    return {
      items: [
        {"title": "등산0",     "path": "/board", "background": "red"},
        {"title": "낚시0",     "path": "/board", "background": "blue"},
        {"title": "캠핑1",     "path": "/board", "background": "yellow"},
        {"title": "액티비티1",  "path": "/board", "background": "green"},
        {"title": "유머12",     "path": "/board", "background": "white"}
      ]
    }
  },
  computed: {
    itemWidth() {
      const len = this.items.length
      if(len > 0) {
        return (100/len) + "%"
      } else {
        return "0"
      }
    }
  }
}
</script>

<style scoped>

</style>