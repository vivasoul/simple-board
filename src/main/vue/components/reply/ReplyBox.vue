<template>
  <div class="reply-container">
    <div class="reply-desc">{{reply.updDt}}</div>
    <div class="reply-content">{{reply.content}}</div>
  </div>
</template>

<script>
export default {
  name: "ReplyBox",
  props: {
    reply: Object
  }
}
</script>

<style scoped lang="scss">
@import "@/assets/css/reply.scss";
</style>