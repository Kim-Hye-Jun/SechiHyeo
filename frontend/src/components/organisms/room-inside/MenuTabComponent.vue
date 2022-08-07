<template>
  <div class="table">
    <menu-tab-mic-icon-component class="border"></menu-tab-mic-icon-component>
    <menu-tab-cam-icon-component class="border"></menu-tab-cam-icon-component>
    <menu-tab-doc-icon-component class="border"></menu-tab-doc-icon-component>
    <menu-tab-chat-icon-component class="border"></menu-tab-chat-icon-component>
    <menu-tab-share-icon-component
      class="border"
    ></menu-tab-share-icon-component>
    <menu-tab-exit-icon-component class="border"></menu-tab-exit-icon-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MenuTabMicIconComponent from "@/components/molecules/room-inside/icon/MenuTabMicIconComponent.vue";
import MenuTabDocIconComponent from "@/components/molecules/room-inside/icon/MenuTabDocIconComponent.vue";
import MenuTabCamIconComponent from "@/components/molecules/room-inside/icon/MenuTabCamIconComponent.vue";
import MenuTabChatIconComponent from "@components/molecules/room-inside/icon/MenuTabChatIconComponent.vue";
import MenuTabExitIconComponent from "@/components/molecules/room-inside/icon/MenuTabExitIconComponent.vue";
import MenuTabShareIconComponent from "@/components/molecules/room-inside/icon/MenuTabShareIconComponent.vue";

export default defineComponent({
  components: {
    MenuTabMicIconComponent,
    MenuTabDocIconComponent,
    MenuTabCamIconComponent,
    MenuTabChatIconComponent,
    MenuTabExitIconComponent,
    MenuTabShareIconComponent,
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
  background: #252954;
}
svg {
  width: 45px;
  height: 45px;
  fill: currentColor;
  color: #9b9ca7;
  cursor: pointer;
}

svg active,
svg:hover {
  color: #4255d4;
}

.border {
  padding-top: 1em;
  padding-left: 3em;
  /* padding-bottom: ; */
}
</style>
