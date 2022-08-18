<template>
  <div class="container"></div>
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
  //   created() {},
  //   mounted() {},
  methods: {},
});
</script>

<style lang="scss" scoped>
.container {
  background: #1a1d3a;
  background: linear-gradient(45deg, #1a1d3a 0%, #212752 100%);
  padding: 1em;
  border-radius: 0.3em;
}
</style>
