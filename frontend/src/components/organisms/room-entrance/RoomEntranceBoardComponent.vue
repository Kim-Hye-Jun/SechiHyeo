<template>
  <!-- @click="joinRoom(roomInfo.roomId)" -->
  <div class="my-container grid">
    <room-entrance-component
      v-for="(roomInfo, index) in roomInfoList"
      @click="openModal(roomInfo)"
      v-bind:roomInfo="roomInfo"
      v-bind:key="index"
    ></room-entrance-component>
  </div>
  <div id="open-modal" class="modal-window">
    <div>
      <a href="#" title="Close" class="modal-close">X</a>
      <div class="modal__content__container">
        <p class="modal__content__roomTitle__paragraph">{{ roomTitle }}</p>
        <div class="modal_content__debateSide__container">
          <p class="modal__debateSide__paragraph">A진영 : {{ roomSideA }}</p>
          <p class="modal__debateSide__paragraph">B진영 : {{ roomSideB }}</p>
        </div>
        <div class="modal__content__sideorder__container">
          <modal-radio-button-component
            v-for="(sideOrder, index) in sideOrderList"
            v-bind:key="sideOrder"
            :class="returnClass(sideOrder.sideOrder, index)"
            :disabled="!sideOrder.empty"
            :labelId="sideOrder.sideOrder"
            :labelValue="sideOrder.sideOrder"
            :selector="sideOrderSelector"
          ></modal-radio-button-component>
        </div>
        <button-component
          class="modal__content__joinbutton"
          buttonName="JOIN!"
          @click="joinRoom"
        ></button-component>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref } from "vue";
import RoomEntranceComponent from "@components/molecules/room-entrance/RoomEntranceComponent.vue";
import { RoomThumbnailInfo, SideOrderInfo } from "@type/types";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
import ButtonComponent from "@/components/atoms/common/ButtonComponent.vue";

import http from "@/http";
// import ContainerComponent from "@components/atoms/common/ContainerComponent.vue";
export default defineComponent({
  components: {
    RoomEntranceComponent,
    ModalRadioButtonComponent,
    ButtonComponent,
  },
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
  data() {
    return {
      roomTitle: "roomTitle",
      roomSideA: "Aside",
      roomSideB: "Bside",
      sideOrderList: [] as Array<SideOrderInfo>,
      sideOrderSelector: "sideOrderSelector",
      roomId: "",
    };
  },
  methods: {
    returnClass(s: string, idx: number): string {
      return "@@";
    },
    openModal(roomInfo: any): void {
      this.sideOrderList = roomInfo.sideOrderList;
      this.roomId = roomInfo.roomId;
      console.log(this.sideOrderList);
      document.location.href = "#open-modal";
    },
    joinRoom(): void {
      // (#구현해야할것) Room ID 를 이용해서 방에 참가신청, 전처리 -> 방인원수 확인
      // 선택 하지 않았다면?

      if (
        !(document.querySelector(
          `input[name=${this.sideOrderSelector}]:checked`
        ) as HTMLInputElement)
      ) {
        alert("진영 및 순서를 선택해주세요");
        return;
      }

      const sideOrderInputValue = (
        document.querySelector(
          `input[name=${this.sideOrderSelector}]:checked`
        ) as HTMLInputElement
      ).getAttribute("value") as string;

      console.log(this.roomId);
      console.log(sideOrderInputValue);

      this.goRoomInsidePage(
        this.roomId,
        sideOrderInputValue[0],
        parseInt(sideOrderInputValue[1])
      );

      // join (roomId, sideOrderInputValue)
    },
    goRoomInsidePage(roomId: string, side: string, order: number): void {
      this.$router.push({
        name: "RoomInsidePage",
        params: {
          roomId: roomId,
          side: side,
          order: order,
        },
      });
    },
  },
});
</script>

<style scoped>
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

/* ?#### */
.modal-window {
  position: fixed;
  background-color: rgba(255, 0, 0, 0.25);
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 999;
  visibility: hidden;
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s;
}
.modal-window:target {
  visibility: visible;
  opacity: 1;
  pointer-events: auto;
}
.modal-window > div {
  width: 800px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 2em;
  background: white;
}
.modal-window header {
  font-weight: bold;
}
.modal-window h1 {
  font-size: 150%;
  margin: 0 0 15px;
}

.modal-close {
  color: #aaa;
  line-height: 50px;
  font-size: 80%;
  position: absolute;
  right: 0;
  text-align: center;
  top: 0;
  width: 70px;
  text-decoration: none;
}
.modal-close:hover {
  color: black;
}

/* Demo Styles */
/* html,
body {
  height: 100%;
}

html {
  font-size: 18px;
  line-height: 1.4;
} */

body {
  font-family: apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans,
    Ubuntu, Cantarell, "Helvetica Neue", sans-serif;
  font-weight: 600;
  background-image: linear-gradient(to right, #7f53ac 0, #657ced 100%);
  color: black;
}

a {
  color: inherit;
  text-decoration: none;
}

.container {
  display: grid;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.modal-window > div {
  border-radius: 1rem;
}

.modal-window div:not(:last-of-type) {
  margin-bottom: 15px;
}

.logo {
  max-width: 150px;
  display: block;
}

small {
  color: lightgray;
}

.btn {
  background-color: white;
  padding: 1em 1.5em;
  border-radius: 0.5rem;
  text-decoration: none;
}
.btn i {
  padding-right: 0.3em;
}

.modal__content__container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.modal__content__sideorder__container {
  display: grid;
  width: 100%;
  grid-template-columns: 1fr 1fr;
}

.modal_content__debateSide__container {
  display: flex;
  flex-direction: row;
  width: 100%;
}

.modal__debateSide__paragraph {
  width: 50%;
  text-align: center;
  font-size: 30px;
  /* padding-left: 10px; */
}

.modal__content__roomTitle__paragraph {
  text-align: center;
  font-size: 50px;
}

.modal__content__joinbutton {
  width: 30%;
}
</style>
