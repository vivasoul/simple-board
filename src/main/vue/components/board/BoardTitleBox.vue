<template>
  <div class="q-pa-xs">
    <q-input
        v-model="title"
        label="제목"
        filled
        dense
        @update:model-value="handleInputChange"
    />
  </div>
</template>

<script>
import useBoardDetail from "@/composables/useBoardDetail"

export default {
  name: "BoardTitleBox",
  props:["title"],
  emits:["updateTitle"],
  setup() {
    const { title } = useBoardDetail()

    return {
      title
    }
  },
  data() {
    return {
      //"text": this.title
    }
  },
  methods: {
    handleInputChange(val) {
      this.$emit("updateTitle", val)
    }
  }
}
</script>

<style scoped>

</style>