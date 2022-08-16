<template>
  <video autoplay :class="xx"></video>
  <!-- <div class="flex">
    <video autoplay :id="xx" :draggable="isRoomAdmin"></video>
    <debate-memo-modal-component></debate-memo-modal-component>
  </div> -->
</template>

<script lang="ts">
import { defineComponent } from "vue";
// import DebateMemoModalComponent from "@components/molecules/room-inside/DebateMemoModalComponent.vue";

export default defineComponent({
  components: {
    // DebateMemoModalComponent,
  },
  props: {
    streamManager: Object,
    xx: String,
    // isRoomAdmin: Boolean,
  },
  mounted() {
    console.log("추가", this.streamManager);
    console.log("EL : ", this.$el);
    console.log("selector : ", document.querySelector("video"));
    // this.streamManager?.addVideoElement(
    //   document.getElementById(this.xx as string)
    // );
    console.log(document.getElementsByClassName(this.$props.xx as string)[0]);

    this.streamManager?.addVideoElement(
      document.getElementsByClassName(this.$props.xx as string)[0]
    );
  },
  data() {
    return {};
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

/* .flex {
  display: flex;
  flex-direction: column;
  align-items: center;
} */
</style>
