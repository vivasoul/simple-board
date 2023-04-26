<template>
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-edit-item
        v-for="({fileId, downPath, thumbYn}) in list"
        :key="fileId"
        :file-id="fileId"
        :down-path="downPath"
        :thumb-yn="thumbYn"
        @item-updated="handleThumbUpdate"
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
  },
  methods: {
    handleThumbUpdate(fileId, thumbYn) {
      this.list.forEach( e => { e["thumbYn"] = "N" })

      if(thumbYn == "Y") {
        const file = this.list.find( e => e.fileId === fileId)
        file.thumbYn = "Y"
      }
    }
  }
}
</script>