<template>
  <div class="reply-container">
    <div class="reply-desc">
      <span class="reply-author">{{replNo}}.익명 <span class="reply-ip">{{ip}}</span></span>
      <span class="reply-date">{{desc}}</span>
    </div>
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
    content: String,
    ip: String
  },
  emits:["reqReplDelete"],
  methods: {
    handleDelPop() {
      this.$emit("reqReplDelete", this.replNo)
    }
  }
}
</script>