<template>
  <div class="card">
    <!-- <div class="flex-container"> -->
    <room-entrance-thumbnail-component
      class="flex-item"
      :thumbnailSrc="roomInfo.thumbnail"
    ></room-entrance-thumbnail-component>
    <room-entrance-name-component
      class="flex-item"
      :roomTitle="roomInfo.roomTitle"
    ></room-entrance-name-component>
    <room-entrance-info-component
      class="flex-item"
    ></room-entrance-info-component>
    <!-- </div> -->
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import RoomEntranceThumbnailComponent from "@components/atoms/room-entrance/RoomEntranceThumbnailComponent.vue";
import RoomEntranceNameComponent from "@components/atoms/room-entrance/RoomEntranceNameComponent.vue";
import RoomEntranceInfoComponent from "@components/atoms/room-entrance/RoomEntranceInfoComponent.vue";

export default defineComponent({
  components: {
    RoomEntranceThumbnailComponent,
    RoomEntranceNameComponent,
    RoomEntranceInfoComponent,
  },
  props: ["roomInfo"],
  setup(props) {
    if (props.roomInfo) {
      console.log("PROPS :: ", props.roomInfo);
    }
  },
  methods: {},
});
</script>

<style scoped>
.flex-container {
  width: 80%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.flex-item {
  text-align: center;
  border-radius: 5px;
  width: 12em;
  height: auto;
  overflow: hidden;
  color: #5ddcff;
}
@property --rotate {
  syntax: "<angle>";
  initial-value: 132deg;
  inherits: false;
}

.card {
  background: #191c29;
  width: 12em;
  height: 8em;
  margin: 3em;
  position: relative;
  border-radius: 6px;
  justify-content: center;
  align-items: center;
  text-align: center;
  display: flex;
  font-size: 1.5em;
  color: rgb(88 199 250 / 0%);
  cursor: pointer;
  font-family: cursive;
  flex-direction: column;
  align-items: center;

  /* border: 5px solid transparent;
  border-radius: 20px;
  background-image: linear-gradient(#444444, #444444),
    linear-gradient(to right, #fbfcb9be, #ffcdf3aa, #65d3ffaa);
  background-origin: border-box;
  background-clip: content-box, border-box; */
}

.card::before {
  content: "";
  width: 104%;
  height: 104%;
  border-radius: 8px;
  background-image: linear-gradient(
    var(--rotate),
    #5ddcff,
    #3c67e3 43%,
    #4e00c2
  );
  position: absolute;
  z-index: -1;
  top: -2%;
  left: -2%;
  animation: spin 2.5s linear infinite;
}

@keyframes spin {
  0% {
    --rotate: 0deg;
  }
  100% {
    --rotate: 360deg;
  }
}
</style>
