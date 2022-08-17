<template>
  <div class="card">
    <!-- <div class="flex-container"> -->
    <room-entrance-thumbnail-component
      class="flex-item"
      :thumbnailSrc="thumbNailPath()"
    ></room-entrance-thumbnail-component>
    <room-entrance-name-component
      class="flex-item"
      :roomTitle="roomInfo.roomTitle"
    ></room-entrance-name-component>
    <room-entrance-info-component
      class="flex-item"
      :roomInfo="roomInfo"
    ></room-entrance-info-component>
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
  methods: {
    thumbNailPath() {
      console.log(this.roomInfo.thumbnail);

      if (this.roomInfo.thumbnail) {
        return this.roomInfo.thumbnail;
      }
      return "@/assets/basic.jpg";
    },
  },
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
  width: 15em;
  height: 10em;
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
/* ###################################### */
.modal-window {
  position: fixed;
  background-color: rgba(255, 0, 0, 0.25);
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 999;
  visibility: hidden;
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s;
}
.modal-window:target {
  visibility: visible;
  opacity: 1;
  pointer-events: auto;
}
.modal-window > div {
  width: 400px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 2em;
  background: white;
}
.modal-window header {
  font-weight: bold;
}
.modal-window h1 {
  font-size: 150%;
  margin: 0 0 15px;
}

.modal-close {
  color: #aaa;
  line-height: 50px;
  font-size: 80%;
  position: absolute;
  right: 0;
  text-align: center;
  top: 0;
  width: 70px;
  text-decoration: none;
}
.modal-close:hover {
  color: black;
}

/* Demo Styles */
html,
body {
  height: 100%;
}

html {
  font-size: 18px;
  line-height: 1.4;
}

body {
  font-family: apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans,
    Ubuntu, Cantarell, "Helvetica Neue", sans-serif;
  font-weight: 600;
  background-image: linear-gradient(to right, #7f53ac 0, #657ced 100%);
  color: black;
}

a {
  color: inherit;
  text-decoration: none;
}

.container {
  display: grid;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.modal-window > div {
  border-radius: 1rem;
}

.modal-window div:not(:last-of-type) {
  margin-bottom: 15px;
}

.logo {
  max-width: 150px;
  display: block;
}

small {
  color: lightgray;
}

.btn {
  background-color: white;
  padding: 1em 1.5em;
  border-radius: 0.5rem;
  text-decoration: none;
}
.btn i {
  padding-right: 0.3em;
}

.modal__content__container {
  display: flex;
  flex-direction: column;
}
</style>
