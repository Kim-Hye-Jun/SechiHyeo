<template>
  <div class="my-container grid">
    <room-entrance-component
      :roomInfo="roomInfoData"
      @click="joinRoom"
    ></room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref } from "vue";
import RoomEntranceComponent from "@components/molecules/room-entrance/RoomEntranceComponent.vue";
import { RoomThumbnailInfo } from "@type/types";
import http from "@/http";
// import ContainerComponent from "@components/atoms/common/ContainerComponent.vue";
export default defineComponent({
  components: { RoomEntranceComponent },
  setup() {
    // (#구현 해야할것)create시에 axios 요청으로 방 가져와서 방 썸네일에 넣어줌 (roomInfoData)
  },
  methods: {
    // test(): void {
    //   console.log(myGlobal);
    // },
    joinRoom(): void {
      // (#구현해야할것) Room ID 를 이용해서 방에 참가신청, 전처리 -> 방인원수 확인
    },
  },
});
</script>

<style>
.grid {
  background: black;
  position: relative;
  z-index: 1;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  opacity: 1;
  row-gap: 5em;
  column-gap: 3em;
}
</style>
