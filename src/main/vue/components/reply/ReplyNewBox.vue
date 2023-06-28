<template>
  <div class="reply-container">
    <div class="reply-desc">새 댓글</div>
    <div class="reply-content">
      <q-input
          v-model="content"
          filled
          type="textarea"
          :input-style="{resize:'none',height:'100px'}"
          clearable
      />
      <div class="reply-addon">
        <div class="reply-pass">
          <q-input v-model="passwd" type="password" label="댓글 비밀번호" maxlength="10" outlined dense/>
        </div>
        <q-btn outline color="outddo1" label="댓글" @click="createReplyBox"/>
      </div>
    </div>
  </div>
</template>

<script>
import {createReply} from "@/data/api/reply";
import {useQuasar} from "quasar";

export default {
  setup() {
    const $q = useQuasar
    return { $q }
  },
  name: "ReplyNewBox",
  props: ["brdNo"],
  emits:["replyCreated"],
  data() {
    return {
      content: "",
      passwd: ""
    }
  },
  methods : {
    async createReplyBox() {
      const {brdNo, content, passwd} = this

      if(!content) {
        this.$q.notify({
          type: "negative",
          message: "빈 댓글은 달 수 없습니다."
        })
      } else {
        const result = await createReply({brdNo, content, passwd})

        if(result) {
          this.content = ""
          this.passwd = ""
          this.$emit("replyCreated")
        } else {
          alert("댓글 작성 실패")
        }
      }
    }
  }
}
</script>
