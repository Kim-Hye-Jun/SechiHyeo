<template>
  <div class="flex">
    <room-entrance-search-input-component
      @getSearchValue="updateRooms"
    ></room-entrance-search-input-component>
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
  methods: {
    updateRooms(searchValue: string) {
      //(#구현해야할것) searchValue로 방검색 axios 요청 보낸 후 room-entrance-board-component 갱신
    },
  },
});
</script>

<style>
.flex {
  display: flex;
  flex-direction: column;
}
</style>
