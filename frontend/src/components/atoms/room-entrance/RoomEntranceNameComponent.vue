<template>
  <text>{{ roomTitle }}</text>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  props: {
    roomTitle: String,
  },
  components: {},
  data() {
    return {
      roomName: "test",
    };
  },
  methods: {},
});
</script>
