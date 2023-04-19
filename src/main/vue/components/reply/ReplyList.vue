<template>
  <div class="reply-list-title">댓글</div>
  <div class="reply-list">
    <reply-box v-for="({updDt, content, replNo, regIp}) in replies"
               :key="replNo"
               :replNo="replNo"
               :ip="regIp"
               :desc="updDt"
               :content="content"
               @req-repl-delete="handleReplDelete"/>
    <reply-new-box :brdNo="brdNo" @reply-created="loadReply"/>
    <reply-del-pop v-if="delopen" :repl-no="delReplNo" :brd-no="brdNo" @del-pop-close="handleDelPopClose"/>
  </div>
</template>

<script>
import {getReply} from "@/data/api/reply";
import ReplyBox from "@/components/reply/ReplyBox.vue";
import ReplyNewBox from "@/components/reply/ReplyNewBox.vue";
import ReplyDelPop from "@/components/reply/ReplyDelPop.vue";

export default {
  name: "ReplyList",
  components: {ReplyDelPop, ReplyBox, ReplyNewBox},
  props: ["brdNo"],
  data() {
    return {
      replies: [],
      delopen: false,
      delReplNo: 0
    }
  },
  methods: {
    async loadReply() {
      this.replies = await getReply(this.brdNo)
    },
    handleReplDelete(replNo) {
      this.delReplNo = replNo
      this.delopen = true
    },
    handleDelPopClose(deleted) {
      this.delopen = false
      if(deleted) this.loadReply()
    }
  },
  mounted() {
    this.loadReply()
  }
}
</script>

<style lang="scss">
@import "@/assets/css/reply.scss";
</style>