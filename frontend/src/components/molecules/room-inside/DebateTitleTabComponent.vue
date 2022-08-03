<template>
  <div class="flex">
    <debate-camp-tab-component-vue
      class="redside"
    ></debate-camp-tab-component-vue>
    <debate-camp-tab-component-vue
      class="blueside"
    ></debate-camp-tab-component-vue>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import DebateCampTabComponentVue from "@/components/atoms/room-inside/DebateCampTabComponent.vue";
export default defineComponent({
  components: { DebateCampTabComponentVue },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
.flex {
  display: flex;
  flex-direction: row;
  width: 80vw;
}
.redside {
  background: rgb(59, 8, 25);
  background: linear-gradient(
    90deg,
    rgba(59, 8, 25, 1) 0%,
    rgba(112, 24, 44, 1) 54%,
    rgba(30, 39, 57, 1) 100%
  );
}
.blueside {
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
