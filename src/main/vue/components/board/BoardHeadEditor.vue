<template>
  <div class="row">
    <div class="col-xs-8 col-md-10">
      <board-title-box/>
      <board-cat-box/>
    </div>
    <div class="col-xs-4 col-md-2">
      <image-uploader-e-x
          :max-size="20"
          @uploaded-add="handleUploadAdd"
          @uploader-close="handleUploadClose"/>
    </div>
  </div>
  <board-gallery-editor/>
</template>

<script>
import BoardTitleBox from "@/components/board/BoardTitleBox.vue"
import BoardCatBox from "@/components/board/BoardCatBox.vue"
import ImageUploaderEX from "@/components/upload/ImageUploaderEX.vue"
import BoardGalleryEditor from "@/components/board/BoardGalleryEditor.vue"
import useBoardDetail from "@/composables/useBoardDetail"
import useContentEditor from "@/composables/useContentEditor"

export default {
  name: "BoardHeadEditor",
  components: {BoardGalleryEditor, ImageUploaderEX, BoardTitleBox, BoardCatBox},
  setup() {
    const { files }= useBoardDetail()
    const { insertImage, insertNewLine, focusLast } = useContentEditor()
    return {
      files,
      insertImage,
      insertNewLine,
      focusLast
    }
  },
  methods: {
    handleUploadAdd(files) {
      files.forEach( e => {
        this.insertImage(e)
        e["thumbYn"] = "N"
      })
      this.insertNewLine()
      this.files = this.files.concat(files)
    },
    handleUploadClose() {
      this.focusLast(true)
    }
  }
}
</script>

<style scoped>

</style>