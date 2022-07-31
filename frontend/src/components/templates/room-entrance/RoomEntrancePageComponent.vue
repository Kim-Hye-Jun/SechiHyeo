<template>
  <div>
    <room-entrance-search-input-component></room-entrance-search-input-component>
    <room-entrance-board-component></room-entrance-board-component>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import RoomEntranceBoardComponent from "@components/organisms/room-entrance/RoomEntranceBoardComponent.vue";
import RoomEntranceSearchInputComponent from "@components/atoms/room-entrance/RoomEntranceSearchInputComponent.vue";

export default defineComponent({
  components: {
    RoomEntranceBoardComponent,
    RoomEntranceSearchInputComponent,
  },
  data() {
    return {
      example: "",
    };
  },
  //   created() {},
  //   mounted() {},
  methods: {},
});
</script>
