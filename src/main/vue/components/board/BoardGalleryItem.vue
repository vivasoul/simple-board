<template>
  <div class="gallery-view-wrapper">
    <img class="gallery-view-item" :src="filePath" />
  </div>
</template>

<script>
export default {
  name: "BoardGalleryItem",
  emits: ["itemDeleted", "itemUpdated"],
  props: ["fileId", "filePath", "thumbYn"]
}
</script>