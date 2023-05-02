<template>
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-edit-item
        v-for="({fileId, downPath, thumbYn}) in files"
        :key="fileId"
        :file-id="fileId"
        :down-path="downPath"
        :thumb-yn="thumbYn"
        @item-updated="handleThumbUpdate"
        @item-deleted="handleDelete"
    />
  </div>
</template>

<script>
import BoardGalleryEditItem from "@/components/board/BoardGalleryEditItem.vue"
import useBoardDetail from "@/composables/useBoardDetail"

export default {
  name: "BoardGalleryEditor",
  setup() {
    const { files }= useBoardDetail()
    return {
      files
    }
  },
  components: {BoardGalleryEditItem},
  methods: {
    handleThumbUpdate(fileId, thumbYn) {
      this.files.forEach( e => { e["thumbYn"] = "N" })

      if(thumbYn == "Y") {
        const file = this.files.find( e => e.fileId === fileId)
        file.thumbYn = "Y"
      }
    },
    handleDelete(fileId) {
      this.files = this.files.filter( e => e.fileId !== fileId)
    }
  }
}
</script>