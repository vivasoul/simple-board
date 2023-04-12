<template>
  <div class="board-detail">
    <div>
      <input class="board-title" type="text" v-model="title" />
    </div>
    <tiny-editor api-key="hos0gsqyxmwl1gdwx91tnhbgkkjcspt61n05og9kwkqrayd6" :init="editorConfig"
                 v-model="content"/>
    <image-uploader @preview-click="handlePreviewClick"/>
  </div>
  <div class="board-detail-addon">
    <button class="board-create-btn" @click="createBoard">등록</button>
    <button class="board-detail-btn" @click="goToList">취소</button>
  </div>
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

<style scoped>
.board-detail {
  margin: 0 auto;
  width: 100%;
}

.board-title {
  border-bottom: 1px solid black;
  width:100%;
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
  text-align: right;
  margin: 20px 10px;
}

.board-detail-btn {

}
</style>