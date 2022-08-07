<template>
  <svg>
    <menu-tab-svg-path-component
      d="M11.9 43q-1.2 0-2.05-.85Q9 41.3 9 40.1V7.9q0-1.2.85-2.05Q10.7 5 11.9 5h17.35L39 14.75V40.1q0 1.2-.85 2.05-.85.85-2.05.85Zm16.25-27.25v-8.5H11.9q-.25 0-.45.2t-.2.45v32.2q0 .25.2.45t.45.2h24.2q.25 0 .45-.2t.2-.45V15.75Zm-16.9-8.5v8.5-8.5V40.75 7.25Z"
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
