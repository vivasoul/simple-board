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

<style scoped>
.reply-container {
  padding:10px;
}
.reply-desc {
  margin: 5px;
  text-align: right;
}
.reply-content {
  margin: 5px;
  border: 1px solid black;
  border-radius: 5px;
  padding: 10px 15px;
  background:#f9f2c075;
}
</style>