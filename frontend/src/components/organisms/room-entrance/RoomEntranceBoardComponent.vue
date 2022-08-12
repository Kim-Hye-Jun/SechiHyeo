<template>
  <div class="my-container grid">
    <room-entrance-component
      v-for="(roomInfo, index) in roomInfoList"
      @click="joinRoom(roomInfo.roomId)"
      v-bind:roomInfo="roomInfo"
      v-bind:key="index"
    ></room-entrance-component>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import RoomEntranceComponent from "@components/molecules/room-entrance/RoomEntranceComponent.vue";
import { RoomThumbnailInfo } from "@type/types";

import http from "@/http";
// import ContainerComponent from "@components/atoms/common/ContainerComponent.vue";
export default defineComponent({
  components: { RoomEntranceComponent },
  async setup(props) {
    // (#구현 해야할것)create시에 axios 요청으로 방 가져와서 방 썸네일에 넣어줌 (roomInfoData)
    let roomInfoDataList: Array<RoomThumbnailInfo>;
    const response = await http.get("sessions/1");
    roomInfoDataList = response.data;
    console.log(roomInfoDataList);
    return {
      roomInfoList: roomInfoDataList,
    };
  },
  methods: {
    // test(): void {
    //   console.log(myGlobal);
    // },
    joinRoom(roomId: string): void {
      // (#구현해야할것) Room ID 를 이용해서 방에 참가신청, 전처리 -> 방인원수 확인
      console.log(roomId, "==== 접속");
      this.goRoomInsidePage(roomId);
    },
    goRoomInsidePage(roomId: string): void {
      this.$router.push({
        name: "RoomInsidePage",
        params: {
          roomId: roomId,
        },
      });
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
