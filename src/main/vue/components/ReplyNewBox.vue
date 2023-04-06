<template>
  <div class="reply-container">
    <div class="reply-desc">새 댓글</div>
    <div class="reply-content">
      <textarea v-model="content"></textarea>
    </div>
    <div class="reply-addon">
      <button type="button" @click="onClickCreate">댓글</button>
    </div>
  </div>
</template>

<script>
import {createReply} from "@/data/api/reply";

export default {
  name: "ReplyNewBox",
  props: ["brdNo"],
  emits:["replyCreated"],
  data() {
    return {
      content: ""
    }
  },
  methods : {
    async onClickCreate() {
      const {brdNo, content} = this
      const result = await createReply({brdNo, content})

      if(result) {
        this.content = ""
        this.$emit("replyCreated")
      } else {
        alert("댓글 작성 실패")
      }
    }
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
}
.reply-content textarea{
  padding: 10px 15px;
  width: 100%;
  min-height: 50px;
  border: 1px solid black;
  border-radius: 5px;
  resize: none;
}
.reply-addon {
  text-align: right;
}
</style>