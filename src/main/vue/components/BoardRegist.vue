<template>
  <div class="board-detail">
    <div class="q-pa-xs">
      <q-input filled v-model="title" label="게시글 제목" />
    </div>
    <tiny-editor api-key="hos0gsqyxmwl1gdwx91tnhbgkkjcspt61n05og9kwkqrayd6" :init="editorConfig"
                 v-model="content"/>
    <image-uploader @preview-click="handlePreviewClick"/>
  </div>
  <div class="q-pa-md q-gutter-y-md column items-end">
    <q-btn-group>
      <q-btn style="background:#69D44A;color:white;" label="등록" @click="createBoard"/>
      <q-btn style="background:#5DEB6B;color:white;" label="취소" @click="goToList"/>
    </q-btn-group>
  </div>
<!--  <div class="board-detail-addon">
    <button class="board-create-btn" @click="createBoard">등록</button>
    <button class="board-detail-btn" @click="goToList">취소</button>
  </div>-->
</template>

<script>
import {createBoard} from "@/data/api/board"
import Editor from '@tinymce/tinymce-vue'
import { getTinymce } from '@tinymce/tinymce-vue/lib/cjs/main/ts/TinyMCE'
import ImageUploader from "@/components/ImageUploader.vue";

export default {
  name: "BoardDetail",
  components: {
    ImageUploader,
    "tiny-editor": Editor
  },
  data() {
    return {
      title: "",
      content: "",
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
    async createBoard() {
      const { title, content } = this;

      createBoard({ title, content }).then((result) => {
        if(result) {
          console.log("등록 성공")
          this.goToList()
        } else {
          alert("등록 실패")
        }
      })
    },
    goToList() {
      this.$router.push("/")
    },
    toggleEdit(editable) {
      this.editable = editable
    },
    handlePreviewClick(imgSrc) {
      getTinymce().activeEditor.insertContent(`<img src="${imgSrc}" />`)
    }
  },
  mounted() {

  }
}
</script>

<style scoped lang="scss">
@import "@/assets/css/board.scss";
</style>