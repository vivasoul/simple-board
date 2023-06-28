<template>
  <q-btn
      outline color="outddo2"
      label="삭제"
      @click="handlePassOpen"/>
  <board-pass-modal
      v-if="passModalShow"
      :brd-no="brdNo"
      @pass-checked="handleDeleteOpen"
      @modal-close="handlePassClose"
  />
  <confirm-modal
      v-if="deleteModalShow"
      message="게시물을 삭제하시겠습니까?"
      @confirm-accept="handleConfirm"
      @confirm-close="handleDeleteClose"
  />
</template>

<script>
import BoardPassModal from "@/components/board/modal/BoardPassModal.vue"

export default {
  name: "BoardDeleteBtn",
  components: {BoardPassModal},
  props:["brdNo"],
  emits:["deleteConfirmed"],
  data() {
    return {
      passModalShow: false,
      deleteModalShow: false
    }
  },
  methods: {
    handlePassOpen() {
      this.passModalShow = true
    },
    handlePassClose() {
      this.passModalShow = false
    },
    handleDeleteOpen() {
      this.deleteModalShow = true
    },
    handleDeleteClose() {
      this.deleteModalShow = false
    },
    handleConfirm() {
      this.$emit("deleteConfirmed", this.brdNo)
    }
  }
}
</script>

<style scoped>

</style>