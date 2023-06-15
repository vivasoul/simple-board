<template>
  <ad-sense
    v-if="isShow"
    class="outddo-side-ad"
    width="160px"
    height="600px"
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
  name: "SideAd",
  components: {AdSense},
  props: ["adSlot", "top", "right", "bottom", "left"],
  methods: {
    isShow() {
      return !this.$isMobile()
    }
  }
}
</script>

<style scoped>
@media(max-width: 1300px) {
  .outddo-side-ad {
    display:none !important;
  }
}
</style>