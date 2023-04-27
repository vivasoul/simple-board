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
  <confirm-modal
      v-if="modalShow"
      :message="deletePopMsg"
      @confirm-accept="handleDelConfirm"
      @confirm-cancel="handleDelCancel"
  />
</template>

<script>
import useContentEditor from "@/composables/useContentEditor"
import {deleteFile} from "@/data/api/file"
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
  data() {
    return {
      "modalShow": false,
      "deletePopMsg": "해당 이미지를 삭제하시겠습니까?<br/><div class='confirm-caption warning'>(* 본문에 해당 이미지가 추가된 경우,<br/>삭제 후에는 출력되지 않습니다.)</div>"
    }
  },
  methods: {
    handlePopDelete() {
      if(this.thumbYn == "Y") {
        this.$q.notify({
          type: "negative",
          message: "썸네일로 지정된 이미지는 삭제할 수 없습니다."
        })
      } else {
        this.modalShow = true
      }
    },
    async handleDelConfirm() {
      this.modalShow = false
      const { fileId } = this
      const res = await deleteFile(fileId)

      if(res) {
        this.$emit("itemDeleted", fileId)
      }
    },
    handleDelCancel() {
      this.modalShow = false
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