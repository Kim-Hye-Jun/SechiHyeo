<template>
  <!-- <Background></Background> -->
  <div class="grid">
    <user-video-component-vue
      :stream-manager="publisher"
      :class="roomAndUserData?.userSideOrder"
      :xx="roomAndUserData?.userSideOrder"
      :isRoomAdmin="isRoomAdmin()"
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
      :xx="
        userSideOrderMap?.get(
          JSON.parse(sub.stream.connection.data.split('%/%')[1])['userId']
        )
      "
      :isRoomAdmin="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      v-for="empty in emptyVideoClasses"
      v-bind:key="empty"
      :class="empty"
    >
    </user-video-component-vue>
    <div class="c">
      <img :src="imgSrc" id="shareImg" style="width: 500px; height: 500px" />
    </div>
  </div>
  <div style="margin-left: 26%">
    <button
      v-for="(item, index) in store.state.uploadImageArr"
      v-bind:key="item"
      @click="updateImg(item)"
    >
      {{ index + 1 }}
    </button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import http from "@/http";
import * as openVidu from "openvidu-browser";
import { RoomUpdateUserSideOrderRequestInfo } from "@type/types";
import UserVideoComponentVue from "@/components/atoms/room-inside/UserVideoComponent.vue";
import { member2 } from "@/api/index";

import { useStore } from "vuex";

export default defineComponent({
  props: {
    publisher: openVidu.Publisher,
    subscribers: Array(Object),
    roomAndUserData: Object,
    userSideOrderMap: Map,
    emptyVideoClasses: Array(Object),
  },
  setup() {
    const store = ref(useStore());
    return { store };
  },
  data() {
    return {
      data1: true,
      data2: false,
      data3: false,
      imgSrc: "",
      startElement: null as EventTarget | null,
    };
  },
  components: {
    UserVideoComponentVue,
    // Background,
  },
  methods: {
    updateImg(src: string): void {
      this.imgSrc = src;
    },
    test(a: string, b: string): void {
      console.log(a, b);
    },
    testC(): void {
      // console.log("CLICK e : ", e);
      console.log("CLIECK this : ", this);
      // this.startElement = e.target;
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
      console.log("DROP e.target : ", e.target);
      console.log("START this.startElemtnt : ", this.startElement);

      const dropVideoClassName = (e.target as HTMLDivElement).className;
      const startVideoClassName = (this.startElement as HTMLDivElement)
        .className;

      console.log(
        "START // ",
        startVideoClassName,
        "DROP //",
        dropVideoClassName
      );

      // (this.startElement as HTMLDivElement).className = dropVideoClassName;
      // (e.target as HTMLDivElement).className = startVideoClassName;

      console.log("e.target : ", e.target);
      console.log("this.startElemtnt : ", this.startElement);

      // 클래스 이름 변경 하고 서버에 요청보내주기
      member2
        .put("/sessions/sideOrder", {
          roomId: this.roomAndUserData?.roomId,
          preSideOrder: dropVideoClassName,
          newSideOrder: startVideoClassName,
        } as RoomUpdateUserSideOrderRequestInfo)
        .then((res: any) => {
          console.log(res);
        });
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
  },
  mounted() {
    const videos = document.querySelectorAll("video");
    for (const video of videos) {
      video.addEventListener("dragstart", (e) => {
        this.startElement = e.target;
        console.log("START : ", e);
      });
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
