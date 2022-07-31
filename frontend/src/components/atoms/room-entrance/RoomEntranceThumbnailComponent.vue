<template>
  <img
    class="thumbnail"
    src="/img/icons/android-chrome-512x512.png"
    alt="Grapefruit slice atop a pile of other slices"
  />
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  data() {
    return {
      room_no: 0,
    };
  },
  methods: {},
});
</script>

<style>
.thumbnail {
  /* border: 3px solid black; */
  /* border-radius: 1em; */
}
</style>
