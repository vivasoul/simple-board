<template>
  <q-list padding>
    <q-item v-for="({brdNo, title, catNostr, replyCnt, viewCnt, thumbPath ,regId, timeElapsed}) in boards"
            class="border-item" style="">
      <q-item-section v-if="thumbPath" top thumbnail class="board-thumnail">
        <img :src="thumbPath">
      </q-item-section>

      <q-item-section>
        <q-item-label>
          <div class="board-detail-link" @click="loadBoardDetail(brdNo)">{{ title }}</div>
        </q-item-label>
        <q-item-label caption lines="2">
          <board-cat-sum :cat-nos="makeCatNoList(catNostr)" inline/>
          | {{ regId }} | {{ timeElapsed }}
        </q-item-label>
      </q-item-section>

      <q-item-section side top>
        <q-item-label caption>{{ replyCnt }} 댓글</q-item-label>
        <q-item-label caption>{{ viewCnt }} 조회</q-item-label>
      </q-item-section>
    </q-item>
  </q-list>
  <div class="q-pa-md q-gutter-y-md column items-end">
    <q-btn-group>
      <q-btn color="secondary" label="글쓰기" @click="createBoard"/>
    </q-btn-group>
  </div>
  <board-pagination :max-page="maxPage" @page-changed="handlePageChange"/>
</template>

<script>

import {getBoard} from "@/data/api/board"
import useCategory from "@/composables/useCategory"
import BoardCatSum from "@/components/board/BoardCatSum.vue"
import BoardPagination from "@/components/board/BoardPagination.vue"
import usePagination from "@/composables/usePagination"

const ROW_PER_PAGE = 10

export default {
  name: "BoardList",
  components: {BoardPagination, BoardCatSum},
  props: ["catNo"],
  setup() {
    const {category} = useCategory()
    return {
      category
    }
  },
  data() {
    return {
      boards: [],
      maxPage: 1
    }
  },
  methods: {
    async loadBoards(catNo, curPage) {
      this.category = catNo
      //this.curPage = pageNo
      //this.boards = getBoards(pageNo)
      const {boards, page} = await getBoard({catNo, curPage})
      this.boards = boards
      this.maxPage = page.maxPage
    },
    loadBoardDetail(brdNo) {
      this.$router.push(`/board/${brdNo}`)
    },
    createBoard() {
      this.$router.push("/board-new")
    },
    makeCatNoList(catNos) {
      if (catNos) return catNos.split(",")
      else return null
    },
    handlePageChange(curPage) {
      this.loadBoards(this.catNo, curPage)
    }
  },
  mounted() {
    this.loadBoards(this.catNo, 1)
  },
  updated() {
    this.loadBoards(this.catNo, 1)
  }
}
</script>