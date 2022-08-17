<template>
  <span
    >{{ roomInfo.curNumOfPeople }} {{ "명" }} /{{ roomInfo.maxNumOfPeople }}
    {{ "명" }}</span
  >
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  props: {
    roomInfo: Object,
  },
  data() {
    return {
      currentNumOfPeople: 0,
      totalNumOfPeople: 0,
    };
  },
});
</script>

<style scoped></style>
