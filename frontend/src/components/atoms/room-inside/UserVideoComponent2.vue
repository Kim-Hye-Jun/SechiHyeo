<template>
  <video autoplay :streamManager="streamManager"></video>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    streamManager: Object,
  },
  mounted() {
    console.log("추가", this.streamManager);
    this.streamManager?.addVideoElement(this.$el);
  },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style scoped>
video {
  width: 300px;
  height: 150px;

  background: black;
  border-radius: 10px;
  /* padding: 5px; */
  box-sizing: border-box;
  background-clip: content-box;
}
</style>
