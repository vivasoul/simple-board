<template>
  <div class="gallery-preview-wrapper">
    <img class="gallery-preview-item" :src="filePath" @click="handleClick"/>
  </div>
</template>

<script>
import useContentEditor from "@/composables/useContentEditor"

export default {
  name: "BoardGalleryEditItem",
  emits: ["itemDeleted", "itemUpdated"],
  props: ["fileId", "filePath", "thumbYn"],
  setup() {
    const { insertImage } = useContentEditor()

    return {
      insertImage
    }
  },
  methods: {
    handleClick() {
      this.insertImage({downFilePath: this.filePath})
    }
  }
}
</script>