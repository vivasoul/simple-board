<template>
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-edit-item
        v-for="({fileId, filePath, downFilePath, thumbYn}) in list"
        :key="fileId"
        :file-id="fileId"
        :file-path="downFilePath || filePath"
        :thumb-yn="thumbYn"
    />
  </div>
</template>

<script>
import BoardGalleryEditItem from "@/components/board/BoardGalleryEditItem.vue"

export default {
  name: "BoardGalleryEditor",
  components: {BoardGalleryEditItem},
  props: {
    "list": {
      "type": Array,
      "default": []
    }
  }
}
</script>