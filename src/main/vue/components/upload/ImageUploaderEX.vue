<template>
  <div class="q-pa-xs upload-btn">
    <q-btn color="secondary" @click="handleUploadClick">
      <div class="text-center">
        <q-icon name="camera_enhance" />
      </div>
      <div class="text-center">
        이미지 첨부
      </div>
    </q-btn>
  </div>
  <q-dialog v-model="show" @hide="handleUploadClose">
    <q-uploader
        url="/files/upload"
        field-name="files"
        :label="uploadLabel"
        color="amber"
        text-color="black"
        accept="image/*"
        :max-file-size="1048576*maxSize"
        multiple
        batch
        auto-upload
        style="max-width:400px;max-height:800px;"
        @uploaded="handleUploadComplete"
    />
  </q-dialog>
</template>

<script>
import useContentEditor from "@/composables/useContentEditor"

export default {
  name: "ImageUploaderEX",
  emits:["uploadedAdd", "uploaderClose"],
  props: {
    "maxSize": {
      "type": Number,
      "default": 1
    }
  },
  setup() {
    const { focusLast } = useContentEditor()

    return {
      focusLast
    }
  },
  data() {
    return {
      show: false
    }
  },
  methods: {
    handleUploadClick() {
      this.focusLast()
      this.show = true
    },
    handleUploadComplete({xhr}) {
      if(xhr.status == 200) {
        this.show = false
        const res = JSON.parse(xhr.response);
        this.$emit("uploadedAdd", res);
      }
    },
    handleUploadClose(){

      this.$emit("uploaderClose");
    }
  },
  computed: {
    uploadLabel() {
      return `이미지업로드(이미지당 최대 ${this.maxSize}MB)`;
    }
  }
}
</script>

<style scoped>
.upload-btn {
  height:100%;
}
.upload-btn button {
  width:100%;
  height:100%;
}
</style>