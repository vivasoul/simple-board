<template>
  <div class="board-detail">
    <board-head-editor/>
    <board-content-editor/>
    <div class="q-pa-md q-gutter-y-md board-regist-addon">
<!--      <q-input v-model="regId" type="text" label="등록 아이디" maxlength="10" outlined dense/>-->
      <q-input v-model="passwd" type="password" label="수정 비밀번호" maxlength="20" outlined dense/>
    </div>
  </div>
  <div class="q-pa-md q-gutter-y-md column items-end board-addon">
    <q-btn-group>
      <q-btn outline color="outddo1" label="등록" @click="createBoard"/>
      <q-btn outline color="outddo2" label="취소" @click="goToList"/>
    </q-btn-group>
  </div>
</template>

<script>
import {createBoard} from "@/data/api/board"
import useBoardDetail from "@/composables/useBoardDetail"
import {useValidation} from "@/composables/useValidation"
import BoardHeadEditor from "@/components/board/BoardHeadEditor.vue"
import BoardContentEditor from "@/components/board/BoardContentEditor.vue"
import useBoardSearch from "@/composables/useBoardSearch"

export default {
  name: "BoardDetail",
  components: {
    BoardHeadEditor,
    BoardContentEditor
  },
  setup() {
    const { sCatNo } = useBoardSearch()
    const { boardValidator } =  useValidation()
    const { title, catNo, files, content, goToList } = useBoardDetail({catNo: sCatNo.value})

    return {
      sCatNo,
      boardValidator,
      title, catNo, files, content,
      goToList
    }
  },
  data() {
    return {
      regId: "",
      passwd: ""
    }
  },
  methods: {
    async createBoard() {
      const { title, content, catNo, regId, passwd } = this;
      const catNos = [catNo];
      const files = this.files.map(({fileId, thumbId, thumbYn}) => ({fileId, thumbId, thumbYn}))

      if(this.boardValidator({title, catNo, content, passwd}, true)) {

        createBoard({title, content, catNos, files, regId, passwd}).then((result) => {
          if (result) {
            console.log("등록 성공")
            this.goToList()
          } else {
            alert("등록 실패")
          }
        })
      }
    },
    toggleEdit(editable) {
      this.editable = editable
    }
  }
}
</script>