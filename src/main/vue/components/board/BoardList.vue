<template>
  <div class="board-empty-text" v-if="!boards || !boards.length">
    해당 카테고리의 게시물이 없습니다.
  </div>
  <q-list v-else padding>
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
<!--        <q-item-label caption>{{ viewCnt }} 조회</q-item-label>-->
      </q-item-section>
    </q-item>
  </q-list>
<!--  <content-ad
      ad-slot="1894022555"
  />-->
  <div class="q-pa-md q-gutter-y-md column items-end" v-if="catNo > 0">
    <q-btn-group>
      <q-btn color="secondary" label="글쓰기" @click="createBoard"/>
    </q-btn-group>
  </div>
  <board-search-box />
  <board-pagination />
</template>

<script>

import BoardCatSum from "@/components/board/BoardCatSum.vue"
import BoardPagination from "@/components/board/BoardPagination.vue"
import BoardSearchBox from "@/components/board/BoardSearchBox.vue"
import {useMeta} from "quasar"
import useBoardSearch from "@/composables/useBoardSearch"
import ContentAd from "@/components/_ads/ContentAd.vue"

const ROW_PER_PAGE = 10

export default {
  name: "BoardList",
  components: {ContentAd, BoardSearchBox, BoardPagination, BoardCatSum},
  setup() {
    const { searchCategory, searchText, searchPage, boards } = useBoardSearch()

    return {
      searchCategory,
      searchText,
      searchPage,
      boards
    }
  },
  props: {
    "catNo": {
      type: "String",
      default: "-1"
    }
  },
  methods: {
    loadBoardDetail(brdNo) {
      this.$router.push(`/boards/${brdNo}`)
    },
    createBoard() {
      this.$router.push("/boards-new")
    },
    makeCatNoList(catNos) {
      if (catNos) return catNos.split(",")
      else return null
    },
    isEmpty() {
      return !this.boards || !this.boards.length
    }
  },
  mounted() {
    this.searchCategory(this.catNo)
  },
  unmounted() {
    this.boards = []
  }
}
</script>