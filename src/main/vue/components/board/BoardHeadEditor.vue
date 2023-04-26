<template>
  <div class="row">
    <div class="col-xs-8 col-md-10">
      <board-title-box/>
      <board-cat-box/>
    </div>
    <div class="col-xs-4 col-md-2">
      <image-uploader-e-x @uploaded-add="handleUploadAdd"/>
    </div>
  </div>
  <board-gallery-editor :list="files"/>
</template>

<script>
import BoardTitleBox from "@/components/board/BoardTitleBox.vue"
import BoardCatBox from "@/components/board/BoardCatBox.vue"
import ImageUploaderEX from "@/components/upload/ImageUploaderEX.vue"
import BoardGalleryEditor from "@/components/board/BoardGalleryEditor.vue"
import useBoardDetail from "@/composables/useBoardDetail"
import {getTinymce} from "@tinymce/tinymce-vue/lib/es2015/main/ts/TinyMCE"
import useContentEditor from "@/composables/useContentEditor"

export default {
  name: "BoardHeadEditor",
  components: {BoardGalleryEditor, ImageUploaderEX, BoardTitleBox, BoardCatBox},
  setup() {
    const { files }= useBoardDetail()
    const { insertImage } = useContentEditor()
    return {
      files,
      insertImage
    }
  },
  methods: {
    handleUploadAdd(files) {
      files.forEach( e => {
        this.insertImage(e)
        e["thumbYn"] = "N"
      })
      this.files = this.files.concat(files)
    }
  }
}
</script>

<style scoped>

</style>