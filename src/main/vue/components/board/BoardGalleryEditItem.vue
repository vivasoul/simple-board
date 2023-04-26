<template>
  <div class="gallery-preview-wrapper">
    <q-btn
        color="blue-9"
        icon="menu"
        labe="확대"
        class="board-gallery-menu-btn"
        size="xs"
        dense
        square
        push>
      <q-menu anchor="bottom end" self="top end" transition-duration="0">
        <q-list style="min-width: 100px">
          <q-item clickable v-close-popup @click="handlePopDelete">
            <q-item-section>삭제</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable v-close-popup @click="handleAddImage">
            <q-item-section>본문 추가</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable v-close-popup @click="handleSetThumb">
            <q-item-section>썸네일 {{ thumbYn == "Y" ? "해제" : "지정" }}</q-item-section>
          </q-item>
          <q-separator />
        </q-list>
      </q-menu>
    </q-btn>
    <div v-if="thumbYn === 'Y'" class="gallery-thumb-mark">
      <q-icon name="star" color="amber-9" size="20px"/>
    </div>
    <img class="gallery-preview-item" :src="downPath"/>
  </div>
</template>

<script>
import useContentEditor from "@/composables/useContentEditor"

export default {
  name: "BoardGalleryEditItem",
  emits: ["itemDeleted", "itemUpdated"],
  props: ["fileId", "downPath", "thumbYn"],
  setup() {
    const { insertImage } = useContentEditor()

    return {
      insertImage
    }
  },
  methods: {
    handlePopDelete() {

    },
    handleAddImage() {
      const { downPath } = this
      this.insertImage({downPath})
    },
    handleSetThumb() {
      const thumbYn = this.thumbYn == "Y" ? "N" : "Y"
      this.$emit("itemUpdated", this.fileId, thumbYn)
    }
  }
}
</script>


<style scoped>
.board-gallery-menu-btn {
  display: inline-block;
  position: absolute;
  z-index: 2;
  top: 5px;
  right: 5px;
}
.gallery-thumb-mark {
  display: inline-block;
  position: absolute;
  z-index: 2;
  top: 0px;
  left: 5px;
}
</style>