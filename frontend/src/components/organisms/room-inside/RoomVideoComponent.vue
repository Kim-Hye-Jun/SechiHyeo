<template>
  <div class="grid">
    <!-- <user-video-component-vue
      :stream-manager="publisher"
      :class="roomAndUserData?.userSideOrder"
    ></user-video-component-vue> -->
    <user-video-component-vue
      :draggable="isRoomAdmin()"
      class="a1"
    ></user-video-component-vue>
    <user-video-component-vue
      class="a2"
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      class="a3"
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b1"
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b2"
      :draggable="isRoomAdmin()"
    ></user-video-component-vue>
    <user-video-component-vue
      class="b3"
      :draggable="isRoomAdmin()"
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
  components: { UserVideoComponentVue },
  methods: {
    isRoomAdmin(): boolean {
      return true;
    },
  },
  // setup() {},
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
.a1 {
  width: 100%;
  grid-area: a1;
}
.a2 {
  width: 100%;
  grid-area: a2;
}
.a3 {
  width: 100%;
  grid-area: a3;
}
.b1 {
  width: 100%;
  grid-area: b1;
}
.b2 {
  width: 100%;
  grid-area: b2;
}
.b3 {
  width: 100%;
  grid-area: b3;
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
