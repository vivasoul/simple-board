<template>
  <div class="reply-container">
    <div class="reply-desc">{{desc}}</div>
    <div class="reply-content">{{content}}</div>
    <div class="reply-del-btn" @click="handleDelPop">x</div>
  </div>
</template>

<script>
export default {
  name: "ReplyBox",
  props: {
    replNo: Number,
    desc: String,
    content: String
  },
  emits:["reqReplDelete"],
  methods: {
    handleDelPop() {
      console.log("---")
      this.$emit("reqReplDelete", this.replNo)
    }
  }
}
</script>

<style scoped lang="scss">
@import "@/assets/css/reply.scss";
</style>