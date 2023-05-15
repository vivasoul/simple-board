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
  <q-dialog v-model="show">
    <q-uploader
        url="/files/upload"
        field-name="files"
        label="이미지업로드(이미지당 최대 1MB)"
        color="amber"
        text-color="black"
        max-file-size="2097152"
        multiple
        batch
        auto-upload
        style="max-width:400px;max-height:800px;"
        @uploaded="handleUploadComplete"
    />
  </q-dialog>
</template>

<script>
export default {
  name: "ImageUploaderEX",
  emits:["uploadedAdd"],
  data() {
    return {
      show: false
    }
  },
  methods: {
    handleUploadClick() {
      this.show = true
    },
    handleUploadComplete({xhr}) {
      if(xhr.status == 200) {
        const res = JSON.parse(xhr.response);
        this.$emit("uploadedAdd", res);
      }
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