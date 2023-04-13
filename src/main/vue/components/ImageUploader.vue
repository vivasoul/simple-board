<template>
  <input type="file" class="hidden-uploader" ref="fileInput" accept=".jpeg,.jpg,.png,.bmp,.webp,.gif" @change="handleFileChange"/>
  <div class="q-pa-md q-gutter-y-md column items-start">
    <q-btn-group>
      <q-btn color="secondary" label="이미지 첨부" icon="camera_enhance" @click="popupFileDialog"/>
    </q-btn-group>
  </div>
  <div :style="{color:'red', fontSize:'11px'}">* 업로드된 미리보기를 클릭하여야 본문에 실제로 첨부 됩니다.</div>
  <div :style="{fontSize:'11px'}">* 업로드 가능한 이미지 사이즈는 파일당 1MB가 최대입니다.</div>
  <div class="upload-preview-list">
    <img class="upload-preview-item" v-for="({downFilePath}) in filesUploaded" :src="downFilePath" @click="handleImgClick" />
  </div>
</template>

<script>
import { uploadFiles } from "@/data/api/file"
export default {
  name: "ImageUploader",
  emits:["previewClick"],
  data() {
    return {
      files: [],
      filesUploaded: []
    }
  },
  methods: {
    popupFileDialog() {
      this.$refs.fileInput.click()
    },
    handleImgClick(e) {
      this.$emit("previewClick", e.target.src)
    },
    handleFileChange(e) {
      const file = e.target.files[0]
      if(file) {
        const filesUploaded = this.filesUploaded;

        this.files.push(file)
        this._uploadFiles(this.files).then(
            function(data) {
              if(data) {
                for(let i=0; i<data.length; i++) {
                  filesUploaded.push(data[i])
                }
              }
            }
        )
      }
    },
    async _uploadFiles(files) {
      const postData = new FormData();
      if(files.length) {
        files.forEach((f, i) => {
          postData.append("files", f)
        })
      }

      const res = await uploadFiles(postData)
      this._initFiles(files)
      return res
    },
    _initFiles(files) {
      files.splice(0, files.length)
    }
  },
  mounted() {
    this.files = []
    this.filesUploaded = []
  }
}
</script>

<style scoped>
.hidden-uploader {
  display:none;
}
.upload-preview-list {
  margin: 20px 10px;
}
.upload-preview-item {
  margin: 3px;
  width: 100px;
  height: 100px;
  cursor: pointer;
}
</style>