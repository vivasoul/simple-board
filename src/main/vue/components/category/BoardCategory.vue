<template>
  <q-chip square @click="handleCategoryClick" clickable :color="category == catNo ? 'catchip': 'white'">{{title}}</q-chip>
</template>

<script>
import useCategory from "@/composables/useCategory";

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
      },
      "catNo": String
  },
  setup() {
    const { category } = useCategory()

    return {
      category
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