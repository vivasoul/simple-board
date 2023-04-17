<template>
  <div class="board-detail">
    <div class="q-pa-xs">
      <q-input filled v-model="title" label="제목" />
    </div>
    <board-cat-box v-model="catNo" @update:model-value="handleCatNoChange"/>
    <board-content-editor v-model="content" @update:model-value="handleContentChange" />
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
import BoardContentEditor from "@/components/BoardContentEditor.vue"
import { getTinymce } from '@tinymce/tinymce-vue/lib/cjs/main/ts/TinyMCE'
import ImageUploader from "@/components/ImageUploader.vue"
import BoardCatBox from "@/components/BoardCatBox.vue"
import useCategory from "@/composables/useCategory"

export default {
  name: "BoardDetail",
  components: {
    BoardContentEditor,
    BoardCatBox,
    ImageUploader
  },
  setup() {
    const { category } = useCategory();
    console.log(category)
    return {
      category
    }
  },
  data() {
    return {
      title: "",
      content: "",
      catNo: this.category || "",
    }
  },
  methods: {
    async createBoard() {
      const { title, content } = this;
      const catNos = [this.catNo];

      createBoard({ title, content, catNos }).then((result) => {
        if(result) {
          console.log("등록 성공")
          this.goToList()
        } else {
          alert("등록 실패")
        }
      })
    },
    goToList() {
      this.$router.go(-1)
    },
    toggleEdit(editable) {
      this.editable = editable
    },
    handlePreviewClick(imgSrc) {
      getTinymce().activeEditor.insertContent(`<img src="${imgSrc}" />`)
    },
    handleCatNoChange(catNo) {
      this.catNo = catNo
    },
    handleContentChange(content) {
      this.content = content
    }
  },
  mounted() {

  }
}
</script>

<style scoped lang="scss">
@import "@/assets/css/board.scss";
</style>