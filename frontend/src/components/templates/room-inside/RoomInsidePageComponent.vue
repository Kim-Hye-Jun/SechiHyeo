<template>
  <div class="flexA">
    <debate-topic-component></debate-topic-component>
    <debate-title-tab-component
      class="room__inside__class1"
    ></debate-title-tab-component>
    <room-video-component class="room__inside__class2"></room-video-component>
    <menu-tab-component class="room__inside__class3"></menu-tab-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import DebateTitleTabComponent from "@components/molecules/room-inside/DebateTitleTabComponent.vue";
import RoomVideoComponent from "@components/organisms/room-inside/RoomVideoComponent.vue";
import MenuTabComponent from "@components/organisms/room-inside/MenuTabComponent.vue";
import DebateTopicComponent from "@components/atoms/room-inside/DebateTopicComponent.vue";

export default defineComponent({
  components: {
    RoomVideoComponent,
    MenuTabComponent,
    DebateTitleTabComponent,
    DebateTopicComponent,
  },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
body {
  margin: 0;
  background: #0e0e23;
}
.flexA {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* align-content: center; */
  height: 100%;
}
.room__inside__class1 {
  box-sizing: border-box;
  width: 100%;
  padding: 20px;
}
.room__inside__class2 {
  position: relative;
  width: 100%;
}
.room__inside__class3 {
  width: 100%;
  height: 50px;
  background: #0e0e23;
}
</style>
