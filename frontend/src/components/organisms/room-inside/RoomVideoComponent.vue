<template>
  <Background></Background>
  <div class="grid">
    <!-- <user-video-component-vue
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
    ></user-video-component-vue> -->
    <user-video-component-vue
      :stream-manager="publisher"
      :class="roomAndUserData?.userSideOrder"
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      v-for="sub in subscribers"
      v-bind:key="sub.stream.connection.connectionId"
      :stream-manager="sub"
      :class="
        userSideOrderMap?.get(
          JSON.parse(sub.stream.connection.data.split('%/%')[1])['userId']
        )
      "
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      v-for="empty in emptyVideoClasses"
      v-bind:key="empty"
      :class="empty"
    >
    </user-video-component-vue>
    <DebateImage1 :class="[data1 === true ? '' : 'hidden']"></DebateImage1>
    <DebateImage2 :class="[data2 === true ? '' : 'hidden']"></DebateImage2>
    <DebateImage3 :class="[data3 === true ? '' : 'hidden']"></DebateImage3>
  </div>
  <div style="margin-left: 26%">
    <button @click="data1Click">1</button>
    <button @click="data2Click">2</button>
    <button @click="data3Click">3</button>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import http from "@/http";
import * as openVidu from "openvidu-browser";
import { RoomJoinResponseInfo } from "@type/types";
import UserVideoComponentVue from "@/components/atoms/room-inside/UserVideoComponent.vue";
import Background from "@/components/common/Background.vue";
import DebateImage1 from "../../molecules/DebateImage1.vue";
import DebateImage2 from "../../molecules/DebateImage2.vue";
import DebateImage3 from "../../molecules/DebateImage3.vue";

export default defineComponent({
  props: {
    publisher: openVidu.Publisher,
    subscribers: Array(Object),
    roomAndUserData: Object,
    userSideOrderMap: Map,
    emptyVideoClasses: Array(Object),
  },
  data() {
    return {
      startElement: null as EventTarget | null,
      data1: true,
      data2: false,
      data3: false,
    };
  },
  components: {
    UserVideoComponentVue,
    DebateImage1,
    DebateImage2,
    DebateImage3,
    Background,
  },
  methods: {
    test(arr: Array<string>, index: number, name: string): string {
      // console.log("name : ", name);
      // console.log("arr : ", arr);
      // console.log("index : ", index);
      return arr[index];
    },
    isRoomAdmin(): boolean {
      // console.log("HOST : ", this.roomAndUserData?.host);
      return this.roomAndUserData?.host;
    },
    dragOver(e: Event): void {
      e.preventDefault();
      console.log("OVER", e);
    },
    dragEnter(e: Event): void {
      // e.preventDefault();
      console.log("ENTER", e);
    },
    dragLeave(e: Event): void {
      // e.preventDefault();
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
      // console.log("START //  DROP", startVideoClassName, dropVideoClassName);

      // 클래스 이름 변경 하고 서버에 요청보내주기
    },
    dragStart(e: Event): void {
      this.startElement = e.target;
      console.log("START : ", e);
    },
    dragEnd(e: Event): void {
      // e.preventDefault();
      // console.log("END", e.target);
      // console.log("START", this.startElement);
      // console.log((e.target as HTMLDivElement).className);
      // console.log((this.startElement as HTMLDivElement).className);
    },
    data1Click() {
      this.data1 = true;
      this.data2 = false;
      this.data3 = false;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
    data2Click() {
      this.data1 = false;
      this.data2 = true;
      this.data3 = false;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
    data3Click() {
      this.data1 = false;
      this.data2 = false;
      this.data3 = true;
      console.log(this.data1);
      console.log(this.data2);
      console.log(this.data3);
    },
  },
  mounted() {
    const videos = document.querySelectorAll("video");
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

<style scoped>
.grid {
  /* background: radial-gradient(circle, #141834 0%, #13162f 100%); */
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
  padding: 20px;
  box-sizing: border-box;
  background-clip: content-box;
}
</style>
