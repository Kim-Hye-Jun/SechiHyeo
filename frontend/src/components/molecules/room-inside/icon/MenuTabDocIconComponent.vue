<template>
  <svg>
    <menu-tab-svg-path-component
      d="M16.65 28.65h3v-4.5q0-1.2.8-2t2-.8h4.05v3.5l5-5-5-5v3.5h-4.05q-2.4 0-4.1 1.7-1.7 1.7-1.7 4.1ZM3.5 42q-.65 0-1.075-.425Q2 41.15 2 40.5q0-.65.425-1.075Q2.85 39 3.5 39h41q.65 0 1.075.425Q46 39.85 46 40.5q0 .65-.425 1.075Q45.15 42 44.5 42ZM7 36q-1.2 0-2.1-.9Q4 34.2 4 33V9q0-1.2.9-2.1Q5.8 6 7 6h34q1.2 0 2.1.9.9.9.9 2.1v24q0 1.2-.9 2.1-.9.9-2.1.9Zm0-3h34V9H7v24Zm0 0V9v24Z"
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
