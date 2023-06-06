<template>
  <ins class="adsbygoogle"
       :style="style"
       data-ad-client="ca-pub-2970146614296196"
       data-ad-format="auto"
       data-full-width-responsive="true"
       :data-ad-slot="this.slot">
  </ins>
</template>
<script>
export default{
  name: "AdSense",
  props:{
    position: {
      type: "String",
      default: "absolute"
    },
    slot : String,
    width: Number,
    height: Number,
    top: Number,
    right: Number,
    bottom: Number,
    left: Number
  },
  data() {
    return {
      adsbygoogle: null
    }
  },
  computed: {
    style() {
      const { position, width, height, top, right, bottom, left } = this
      const st = {
        position,
        display: "inline-block",
        width,
        height
      }
      if(top) st["top"] = top
      if(right) st["right"] = right
      if(bottom) st["bottom"] = bottom
      if(left) st["left"] = left

      return st
    }
  },
  mounted(){
    (this.adsbygoogle = window.adsbygoogle || []).push({});
  }
}
</script>