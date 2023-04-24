<template>
  <div class="gallery-preview-wrapper">
    <img class="gallery-preview-item" :src="filePath" />
  </div>
</template>

<script>
export default {
  name: "BoardGalleryItem",
  emits: ["itemDeleted", "itemUpdated"],
  props: ["fileId", "filePath", "thumbYn"]
}
</script>