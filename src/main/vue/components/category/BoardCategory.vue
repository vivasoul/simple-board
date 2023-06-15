<template>
  <q-chip square @click="handleCategoryClick" clickable :color="sCatNo == catNo ? selColor: 'white'">{{title}}</q-chip>
</template>

<script>
import useBoardSearch from "@/composables/useBoardSearch"

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
    const { sCatNo, searchCategory } = useBoardSearch()

    return {
      sCatNo,
      searchCategory
    }
  },
  methods:{
    handleCategoryClick(){
      this.searchCategory(this.catNo)
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