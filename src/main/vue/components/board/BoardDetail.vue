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
      </div>
      <div class="board-content" v-html="content"></div>
    </div>
  </div>
  <div class="q-pa-md q-gutter-y-md column items-end">
    <q-btn-group>
      <q-btn v-if="editable"  style="background:#69D44A;color:white;" label="저장" @click="updateBoard"/>
      <q-btn v-else           style="background:#69D44A;color:white;" label="수정" @click="toggleEdit(true)"/>
      <q-btn                  style="background:#4AD47F;color:white;" label="삭제" @click="deleteBoard"/>
      <q-btn v-if="editable"  style="background:#5DEB6B;color:white;" label="취소" @click="toggleEdit(false)"/>
      <q-btn v-else           style="background:#5DEB6B;color:white;" label="목록" @click="goToList"/>
    </q-btn-group>
  </div>
  <reply-list  v-if="!editable" :brd-no="brdNo" />
<!--  <confirm-modal
      v-if="modalShow"
      :message="deletePopMsg"
      @confirm-accept="handleDelConfirm"
      @confirm-cancel="handleDelCancel"
  />-->
</template>

<script>
import {getBoardDetail, updateBoard, deleteBoard} from "@/data/api/board"
import {useValidation} from "@/composables/useValidation"
import useBoardDetail from "@/composables/useBoardDetail"
import BoardHeadEditor from "@/components/board/BoardHeadEditor.vue"
import BoardContentEditor from "@/components/board/BoardContentEditor.vue"
import BoardCatSum from "@/components/board/BoardCatSum.vue"
import ReplyList from "@/components/reply/ReplyList.vue"

export default {
  name: "BoardDetail",
  components: {
    BoardHeadEditor,
    BoardContentEditor,
    BoardCatSum,
    ReplyList
  },
  props: ["brdNo"],
  setup() {
    const {boardValidator} =  useValidation()
    const { title, catNo, files, content } = useBoardDetail()

    return {
      boardValidator,
      title, catNo, files, content
    }
  },
  data() {
    return {
      editable: false,
      modalShow: false
    }
  },
  methods: {
    async loadBoard() {
      const {title, content, catNos, files} = await getBoardDetail(this.brdNo)
      this.title = title
      this.content = content
      this.files = files
      if(catNos.length) this.catNo = catNos[0]
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
    deleteBoard() {
      this.$q.dialog({
        message: "현재 게시물을 삭제하시겠습니까?",
        cancel: true
      }).onOk(function(){
        deleteBoard(this.brdNo).then(res => {
          if (res) {
            console.log("삭제 성공")
            this.goToList()
          } else {
            alert("삭제 실패")
          }
        })
      }.bind(this))
    },
    goToList() {
      this.$router.go(-1)
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
  }
}
</script>