<template>
  <img
    style="height: 80%"
    class="thumbnail"
    :src="thumbnailSrc"
    alt="서버에서 이미지를 불러오지 못했습니다."
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
  /* height: 175px; */
}
</style>
