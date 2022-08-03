<template>
  <div class="modal">
    <modal-input-box-component
      ref="roomTitleInput"
      labelName="RoomTitle"
    ></modal-input-box-component>
    <modal-input-box-component
      ref="debateSubjectInput"
      labelName="DebateSubject"
    ></modal-input-box-component>
    <ul>
      <modal-radio-button-component
        labelId="private"
        labelValue="private"
        selector="accessModifier"
        @method="setRadioValue"
      ></modal-radio-button-component>
      <modal-radio-button-component
        labelId="public"
        labelValue="public"
        selector="accessModifier"
        @method="setRadioValue"
      ></modal-radio-button-component>
    </ul>
    <button-component-vue @click="myFunc()"></button-component-vue>
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
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";

export default defineComponent({
  components: {
    ModalInputBoxComponent,
    ButtonComponentVue,
    ModalRadioButtonComponent,
  },
  data() {
    return {
      roomTitle: "" as string,
      radioValue: "" as string,
    };
  },
  methods: {
    myFunc(): void {
      let roomTitleInput: string = (
        this.$refs.roomTitleInput as InstanceType<typeof ModalInputBoxComponent>
      ).inputValue;
      let debateSubjectInput: string = (
        this.$refs.debateSubjectInput as InstanceType<
          typeof ModalInputBoxComponent
        >
      ).inputValue;
    },
    setRadioValue(radioValue: string): void {
      this.radioValue = radioValue;
    },
  },
});
</script>

<style>
body {
  width: 100vw;
  height: 100vh;
  background: linear-gradient(#141e30, #243b55);
}
.modal {
  width: 60vw;
  height: 60vh;
  left: 10vw;
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;

  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
}
</style>
