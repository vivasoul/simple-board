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
    </div>
    <div class="reply-addon">
      <q-btn color="brown-5" label="댓글" @click="createReplyBox"/>
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
      content: ""
    }
  },
  methods : {
    async createReplyBox() {
      const {brdNo, content} = this

      if(!content) {
        this.$q.notify({
          type: "negative",
          message: "빈 댓글은 달 수 없습니다."
        })
      } else {
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
}
</script>
<style scoped lang="scss">
@import "@/assets/css/reply.scss";
</style>