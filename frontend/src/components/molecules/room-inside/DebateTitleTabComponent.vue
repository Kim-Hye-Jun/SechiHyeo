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

<style scoped>
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
  background: rgb(197, 175, 50);
}
.blue {
  background: #4255d4;
}
</style>
