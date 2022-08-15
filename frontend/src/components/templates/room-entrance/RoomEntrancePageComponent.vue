<template>
  <div class="flex">
    <room-entrance-search-input-component
      @getSearchValue="updateRooms"
    ></room-entrance-search-input-component>
    <suspense>
      <room-entrance-board-component></room-entrance-board-component>
    </suspense>
    <button
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#create"
      @click="modalIn"
    >
      방 생성
    </button>
    <!-- <div class="modal" v-show="modal"> -->
    <!-- <room-make-modal-component
        class="modal-dialog modal-dialog-scrollable"
        @modalOut="modalOut"
      ></room-make-modal-component> -->
    <!-- </div> -->
    <!-- 모달창 -->
    <div id="create" class="modal">
      <div class="modal-dialog modal-dialog-scrollable modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="text">방 만들기</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          <div class="modal-body">
            <modal-input-box-component
              ref="roomTitleInput"
              :labelName="roomTitleLabelName"
            ></modal-input-box-component>
            <modal-input-box-component
              ref="debateSubjectInput"
              :labelName="debateTopicLabelName"
            ></modal-input-box-component>
            <div class="flex__modal__side">
              <modal-input-box-component
                ref="sideAInput"
                :labelName="sideALabelName"
              ></modal-input-box-component>
              <modal-input-box-component
                ref="sideBInput"
                :labelName="sideBLabelName"
              ></modal-input-box-component>
            </div>
            <div>
              <input type="file" @change="onFileChange" />
              <div id="preview">
                <img v-if="url" :src="url" />
              </div>
            </div>
            <div class="flex__ul">
              <modal-radio-button-component
                labelId="private"
                labelValue="private"
                :selector="accessModifierRadioSelecter"
              ></modal-radio-button-component>
              <modal-radio-button-component
                labelId="public"
                labelValue="public"
                :selector="accessModifierRadioSelecter"
              ></modal-radio-button-component>
            </div>
            <div class="flex__ul">
              <modal-radio-button-component
                labelId="2"
                labelValue="2"
                :selector="numOfPeopleRadioSelecter"
              ></modal-radio-button-component>
              <modal-radio-button-component
                labelId="4"
                labelValue="4"
                :selector="numOfPeopleRadioSelecter"
              ></modal-radio-button-component>
              <modal-radio-button-component
                labelId="6"
                labelValue="6"
                :selector="numOfPeopleRadioSelecter"
              ></modal-radio-button-component>
            </div>
          </div>

          <div class="modal-footer">
            <button-component-vue
              data-bs-dismiss="modal"
              @click="myFunc()"
            ></button-component-vue>
            <button-component-back
              data-bs-dismiss="modal"
            ></button-component-back>
          </div>
        </div>
      </div>
      <!-- <room-make-modal-component></room-make-modal-component> -->
    </div>
    <!-- <button-component
      buttonName="Create"
      @click="state.isRoomMakeModalView = true"
    ></button-component>
    <room-make-modal-component
      v-if="state.isRoomMakeModalView"
    ></room-make-modal-component> -->
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs, reactive } from "vue";
import RoomEntranceBoardComponent from "@components/organisms/room-entrance/RoomEntranceBoardComponent.vue";
import RoomEntranceSearchInputComponent from "@components/atoms/room-entrance/RoomEntranceSearchInputComponent.vue";
import ButtonComponent from "@/components/atoms/common/ButtonComponent2.vue";
import ButtonComponentVue from "@/components/atoms/common/ButtonComponent.vue";
import ButtonComponentBack from "@/components/atoms/common/ButtonComponentBack.vue";
import ModalInputBoxComponent from "@/components/molecules/room-entrance/ModalInputBoxComponent.vue";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
import RoomMakeModalComponent from "@/components/organisms/room-entrance/RoomMakeModalComponent.vue";
import { RoomCreateRequestInfo, RoomCreateResponseInfo } from "@type/types";

import http from "@/http";
import { hasChanged } from "@vue/shared";

export default defineComponent({
  components: {
    RoomEntranceBoardComponent,
    RoomEntranceSearchInputComponent,
    // ButtonComponent,
    // RoomMakeModalComponent,
    ButtonComponentVue,
    ButtonComponentBack,
    ModalInputBoxComponent,
    ModalRadioButtonComponent,
  },
  setup() {
    let state = reactive({
      isRoomMakeModalView: false,
    });

    return {
      state,
    };
  },
  data() {
    return {
      modal: false,
      url: null as any,
      roomTitleLabelName: "RoomTitle" as string,
      debateTopicLabelName: "DebateTopic" as string,
      sideALabelName: "Side 1" as string,
      sideBLabelName: "Side 2" as string,
      accessModifierRadioSelecter: "accessModifier" as string,
      numOfPeopleRadioSelecter: "numOfPeople" as string,
    };
  },
  methods: {
    updateRooms(searchValue: string) {
      //(#구현해야할것) searchValue로 방검색 axios 요청 보낸 후 room-entrance-board-component 갱신
    },
    onFileChange(e: any) {
      const file = e.target.files[0];
      this.url = URL.createObjectURL(file);
    },
    myFunc(): void {
      if (
        document.querySelector(
          `input[name=${this.accessModifierRadioSelecter}]:checked`
        ) === null ||
        document.querySelector(
          `input[name=${this.accessModifierRadioSelecter}]:checked`
        ) === null
      ) {
        console.log("NULL ...!");

        return;
      }

      const roomCreateRequestValue: RoomCreateRequestInfo = {
        roomTitle: (
          this.$refs.roomTitleInput as InstanceType<
            typeof ModalInputBoxComponent
          >
        ).inputValue,
        debateTopic: (
          this.$refs.debateSubjectInput as InstanceType<
            typeof ModalInputBoxComponent
          >
        ).inputValue,
        headCount: parseInt(
          (
            document.querySelector(
              `input[name=${this.numOfPeopleRadioSelecter}]:checked`
            ) as HTMLInputElement
          ).getAttribute("value") as string
        ),
        roomType: (
          document.querySelector(
            `input[name=${this.accessModifierRadioSelecter}]:checked`
          ) as HTMLInputElement
        ).getAttribute("value") as string,
        debateType: "토론유형",
        sideA: (
          this.$refs.sideAInput as InstanceType<typeof ModalInputBoxComponent>
        ).inputValue,
        sideB: (
          this.$refs.sideBInput as InstanceType<typeof ModalInputBoxComponent>
        ).inputValue,
      };

      // (#구현할것) 모달 input에서 데이터를 받아와서 방 생성 post 요청을 보냄
      console.log("ROOM CREATE REQUEST VALUE", roomCreateRequestValue);
      http
        .post("/sessions", roomCreateRequestValue)
        .then((res) => {
          console.log("ROOM CREATE RES : ", res);
          if (res.status === 200) {
            const roomId = res.data.roomId;
            console.log(roomId);
            this.goRoomInsidePage(roomId);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goRoomInsidePage(roomId: string): void {
      // this.modalOut();
      this.$router.push({
        name: "RoomInsidePage",
        params: {
          roomId: roomId,
        },
      });
    },
    modalIn() {
      this.modal = true;
    },
    modalOut() {
      this.modal = false;
    },
  },
});
</script>

<style scoped>
body {
  background: rgb(7, 8, 78);
}

.modal-content {
  background: black;
}

.flex {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.flex__ul {
  display: flex;
  flex-direction: row;
}
.flex__modal__side {
  display: flex;
  flex-direction: row;
  width: 50%;
}
.text {
  color: white;
}
</style>
