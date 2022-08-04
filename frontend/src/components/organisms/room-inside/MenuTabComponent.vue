<template>
  <div class="table">
    <menu-tab-mic-icon-component class="border"></menu-tab-mic-icon-component>
    <menu-tab-cam-icon-component class="border"></menu-tab-cam-icon-component>
    <menu-tab-doc-icon-component class="border"></menu-tab-doc-icon-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MenuTabMicIconComponent from "@/components/molecules/room-inside/icon/MenuTabMicIconComponent.vue";
import MenuTabDocIconComponent from "@/components/molecules/room-inside/icon/MenuTabDocIconComponent.vue";
import MenuTabCamIconComponent from "@/components/molecules/room-inside/icon/MenuTabCamIconComponent.vue";

export default defineComponent({
  components: {
    MenuTabMicIconComponent,
    MenuTabDocIconComponent,
    MenuTabCamIconComponent,
  },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
.table {
  width: 500px;
  background-color: #0e0e23;
}
svg {
  width: 50px;
  height: 50px;
  fill: currentColor;
  color: #9b9ca7;
  cursor: pointer;
}

svg active,
svg:hover {
  color: #4255d4;
}

.border {
  border-left: 1.5px solid rgb(44, 146, 187);
  color: rgb(35, 124, 132);
}
</style>
