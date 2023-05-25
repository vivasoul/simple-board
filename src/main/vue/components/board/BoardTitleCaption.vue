<template>
  <div class="board-title-caption">
    <div class="caption-item">{{regId}} | {{regDt}}</div>
    <div class="caption-item">{{replyCnt}} 댓글</div>
  </div>
</template>

<script>
export default {
  name: "BoardTitleCaption",
  props: ["regId", "regDt", "viewCnt", "replyCnt" ]
}
</script>