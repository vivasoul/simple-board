<template>
  <div class="board-detail">
    <div v-if="editable">
      <div>
        <input class="board-title" type="text" v-model="title"/>
      </div>
      <tiny-editor api-key="hos0gsqyxmwl1gdwx91tnhbgkkjcspt61n05og9kwkqrayd6" :init="editorConfig"
                   v-model="content"/>
    </div>
    <div v-else>
      <div class="board-title">{{ title }}</div>
      <div class="board-content" v-html="content"></div>
    </div>
  </div>
  <div class="board-detail-addon">
    <button v-if="editable" class="board-update-btn" @click="updateBoard">저장</button>
    <button v-else class="board-update-btn" @click="toggleEdit(true)">수정</button>
    <button class="board-delete-btn" @click="deleteBoard">삭제</button>
    <button class="board-detail-btn" @click="goToList">목록</button>
  </div>
</template>

<script>
import {getBoard} from "@/data/mock/board"
import {getBoardDetail, updateBoard, deleteBoard} from "@/data/api/board"
import Editor from '@tinymce/tinymce-vue'

export default {
  name: "BoardDetail",
  components: {
    "tiny-editor": Editor
  },
  props: ["brdNo"],
  data() {
    return {
      title: "",
      content: "",
      editable: false,
      editorConfig: {
        plugins: "lists link image table code wordcount",
        toolbar: "styles | bold italic underline | forecolor backcolor | alignleft aligncenter alignright | bullist numlist | link image table",
        images_upload_url: "http://localhost",
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
    },
    async updateBoard() {
      const {brdNo, title, content} = this;
      updateBoard({brdNo, title, content}).then((result) => {
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
      this.$router.push("/")
    },
    toggleEdit(editable) {
      this.editable = editable
    }
  },
  mounted() {
    this.loadBoard()
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
  width: 100%;
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