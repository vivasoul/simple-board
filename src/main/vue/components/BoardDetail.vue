<template>
  <div class="board-detail">
    <div v-if="editable">
      <div class="q-pa-xs">
        <q-input filled v-model="title" label="게시글 제목"/>
      </div>
      <board-cat-box v-model="catNo" @update:model-value="handleCatNoChange"/>
      <tiny-editor api-key="hos0gsqyxmwl1gdwx91tnhbgkkjcspt61n05og9kwkqrayd6" :init="editorConfig"
                   v-model="editContent"/>
      <image-uploader @preview-click="handlePreviewClick"/>
    </div>
    <div v-else>
      <div class="board-title">{{ title }}</div>
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
import Editor from '@tinymce/tinymce-vue'
import { getTinymce } from '@tinymce/tinymce-vue/lib/cjs/main/ts/TinyMCE'
import ReplyList from "@/components/ReplyList.vue";
import ImageUploader from "@/components/ImageUploader.vue";
import BoardCatBox from "@/components/BoardCatBox.vue"

export default {
  name: "BoardDetail",
  components: {
    ReplyList,
    BoardCatBox,
    ImageUploader,
    "tiny-editor": Editor
  },
  props: ["brdNo"],
  data() {
    return {
      title: "",
      content: "",
      catNo: 0,
      editContent: "",
      editable: false,
      editorConfig: {
        plugins: "lists link image table code wordcount",
        toolbar: "styles | bold italic underline | forecolor backcolor | alignleft aligncenter alignright | bullist numlist | link image table",
        //images_upload_url: "http://localhost",
        menubar: false,
        language: "ko_KR"
      }
    }
  },
  methods: {
    async loadBoard() {
      const {title, content} = await getBoardDetail(this.brdNo)
      this.title = title
      this.content = content
      this.editContent = content
    },
    async updateBoard() {
      const {brdNo, title, editContent} = this;
      this.content = editContent

      updateBoard({brdNo, title, content: this.content}).then((result) => {
        if (result) {
          console.log("수정 성공")
          this.toggleEdit(false)
        } else {
          alert("수정 실패")
        }
      })
    },
    async deleteBoard() {
      deleteBoard(this.brdNo).then((result) => {
        if (result) {
          console.log("삭제 성공")
          this.goToList()
        } else {
          alert("삭제 실패")
        }
      })
    },
    goToList() {
      this.$router.push("/board")
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
      this.catNo = catNo
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