<template>
  <q-dialog v-model="show" @hide="handelClose">
    <q-card>
      <q-card-section class="row items-center">
        <span class="q-ml-sm" v-html="message"></span>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat :label="okLabel" color="green-7" @click="handelConfirm" />
        <q-btn flat label="취소" color="red-7" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
export default {
  name: "ConfirmModal",
  props:{
    "message": String,
    "okLabel": {
      type: String,
      default: "확인"
    },
    "modalShow": Boolean
  },
  emits:["confirmAccept","confirmClose"],
  data() {
    return {
      "show": false
    }
  },
  methods: {
    handelConfirm() {
      this.$emit("confirmAccept")
    },
    handelClose() {
      this.$emit("confirmClose")
    }
  },
  mounted() {
    this.show = true
  }
}
</script>

<style scoped>

</style>