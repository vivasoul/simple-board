<template>
  <ad-sense
    position="relative"
    :width="width"
    :height="height"
    :slot="adSlot"
    :top="top"
    :right="right"
    :bottom="bottom"
    :left="left"
    :format="format"
  />
</template>

<script>
import AdSense from "@/components/_ads/AdSense.vue"

export default {
  name: "ContentAd",
  components: {AdSense},
  props: ["adSlot", "top", "right", "width", "bottom", "left", "height", "format"]
}
</script>

<style scoped>

</style>