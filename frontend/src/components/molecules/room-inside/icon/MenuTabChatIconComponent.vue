<template>
  <svg>
    <menu-tab-svg-path-component
      d="M12 28.05h15.65v-3H12Zm0-6.5h24v-3H12Zm0-6.5h24v-3H12ZM4 44V7q0-1.15.9-2.075Q5.8 4 7 4h34q1.15 0 2.075.925Q44 5.85 44 7v26q0 1.15-.925 2.075Q42.15 36 41 36H12Zm3-7.25L10.75 33H41V7H7ZM7 7v29.75Z"
    ></menu-tab-svg-path-component>
  </svg>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import MenuTabSvgPathComponent from "@components/atoms/room-inside/MenuTabSvgPathComponent.vue";
export default defineComponent({
  components: { MenuTabSvgPathComponent },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style></style>
