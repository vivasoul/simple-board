<template>
  <div class="board-detail">
    <div class="board-title">{{ title }}</div>
    <div class="board-content">{{ content }}</div>
  </div>
  <div class="board-detail-addon">
    <button class="board-detail-btn" @click="goToList">목록</button>
  </div>
</template>

<script>
import {getBoard} from "@/database/board";

export default {
  name: "BoardDetail",
  props: ["brdNo"],
  data() {
    return {
      title: "",
      content: ""
    }
  },
  methods: {
    loadBoard() {
      const {title, content} = getBoard(this.brdNo)
      this.title = title
      this.content = content
    },
    goToList() {
      this.$router.push("/")
    }
  },
  mounted() {
    this.loadBoard()
  }
}
</script>

<style scoped>
.board-detail {
  margin: 0 auto;
  width: 100%;
}

.board-title {
  border-bottom: 1px solid black;
  font-size: 15px;
  font-weight: 900;
  line-height: 30px;
  height: 30px;
}

.board-content {
  border-bottom: 1px solid black;
  min-height: 500px;
}

.board-detail-addon {
  text-align:right;
  margin: 20px 10px;
}

.board-detail-btn {

}
</style>