<template>
  <div class="q-pa-xs">
<!--    <q-input-->
<!--        v-if="$isMobile()"-->
<!--        v-model="content"-->
<!--        filled-->
<!--        type="textarea"-->
<!--        :input-style="{resize:'none',height:'300px'}"-->
<!--        clearable-->
<!--        @change="handleChange"-->
<!--    />-->
    <tiny-editor
        :init="editorConfig"
        v-model="content"
        api-key="hos0gsqyxmwl1gdwx91tnhbgkkjcspt61n05og9kwkqrayd6"
        @change="handleChange"
    />
  </div>
</template>

<script>
import Editor from '@tinymce/tinymce-vue'
import useBoardDetail from "@/composables/useBoardDetail"
import useContentEditor from "@/composables/useContentEditor"
export default {
  name: "BoardContentEditor",
  props:["modelValue"],
  emits:["update:modelValue"],
  setup() {
    const { content } = useBoardDetail()
    return {
      content
    }
  },
  components: {
    "tiny-editor": Editor
  },
  data(){
    return {
     // content: this.modelValue,
      editorConfig: {
        plugins: "lists link image table code wordcount",
        toolbar: "styles | bold italic underline | forecolor backcolor | alignleft aligncenter alignright | bullist numlist | link table",
        //images_upload_url: "http://localhost",
        menubar: false,
        language: "ko_KR",
        convert_urls: false
      }
    }
  },
  methods: {
    handleChange(e, el) {
      let val = ""

      if(el && e.target === el) {
        val = el.getContent()
      } else {
        val = e
      }
      this.$emit('update:modelValue', val)
    }
  }
}
</script>

<style scoped>

</style>