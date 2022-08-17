<template>
  <div class="debateSide__flex__container">
    <div class="red side">{{ sideA }}</div>
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
  color: white;
}
.red {
  background: rgb(252 68 68);
}
.blue {
  background: #5ba5ff;
}
</style>
