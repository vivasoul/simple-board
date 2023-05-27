<template>
  <q-chip square @click="handleCategoryClick" clickable :color="category == catNo ? selColor: 'white'">{{title}}</q-chip>
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
        "default": "catchip"
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
  },
  computed: {
    selColor() {
      return this.color || "catchip"
    }
  }
}
</script>

<style scoped>

</style>