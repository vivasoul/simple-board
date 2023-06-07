<template>
  <ins class="adsbygoogle"
       :style="style"
       data-ad-client="ca-pub-2970146614296196"
       :data-ad-format="adFormat"
       :data-full-width-responsive="isResponsive"
       :data-ad-slot="this.slot">
  </ins>
</template>
<script>
import useAdsByGoogle from "@/composables/useAdsByGoogle"

export default{
  name: "AdSense",
  setup() {
    const {pushAd} = useAdsByGoogle()

    return {
      pushAd
    }
  },
  props:{
    position: {
      type: "String",
      default: "absolute"
    },
    format: {
      type: String,
      default: "auto"
    },
    slot : String,
    width: Number,
    height: Number,
    top: Number,
    right: Number,
    bottom: Number,
    left: Number
  },
  computed: {
    style() {
      const { position, width, height, top, right, bottom, left } = this

      return {
        position,
        display: this.isResponsive ? "block" : "inline-block",
        top,
        right,
        bottom,
        left,
        width,
        height
      }
    },
    adFormat() {
      return this.isResponsive ? this.format : undefined
    },
    isResponsive() {
      return this.width === undefined
    }
  },
  mounted(){
    this.pushAd()
  }
}
</script>