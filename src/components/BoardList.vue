<template>
    <div class="board-list" v-for="({brdNo, title}) in boards" @click="loadBoardDetail(brdNo)">
      {{ brdNo }} {{ title }}
    </div>
    <Pagination :curPage="curPage" :maxPage="maxPage" @page-click="loadBoards"/>
</template>

<script>
import {getBoards, getTotCount} from "@/data/mock/board";
import Pagination from "@/components/Pagination.vue";

import {getBoard as getBoard2 } from "@/data/api/board"

const ROW_PER_PAGE = 10

export default {
  name: "BoardList",
  components: {Pagination},
  data() {
    return {
      curPage: 1,
      boards: []
    }
  },
  methods: {
    async loadBoards(pageNo) {
      this.curPage = pageNo
      //this.boards = getBoards(pageNo)
      this.boards = await getBoard2(pageNo)
    },
    loadBoardDetail(brdNo) {
      this.$router.push(`/board/${brdNo}`)
    }
  },
  computed: {
    maxPage() {
      const totCnt = getTotCount() || 0;
      const maxPage= Math.ceil(totCnt / ROW_PER_PAGE);
      return maxPage
    }
  },
  mounted() {
   this.loadBoards(this.curPage)
  }
}
</script>

<style scoped>
.board-list {
  padding: 20px 10px;
  border-bottom: 1px solid black;
}
</style>