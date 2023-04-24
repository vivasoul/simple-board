<template>
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-item
        v-for="({fileId, filePath, thumbYn}) in list"
        :key="fileId"
        :file-id="fileId"
        :file-path="filePath"
        :thumb-yn="thumbYn"
    />
  </div>
</template>

<script>
import BoardGalleryItem from "@/components/board/BoardGalleryItem.vue"

export default {
  name: "BoardGallery",
  components: {BoardGalleryItem},
  props: {
    "list": {
      "type": Array,
      "default": []
    }
  }
}
</script>

<style scoped>

</style>