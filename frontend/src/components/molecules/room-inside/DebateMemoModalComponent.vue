<template>
  <debate-memo-component
    v-if="isModalView"
    @close-modal="isModalView = false"
  ></debate-memo-component>
  <button-component3
    v-if="!isModalView"
    @click="isModalView = true"
  ></button-component3>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import DebateMemoComponent from "@components/atoms/room-inside/DebateMemoComponent.vue";
import ButtonComponent3 from "@/components/atoms/common/ButtonComponent3.vue";

export default defineComponent({
  components: { DebateMemoComponent, ButtonComponent3 },
  data() {
    return {
      isModalView: false as boolean,
      parentData: "" as string,
    };
  },
});
</script>
