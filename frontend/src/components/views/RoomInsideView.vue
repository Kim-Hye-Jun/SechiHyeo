<template>
  <suspense>
    <room-inside-page-component></room-inside-page-component>
  </suspense>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import RoomInsidePageComponent from "../templates/room-inside/RoomInsidePageComponent.vue";

export default defineComponent({
  components: { RoomInsidePageComponent },
  methods: {},
});
</script>

<style scoped></style>
