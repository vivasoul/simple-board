<template>
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-item
        v-for="({fileId, downPath, thumbYn}) in list"
        :key="fileId"
        :file-id="fileId"
        :down-path="downPath"
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
