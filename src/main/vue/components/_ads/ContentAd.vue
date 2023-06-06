<template>
  <ad-sense
    position="relative"
    width="100%"
    height="100px"
    :slot="adSlot"
    :top="top"
    :right="right"
    :bottom="bottom"
    :left="left"
  />
</template>

<script>
import AdSense from "@/components/_ads/AdSense.vue"

export default {
  name: "ContentAd",
  components: {AdSense},
  props: ["adSlot", "top", "right", "bottom", "left"]
}
</script>

<style scoped>

</style>