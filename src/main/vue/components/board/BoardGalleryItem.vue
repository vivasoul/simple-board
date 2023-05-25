<template>
  <div class="gallery-view-wrapper">
    <img class="gallery-view-item" :src="'/'+downPath" />
  </div>
</template>

<script>
export default {
  name: "BoardGalleryItem",
  emits: ["itemDeleted", "itemUpdated"],
  props: ["fileId", "downPath", "thumbYn"]
}
</script>