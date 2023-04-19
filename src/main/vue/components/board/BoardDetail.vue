<template>
  <div class="board-detail">
    <div v-if="editable">
      <div class="q-pa-xs">
        <q-input filled v-model="title" label="제목"/>
      </div>
      <board-cat-box v-model="catNo" @update:model-value="handleCatNoChange"/>
      <board-content-editor v-model="editContent" @update:model-value="handleContentChange" />
      <image-uploader @preview-click="handlePreviewClick"/>
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
</template>

<script>
import {getBoardDetail, updateBoard, deleteBoard} from "@/data/api/board"
import BoardContentEditor from "@/components/board/BoardContentEditor.vue";
import { getTinymce } from '@tinymce/tinymce-vue/lib/cjs/main/ts/TinyMCE'
import ReplyList from "@/components/reply/ReplyList.vue";
import ImageUploader from "@/components/board/ImageUploader.vue";
import BoardCatBox from "@/components/board/BoardCatBox.vue"
import BoardCatSum from "@/components/board/BoardCatSum.vue";

export default {
  name: "BoardDetail",
  components: {
    BoardContentEditor,
    BoardCatSum,
    ReplyList,
    BoardCatBox,
    ImageUploader
  },
  props: ["brdNo"],
  data() {
    return {
      title: "",
      content: "",
      catNo: undefined,
      editContent: "",
      editable: false
    }
  },
  methods: {
    async loadBoard() {
      const {title, content, catNos} = await getBoardDetail(this.brdNo)
      this.title = title
      this.content = content
      this.editContent = content
      if(catNos.length) this.catNo = catNos[0]
    },
    async updateBoard() {
      const {brdNo, title, editContent} = this;
      this.content = editContent
      const catNos = [this.catNo];

      updateBoard({brdNo, title, content: this.content, catNos}).then((result) => {
        if (result) {
          console.log("수정 성공")
          this.toggleEdit(false)
        } else {
          alert("수정 실패")
        }
      })
    },
    async deleteBoard() {
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
        this.editContent = this.content
      }
    },
    handlePreviewClick(imgSrc) {
      getTinymce().activeEditor.insertContent(`<img src="${imgSrc}" />`)
    },
    handleCatNoChange(catNo) {
      console.log("catNo="+catNo)
      this.catNo = catNo
    },
    handleContentChange(content) {
      this.editContent = content
    }
  },
  mounted() {
    this.loadBoard()
  }
}
</script>

<style scoped lang="scss">
@import "@/assets/css/board.scss";
</style>