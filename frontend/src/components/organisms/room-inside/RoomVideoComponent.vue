<template>
  <div class="grid">
    <user-video-component-vue class="a1"></user-video-component-vue>
    <user-video-component-vue class="a2"></user-video-component-vue>
    <user-video-component-vue class="a3"></user-video-component-vue>
    <user-video-component-vue class="b1"></user-video-component-vue>
    <user-video-component-vue class="b2"></user-video-component-vue>
    <user-video-component-vue class="b3"></user-video-component-vue>
    <div class="c"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import UserVideoComponentVue from "@/components/atoms/room-inside/UserVideoComponent.vue";
export default defineComponent({
  components: { UserVideoComponentVue },
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
  background: blue;
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  /* gap: 20px; */
  grid-template-areas:
    "a1 c b1"
    "a2 c b2"
    "a3 c b3";
}
.a1 {
  width: 100%;
  grid-area: a1;
}
.a2 {
  width: 100%;
  grid-area: a2;
}
.a3 {
  width: 100%;
  grid-area: a3;
}
.b1 {
  width: 100%;
  grid-area: b1;
}
.b2 {
  width: 100%;
  grid-area: b2;
}
.b3 {
  width: 100%;
  grid-area: b3;
}
.c {
  width: 100%;
  grid-area: c;
  background: red;
}
</style>
