<template>
  <div class="board-detail">
    <board-head-editor/>
    <board-content-editor/>
  </div>
  <div class="q-pa-md q-gutter-y-md column items-end">
    <q-btn-group>
      <q-btn style="background:#69D44A;color:white;" label="등록" @click="createBoard"/>
      <q-btn style="background:#5DEB6B;color:white;" label="취소" @click="goToList"/>
    </q-btn-group>
  </div>
</template>

<script>
import {createBoard} from "@/data/api/board"
import useCategory from "@/composables/useCategory"
import useBoardDetail from "@/composables/useBoardDetail"
import {useValidation} from "@/composables/useValidation"
import BoardHeadEditor from "@/components/board/BoardHeadEditor.vue"
import BoardContentEditor from "@/components/board/BoardContentEditor.vue"

export default {
  name: "BoardDetail",
  components: {
    BoardHeadEditor,
    BoardContentEditor
  },
  setup() {
    const { category } = useCategory()
    const { boardValidator } =  useValidation()
    const { title, catNo, files, content } = useBoardDetail({catNo: category})

    return {
      boardValidator,
      title, catNo, files, content
    }
  },
  methods: {
    async createBoard() {
      const { title, content, catNo } = this;
      const catNos = [catNo];
      const files = this.files.map(({fileId, thumbYn}) => ({fileId, thumbYn}))

      if(this.boardValidator({title, catNo, content})) {

        createBoard({title, content, catNos, files}).then((result) => {
          if (result) {
            console.log("등록 성공")
            this.goToList()
          } else {
            alert("등록 실패")
          }
        })
      }
    },
    goToList() {
      this.$router.go(-1)
    },
    toggleEdit(editable) {
      this.editable = editable
    }
  }
}
</script>