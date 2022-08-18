<template>
  <div class="video__extra__container">
    <button-component3
      v-show="!isModalView"
      @click="isModalView = true"
    ></button-component3>
    <p v-if="!isModalView">{{ nickname }}</p>
    <debate-memo-component
      class="memo__modal"
      v-show="isModalView"
      @close-modal="isModalView = false"
    ></debate-memo-component>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import DebateMemoComponent from "@components/atoms/room-inside/DebateMemoComponent.vue";
import ButtonComponent3 from "@/components/atoms/common/ButtonComponent3.vue";

export default defineComponent({
  components: { DebateMemoComponent, ButtonComponent3 },
  props: {
    nickname: String,
  },
  data() {
    return {
      isModalView: false as boolean,
      parentData: "" as string,
    };
  },
});
</script>

<style scoped>
.video__extra__container {
  display: flex;
  flex-direction: row;
}
.memo__modal {
  position: absolute;
}
p {
  font-size: 20px;
  color: #3a3897;
  padding-left: 25%;
  overflow: hidden;
}
</style>
