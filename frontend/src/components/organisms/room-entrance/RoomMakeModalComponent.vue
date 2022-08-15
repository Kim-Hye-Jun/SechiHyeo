<template>
  <div>
    <div class="modal-content">
      <h1 class="text">방 만들기</h1>
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
      <button-component-vue @click="myFunc()"></button-component-vue>
      <button-component-back @click="modalOut"></button-component-back>
    </div>
  </div>

  <!-- 방 제목 -->
  <!-- 토론 주제 -->
  <!-- A 진영 -->
  <!-- B 진영 -->
  <!-- </div> -->
</template>
<script lang="ts">
import { defineComponent } from "vue";
import ModalInputBoxComponent from "@/components/molecules/room-entrance/ModalInputBoxComponent.vue";
import ButtonComponentVue from "@/components/atoms/common/ButtonComponent.vue";
import ButtonComponentBack from "@/components/atoms/common/ButtonComponentBack.vue";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
import { RoomCreateRequestInfo, RoomCreateResponseInfo } from "@type/types";
import http from "@/http";

export default defineComponent({
  components: {
    ModalInputBoxComponent,
    ButtonComponentVue,
    ButtonComponentBack,
    ModalRadioButtonComponent,
  },
  data() {
    return {
      roomTitleLabelName: "RoomTitle" as string,
      debateTopicLabelName: "DebateTopic" as string,
      sideALabelName: "Side 1" as string,
      sideBLabelName: "Side 2" as string,
      accessModifierRadioSelecter: "accessModifier" as string,
      numOfPeopleRadioSelecter: "numOfPeople" as string,
      url: null as any,
    };
  },
  methods: {
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
      this.$router.push({
        name: "RoomInsidePage",
        params: {
          roomId: roomId,
        },
      });
    },
    modalOut() {
      this.$emit("modalOut");
    },
    onFileChange(e: any) {
      const file = e.target.files[0];
      this.url = URL.createObjectURL(file);
    },
  },
});
</script>

<style scoped>
.modal-content {
  width: 60vw;
  left: 10vw;
  background: rgba(0, 0, 0, 10);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;

  position: fixed;

  z-index: 9999;
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

.img {
  max-width: 100px;
  height: auto;
}
</style>
