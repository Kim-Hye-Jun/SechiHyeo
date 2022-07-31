<template>
  <div class="my-container grid">
    <room-entrance-component>d</room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
    <room-entrance-component></room-entrance-component>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import RoomEntranceComponent from "@components/molecules/room-entrance/RoomEntranceComponent.vue";
// import ContainerComponent from "@components/atoms/common/ContainerComponent.vue";
export default defineComponent({
  components: { RoomEntranceComponent },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
.grid {
  background: black;
  position: relative;
  z-index: -2;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  opacity: 1;
  row-gap: 5em;
  column-gap: 3em;
}
</style>
