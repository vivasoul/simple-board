<template>
  <q-btn
      style="background:#69D44A;color:white;"
      label="수정"
      @click="handlePassOpen"/>
  <board-pass-modal
      v-if="passModalShow"
      :brd-no="brdNo"
      @pass-checked="handleConfirm"
      @modal-close="handlePassClose"
  />
</template>

<script>
import BoardPassModal from "@/components/board/modal/BoardPassModal.vue"

export default {
  name: "BoardUpdateBtn",
  components: {BoardPassModal},
  props:["brdNo"],
  emits:["updateModeConfirmed"],
  data() {
    return {
      passModalShow: false
    }
  },
  methods: {
    handlePassOpen() {
      this.passModalShow = true
    },
    handlePassClose() {
      this.passModalShow = false
    },
    handleConfirm() {
      this.$emit("updateModeConfirmed")
    }
  }
}
</script>

<style scoped>

</style>