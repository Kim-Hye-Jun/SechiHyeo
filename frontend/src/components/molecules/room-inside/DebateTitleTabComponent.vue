<template>
  <div class="flexB">
    <div class="red side"></div>
    <div class="blue side"></div>
  </div>
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
.flexB {
  display: flex;
  flex-direction: row;
  width: 80vw;
}
.side {
  width: 50%;
  height: 3em;
  border-radius: 0.2em;
}
.red {
  background: rgb(59, 8, 25);
  background: linear-gradient(
    90deg,
    rgba(59, 8, 25, 1) 0%,
    rgba(112, 24, 44, 1) 54%,
    rgba(30, 39, 57, 1) 100%
  );
}
.blue {
  background: rgb(8, 48, 59);
  background: linear-gradient(
    90deg,
    rgba(8, 48, 59, 1) 0%,
    rgba(24, 50, 112, 1) 54%,
    rgba(8, 43, 40, 1) 100%
  );
  border: none;
}
</style>
