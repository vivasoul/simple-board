<template>
  <q-dialog v-model="show" @hide="handlePopClose">
    <div v-if="shouldChk">
      <q-card>
        <q-card-section class="row items-center">
          <span class="q-ml-sm">
            <q-input v-model="passwd" type="password" label="댓글 비밀번호" maxlength="10" outlined dense/>
          </span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="확인" color="green-7" @click="handleConfirmReqChk"/>
          <q-btn flat label="취소" color="red-7" v-close-popup />
        </q-card-actions>
      </q-card>
    </div>
    <div v-else>
      <q-card>
        <q-card-section class="row items-center">
          <span class="q-ml-sm">해당 댓글을 삭제하시겠습니까?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="삭제" color="green-7" @click="handleConfirmDelete" />
          <q-btn flat label="취소" color="red-7" v-close-popup />
        </q-card-actions>
      </q-card>
    </div>
  </q-dialog>
</template>

<script>
import { checkReply, deleteReply } from "@/data/api/reply";

export default {
  name: "ReplyDelPop",
  props:["open", "brdNo", "replNo"],
  emits:["delPopClose"],
  data() {
    return {
      "show": false,
      "shouldChk": true,
      "passwd": "",
      "deleted": false
    }
  },
  methods: {
    handlePopClose() {
      this.$emit("delPopClose", this.deleted)
    },
    async handleConfirmReqChk() {
      if(!this.passwd) {
        this.$q.notify({
          type: "negative",
          message: "댓글의 비밀번호를 입력하세요."
        })
        return
      }
      const {brdNo, replNo, passwd} = this
      const res = await checkReply({brdNo, replNo, passwd})

      if(res) {
        this.shouldChk = false
      } else {
        this.$q.notify({
          type: "negative",
          message: "댓글 비밀번호가 일치하지 않습니다."
        })
      }
    },
    async handleConfirmDelete() {
      const {brdNo, replNo} = this
      const res = await deleteReply({brdNo, replNo})
      this.deleted = true
      this.show = false
    }
  },
  mounted() {
    this.show = true
  }
}
</script>
