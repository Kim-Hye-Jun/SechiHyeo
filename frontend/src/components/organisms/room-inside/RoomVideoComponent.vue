<template>
  <div class="grid">
    <!-- <user-video-component-vue
      :stream-manager="publisher"
      :class="roomAndUserData?.userSideOrder"
    ></user-video-component-vue> -->
    <user-video-component-vue
      class="a1 video"
      :draggable="isRoomAdmin()"
      value="1"
    ></user-video-component-vue>
    <user-video-component-vue
      class="a2 video"
      :draggable="isRoomAdmin()"
      value="2"
    ></user-video-component-vue>
    <user-video-component-vue
      class="a3 video"
      :draggable="isRoomAdmin()"
      value="3"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b1 video"
      :draggable="isRoomAdmin()"
      value="4"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b2 video"
      :draggable="isRoomAdmin()"
      value="5"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b3 video"
      :draggable="isRoomAdmin()"
      value="6"
    ></user-video-component-vue>
    <!-- <user-video
      v-for="sub in subscribers"
      v-bind:key="sub.stream.connection.connectionId"
      :stream-manager="sub"
      :class="
        userSideOrderMap?.get(JSON.parse(sub.stream.connection.data)['userId'])
      "
    /> -->
    <div class="c"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import http from "@/http";
import * as openVidu from "openvidu-browser";
import { RoomJoinResponseInfo } from "@type/types";
import UserVideoComponentVue from "@/components/atoms/room-inside/UserVideoComponent.vue";

export default defineComponent({
  props: {
    publisher: openVidu.Publisher,
    subscribers: Array(Object),
    roomAndUserData: Object,
    userSideOrderMap: Map,
  },
  data() {
    return {
      startElement: null as EventTarget | null,
    };
  },
  components: { UserVideoComponentVue },
  methods: {
    isRoomAdmin(): boolean {
      return true;
    },
    dragOver(e: Event): void {
      e.preventDefault();
      console.log("OVER", e);
    },
    dragEnter(e: Event): void {
      e.preventDefault();
      console.log("ENTER", e);
    },
    dragLeave(e: Event): void {
      e.preventDefault();
      console.log("LEAVE", e);
    },
    dragDrop(e: Event): void {
      e.preventDefault();
      console.log("DROP", e.target);
      console.log("START", this.startElement);

      const dropVideoClassName = (e.target as HTMLDivElement).className;
      const startVideoClassName = (this.startElement as HTMLDivElement)
        .className;

      (this.startElement as HTMLDivElement).className = dropVideoClassName;
      (e.target as HTMLDivElement).className = startVideoClassName;
    },
    dragStart(e: Event): void {
      console.log("START", e.target);
      this.startElement = e.target;
    },
    dragEnd(e: Event): void {
      e.preventDefault();
      // console.log("END", e.target);
      // console.log("START", this.startElement);

      // console.log((e.target as HTMLDivElement).className);
      // console.log((this.startElement as HTMLDivElement).className);
    },
  },
  mounted() {
    const videos = document.querySelectorAll(".video");
    for (const video of videos) {
      video.addEventListener("dragstart", this.dragStart);
      video.addEventListener("dragover", this.dragOver);
      video.addEventListener("dragenter", this.dragEnter);
      video.addEventListener("dragleave", this.dragLeave);
      video.addEventListener("drop", this.dragDrop);
      video.addEventListener("dragend", this.dragEnd);
    }
  },
});
</script>

<style>
.grid {
  background: radial-gradient(circle, #141834 0%, #13162f 100%);
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  /* gap: 1.5em; */
  grid-template-areas:
    "a1 c b1"
    "a2 c b2"
    "a3 c b3";
}
.video {
  padding: 20px;
  width: 100%;
}
.a1 {
  grid-area: a1;
  background: yellow;
}
.a2 {
  grid-area: a2;
  background: red;
}
.a3 {
  grid-area: a3;
  background: blue;
}
.b1 {
  grid-area: b1;
  background: green;
}
.b2 {
  grid-area: b2;
  background: pink;
}
.b3 {
  grid-area: b3;
  background: rgb(142, 133, 88);
}
.c {
  width: 100%;
  grid-area: c;
  background: red;
  padding: 20px;
  box-sizing: border-box;
  background-clip: content-box;
}
</style>
