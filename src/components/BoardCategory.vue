<template>
  <div class="board-category-item" @click="onCategoryClick">
    {{title}}
  </div>
</template>

<script>
export default {
  name: "BoardCategory",
  props: {
      "width": String,
      "background": {
        "type": String,
        "default": "white"
      },
      "title": String,
      "path": {
        "type": String,
        "default": "/"
      }
  },
  methods:{
    onCategoryClick(){
      this.$router.push(this.path)
    }
  }
}
</script>

<style scoped>
.board-category-item {
  display: inline-block;
  width: v-bind(width);
  height: 90vh;
  color: black;
  background: v-bind(background)
}
</style>