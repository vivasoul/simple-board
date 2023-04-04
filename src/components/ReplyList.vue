<template>
  <div class="reply-list-title">댓글</div>
  <div class="reply-list">
    <ReplyBox v-for="(item) in replies" :reply="item" :key="item.replNo"/>
    <ReplyNewBox :brdNo="brdNo" @reply-created="loadReply"/>
  </div>
</template>

<script>
import {getReply} from "@/data/api/reply";
import ReplyBox from "@/components/ReplyBox.vue";
import ReplyNewBox from "@/components/ReplyNewBox.vue";

export default {
  name: "ReplyList",
  components: {ReplyBox, ReplyNewBox},
  props: ["brdNo"],
  data() {
    return {
      replies: []
    }
  },
  methods: {
    async loadReply() {
      this.replies = await getReply(this.brdNo)
    }
  },
  mounted() {
    this.loadReply()
  }
}
</script>

<style scoped>
.reply-list-title {
  padding: 10px 20px 0px 20px;
  font-size: 15px;
  font-weight: 700;
}
.reply-list {
  margin: 10px 20px;
}
</style>