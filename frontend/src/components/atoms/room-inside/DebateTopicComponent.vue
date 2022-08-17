<template>
  <div class="topic">{{ debateTopic }}</div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  props: {
    debateTopic: String,
  },
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
.topic {
  width: 100%;
  height: 50px;
  background: radial-gradient(circle, #141834 0%, #13162f 100%);
  color: #ffffff;
  text-align: center;
  font-size: 40px;
}
</style>
