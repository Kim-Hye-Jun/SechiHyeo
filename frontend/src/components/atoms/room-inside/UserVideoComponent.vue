<template>
  <video autoplay></video>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
video {
  background: black;
  border-radius: 10px;
  padding: 5px;
  box-sizing: border-box;
  background-clip: content-box;
}
</style>
