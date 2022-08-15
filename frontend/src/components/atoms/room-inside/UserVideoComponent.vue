<template>
  <div class="flex">
    <video autoplay id="video"></video>
    <debate-memo-modal-component></debate-memo-modal-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import DebateMemoModalComponent from "@components/molecules/room-inside/DebateMemoModalComponent.vue";

export default defineComponent({
  components: {
    DebateMemoModalComponent,
  },
  props: {
    streamManager: Object,
  },
  mounted() {
    console.log("추가", this.streamManager);
    this.streamManager?.addVideoElement(document.getElementById("video"));
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
  background: black;
  border-radius: 10px;
  padding: 5px;
  box-sizing: border-box;
  background-clip: content-box;
}

.flex {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
