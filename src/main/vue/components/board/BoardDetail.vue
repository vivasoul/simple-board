<template>
  <div class="board-detail">
    <div v-if="editable">
      <board-head-editor/>
      <board-content-editor/>
    </div>
    <div v-else>
      <div class="board-title">
        {{ title }}
        <board-cat-sum :cat-nos="[catNo]" inline />
        <board-title-caption
          :reg-dt="regDt"
          :reg-id="regId"
          :view-cnt="viewCnt"
          :reply-cnt="replyCnt"
        />
      </div>
      <div class="board-content" v-html="content"></div>
    </div>
  </div>
  <div class="q-pa-md q-gutter-y-md column items-end board-addon">
    <q-btn-group>
      <q-btn v-if="editable"  outline color="outddo1" label="저장" @click="updateBoard"/>
      <board-update-btn v-else :brd-no="brdNo" @update-mode-confirmed="toggleEdit(true)"/>
<!--      <q-btn v-else           style="background:#69D44A;color:white;" label="수정" @click="toggleEdit(true)"/>-->
<!--      <q-btn                  style="background:#4AD47F;color:white;" label="삭제" @click="deleteBoard"/>-->
      <board-delete-btn :brd-no="brdNo" @delete-confirmed="deleteBoard"/>
      <q-btn v-if="editable"  outline color="outddo2" label="취소" @click="toggleEdit(false)"/>
      <q-btn v-else           outline color="outddo1" label="목록" @click="goToList"/>
    </q-btn-group>
  </div>
<!--  <content-ad
      ad-slot="1603196751"
  />-->
  <reply-list  v-if="!editable" :brd-no="brdNo" />
</template>

<script>
import {getBoardDetail, updateBoard, deleteBoard} from "@/data/api/board"
import {useValidation} from "@/composables/useValidation"
import useBoardDetail from "@/composables/useBoardDetail"
import BoardHeadEditor from "@/components/board/BoardHeadEditor.vue"
import BoardContentEditor from "@/components/board/BoardContentEditor.vue"
import BoardCatSum from "@/components/board/BoardCatSum.vue"
import ReplyList from "@/components/reply/ReplyList.vue"
import BoardTitleCaption from "@/components/board/BoardTitleCaption.vue"
import BoardUpdateBtn from "@/components/board/button/BoardUpdateBtn.vue"
import BoardDeleteBtn from "@/components/board/button/BoardDeleteBtn.vue"
import ContentAd from "@/components/_ads/ContentAd.vue"
import useBoardSearch from "@/composables/useBoardSearch"

export default {
  name: "BoardDetail",
  components: {
    ContentAd,
    BoardDeleteBtn,
    BoardUpdateBtn,
    BoardTitleCaption,
    BoardHeadEditor,
    BoardContentEditor,
    BoardCatSum,
    ReplyList
  },
  props: ["brdNo"],
  setup() {
    const {boardValidator} =  useValidation()
    const { title, catNo, files, content, init, goToList  } = useBoardDetail()
    const { sCatNo } = useBoardSearch()

    return {
      boardValidator,
      title, catNo, files, content,
      goToList,
      initDetail: init,
      sCatNo
    }
  },
  data() {
    return {
      editable: false,
      modalShow: false,
      regId: "",
      regDt: "",
      viewCnt: 0,
      replyCnt: 0
    }
  },
  methods: {
    async loadBoard() {
      const {title, content, catNos, files, regId, regDt, viewCnt, replyCnt} = await getBoardDetail(this.brdNo)
      this.title = title
      this.content = content
      this.files = files

      const _catNo = catNos[0];
      if(catNos.length) {
        this.catNo = _catNo
        this.sCatNo = _catNo
      }

      this.regId = regId
      this.regDt = regDt
      this.viewCnt = viewCnt
      this.replyCnt = replyCnt
    },
    async updateBoard() {
      const {brdNo, title, content, catNo} = this;
      const catNos = [catNo];
      const files = this.files.map(({fileId, thumbId, thumbYn}) => ({fileId, thumbId, thumbYn}))

      if(this.boardValidator({title, catNo, content})) {

        updateBoard({brdNo, title, content, catNos, files}).then((result) => {
          if (result) {
            console.log("수정 성공")
            this.toggleEdit(false)
          } else {
            alert("수정 실패")
          }
        })

      }
    },
    deleteBoard(brdNo) {
      deleteBoard(brdNo).then(res => {
        if (res) {
          console.log("삭제 성공")
          this.goToList()
        } else {
          alert("삭제 실패")
        }
      })
    },
    toggleEdit(editable) {
      this.editable = editable
      if(!editable) {
        this.loadBoard()
      }
    }
  },
  mounted() {
    this.loadBoard()
  },
  unmounted() {
    this.initDetail(null)
    this.sCatNo = -1
  }
}
</script>