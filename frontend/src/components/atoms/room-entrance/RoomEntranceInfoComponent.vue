<template>
  <span>{{ currentNumOfPeople }}/{{ totalNumOfPeople }}</span>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  data() {
    return {
      currentNumOfPeople: 0,
      totalNumOfPeople: 0,
    };
  },
});
</script>

<style scoped></style>
