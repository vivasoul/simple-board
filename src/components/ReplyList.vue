<template>
  <div class="reply-list-title">댓글</div>
  <div class="reply-list">
    <ReplyBox v-for="(item) in replies" :reply="item" :key="item.replNo"/>
  </div>
</template>

<script>
import {getReply} from "@/data/api/reply";
import ReplyBox from "@/components/ReplyBox.vue";

export default {
  name: "ReplyList",
  components: {ReplyBox},
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