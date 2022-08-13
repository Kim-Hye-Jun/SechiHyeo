<template>
  <img
    class="thumbnail"
    :src="thumbnailSrc"
    alt="Grapefruit slice atop a pile of other slices"
  />
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    thumbnailSrc: String,
  },
  data() {
    return {
      room_no: 0,
    };
  },
  methods: {},
});
</script>

<style scoped>
.thumbnail {
  /* border: 3px solid black; */
  /* border-radius: 1em; */
}
</style>
