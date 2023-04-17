<template>
    <q-list padding>
      <q-item v-for="({brdNo, title}) in boards" class="border-item"  style="">
        <q-item-section>
          <q-item-label>
            <div class="board-detail-link" @click="loadBoardDetail(brdNo)">{{title}}</div>
          </q-item-label>
          <q-item-label caption lines="2">내용 미리보기 들어갈 곳</q-item-label>
        </q-item-section>

        <q-item-section side top>
          <q-item-label caption>게시글번호:  {{brdNo}}</q-item-label>
          <q-icon name="star" color="yellow"></q-icon>
        </q-item-section>
      </q-item>
    </q-list>
  <div class="q-pa-md q-gutter-y-md column items-end">
    <q-btn-group>
      <q-btn color="secondary" label="신규" @click="createBoard"/>
    </q-btn-group>
  </div>
<!--    <Pagination :curPage="curPage" :maxPage="maxPage" @page-click="loadBoards"/>-->
</template>

<script>
//import Pagination from "@/components/Pagination.vue";
import {getBoard } from "@/data/api/board"
import useCategory from "@/composables/useCategory";

const ROW_PER_PAGE = 10

export default {
  name: "BoardList",
  //components: {Pagination},
  props:["catNo"],
  setup() {
    const { category } = useCategory()
    return {
      category
    }
  },
  data() {
    return {
      curPage: 1,
      boards: []
    }
  },
  methods: {
    async loadBoards(catNo) {
      this.category = catNo
      //this.curPage = pageNo
      //this.boards = getBoards(pageNo)
      this.boards = await getBoard(catNo)
    },
    loadBoardDetail(brdNo) {
      this.$router.push(`/board/${brdNo}`)
    },
    createBoard() {
      this.$router.push("/board-new")
    },
  },
  computed: {
/*    maxPage() {
      const totCnt = getTotCount() || 0;
      const maxPage= Math.ceil(totCnt / ROW_PER_PAGE);
      return maxPage
    }*/
  },
  mounted() {
    this.loadBoards(this.catNo)
  },
  updated() {
    this.loadBoards(this.catNo)
  }
}
</script>

<style scoped lang="scss">
/*.board-list {
  padding: 20px 10px;
  border-bottom: 1px solid black;
}*/
.border-item {
  border-bottom:1px solid $border-grey;
}
.board-list-addon {
  text-align: right;
}
.board-detail-link:hover{
  text-decoration: underline;
  cursor:pointer;
}
</style>