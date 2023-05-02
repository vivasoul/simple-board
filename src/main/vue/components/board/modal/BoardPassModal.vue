<template>
  <q-dialog v-model="modalShow" @hide="handleClose">
    <q-card>
      <q-card-section class="row items-center">
            <span class="q-ml-sm">
              <q-input v-model="passwd" type="password" label="비밀번호" maxlength="10" outlined dense/>
            </span>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="확인" color="green-7" @click="handleConfirmReqChk"/>
        <q-btn flat label="취소" color="red-7" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import {checkBoard} from "@/data/api/board"

export default {
  name: "BoardPassModal",
  props: ["brdNo"],
  emits: ["passChecked", "modalClose"],
  data() {
    return {
      passwd: "",
      modalShow: true
    }
  },
  methods: {
    async handleConfirmReqChk() {
      if(!this.passwd) {
        this.$q.notify({
          type: "negative",
          message: "게시글 비밀번호를 입력하세요."
        })
        return
      }
      const {brdNo, passwd} = this
      const res = await checkBoard({brdNo, passwd})

      if(res) {
        this.modalShow = false
        this.$emit("passChecked", res)
      } else {
        this.$q.notify({
          type: "negative",
          message: "게시글 비밀번호가 일치하지 않습니다."
        })
      }
    },
    handleClose() {
      this.$emit("modalClose")
    }
  }
}
</script>

<style scoped>

</style>