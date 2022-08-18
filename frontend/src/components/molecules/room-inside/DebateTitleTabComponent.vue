<template>
  <div class="debateSide__flex__container">
    <div class="red side">{{ sideA }}</div>
    &nbsp;&nbsp;
    <div class="blue side">{{ sideB }}</div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  components: {},
  props: {
    sideA: String,
    sideB: String,
  },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style scoped>
.debateSide__flex__container {
  display: flex;
  flex-direction: row;
  width: 80vw;
  justify-content: center;
  align-items: center;
}
.side {
  width: 50%;
  height: 2em;
  border-radius: 0.2em;

  text-align: center;
  font-size: 30px;
  color: #3a3897;

  padding-top: 7px;
}
.red {
  background: #ffe2e2;
}
.blue {
  background: #e1eeff;
}
</style>
