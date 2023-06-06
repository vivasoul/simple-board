<template>
  <ad-sense
    class="outddo-side-ad"
    width="200px"
    height="700px"
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
      return window.document.body.clientWidth > 1300
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