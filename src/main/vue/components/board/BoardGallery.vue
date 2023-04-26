<template>
<!--  <q-btn
      v-if="list.length > 0"
      color="blue-9"
      icon="fullscreen"
      labe="확대"
      class="board-gallery-open-btn"
      size="xs"
      dense
      square
      push
      @click="handleExpandClick" />-->
  <div class="q-ma-xs gallery-preview-list">
    <board-gallery-item
        v-for="({fileId, filePath, thumbYn}) in list"
        :key="fileId"
        :file-id="fileId"
        :file-path="filePath"
        :thumb-yn="thumbYn"
    />
  </div>
<!--  <q-dialog v-model="show">
    <div class="board-gallery-carousel">
      <q-carousel
          v-model="slide"
          animated
          swipeable
          arrows
          infinite
          padding
          :style="{width:'100%', background:'transparent'}"
        >
        <q-carousel-slide
            v-for="({fileId, filePath}, idx) in list"
            :key="fileId"
            :name="idx+1"
        >
          <q-img :src="filePath" fit="contain"/>
        </q-carousel-slide>
      </q-carousel>
    </div>
  </q-dialog>-->
</template>

<script>
import BoardGalleryItem from "@/components/board/BoardGalleryItem.vue"

export default {
  name: "BoardGallery",
  components: {BoardGalleryItem},
  props: {
    "list": {
      "type": Array,
      "default": []
    }
  },
  data() {
    return {
      "show": false,
      "slide": 1
    }
  },
  methods: {
    handleExpandClick() {
      this.show = true
    }
  }
}
</script>

<style scoped>
.board-gallery-open-btn {
  display: inline-block;
  position: absolute;
  z-index: 2;
  top: 60px;
  left: 15px;
}
</style>