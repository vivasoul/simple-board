<template>
  <div :class="enhanced ? 'category-item-enhanced' : ''">
    <router-link :to="url">{{title}}</router-link>
  </div>
</template>

<script>

export default {
  name: "CategoryItem",
  props: {
    "url": {
      type: "Strgin",
      default: "/"
    },
    "title": {
      type: "String",
      default: ""
    },
    "enhanced": Boolean
  },
  methods: {
  }
}
</script>

<style scoped>

</style>