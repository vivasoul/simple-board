<template>
  <q-chip square @click="handleCategoryClick" clickable>{{title}}</q-chip>
</template>

<script>
export default {
  name: "BoardCategory",
  props: {
      "title": String,
      "icon": String,
      "color": {
        "type": String,
        "default": "white"
      },
      "url": {
        "type": String,
        "default": "/"
      }
  },
  methods:{
    handleCategoryClick(){
      this.$router.push(this.url)
    }
  }
}
</script>

<style scoped>

</style>