<template>
  <div :style="{ display: inline ? 'inline-block' : 'block' }">
    <q-badge v-for="(obj, idx) in boardCats" :key="idx" color="catsum">{{obj ? obj.catNm : ""}}</q-badge>
  </div>
</template>

<script>
import useCategories from "@/composables/useCategories";

export default {
  name: "BoardCatSum",
  props: {
    catNos: {
      type: Array,
      default: []
    },
    inline: Boolean
  },
  setup() {
    const { categoryMap } = useCategories()
    return {
      catMap: categoryMap
    }
  },
  computed: {
    boardCats() {
      const cMap = this.catMap
      return this.catNos.map( e => cMap[e] )
    }
  }
}
</script>

<style scoped>
.bg-catsum {
  background-color: #4a8595;
}
</style>