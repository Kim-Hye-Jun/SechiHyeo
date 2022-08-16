<template>
  <div class="flex">
    <room-entrance-search-input-component
      @getSearchValue="updateRooms"
    ></room-entrance-search-input-component>
    <suspense>
      <room-entrance-board-component></room-entrance-board-component>
    </suspense>
    <button-component
      buttonName="Create"
      @click="state.isRoomMakeModalView = true"
    ></button-component>
    <room-make-modal-component
      v-if="state.isRoomMakeModalView"
    ></room-make-modal-component>
    <!-- <common-modal-component></common-modal-component> -->
  </div>
</template>
<script lang="ts">
import { defineComponent, toRefs, reactive } from "vue";
import RoomEntranceBoardComponent from "@components/organisms/room-entrance/RoomEntranceBoardComponent.vue";
import RoomEntranceSearchInputComponent from "@components/atoms/room-entrance/RoomEntranceSearchInputComponent.vue";
import ButtonComponent from "@/components/atoms/common/ButtonComponent2.vue";
import RoomMakeModalComponent from "@/components/organisms/room-entrance/RoomMakeModalComponent.vue";
// import CommonModalComponent from "@components/atoms/common/CommonModal.vue";

import http from "@/http";
import { hasChanged } from "@vue/shared";

export default defineComponent({
  components: {
    RoomEntranceBoardComponent,
    RoomEntranceSearchInputComponent,
    ButtonComponent,
    RoomMakeModalComponent,
    // CommonModalComponent,
  },
  setup() {
    let state = reactive({
      isRoomMakeModalView: false,
    });

    return {
      state,
    };
  },
  methods: {
    openModal() {
      document.location.href = "#open-modal";
    },
    updateRooms(searchValue: string) {
      //(#구현해야할것) searchValue로 방검색 axios 요청 보낸 후 room-entrance-board-component 갱신
    },
  },
});
</script>

<style scoped>
body {
  background: rgb(7, 8, 78);
}

.flex {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
