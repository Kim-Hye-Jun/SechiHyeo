<template>
  <div class="table">
    <menu-tab-mic-icon-component
      class="svg border"
    ></menu-tab-mic-icon-component>
    <menu-tab-cam-icon-component
      class="svg border"
    ></menu-tab-cam-icon-component>
    <menu-tab-doc-icon-component
      class="svg border"
    ></menu-tab-doc-icon-component>
    <menu-tab-chat-icon-component
      class="svg border"
    ></menu-tab-chat-icon-component>
    <input
      type="file"
      id="file"
      ref="selectFile"
      @change="previewFile"
      style="display: none"
    />
    <menu-tab-share-icon-component
      class="svg border"
      @click="clickButton"
    ></menu-tab-share-icon-component>
    <menu-tab-exit-icon-component
      class="svg border"
    ></menu-tab-exit-icon-component>
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

import { useStore } from "vuex";

export default defineComponent({
  components: {
    MenuTabMicIconComponent,
    MenuTabDocIconComponent,
    MenuTabCamIconComponent,
    MenuTabChatIconComponent,
    MenuTabExitIconComponent,
    MenuTabShareIconComponent,
  },
  setup() {
    const store = useStore();
    return { store };
  },
  data() {
    return {
      selectFile: null,
      previewImgUrl: null,
    };
  },
  methods: {
    clickButton() {
      console.log("click", document.getElementById("file"));
      document.getElementById("file")?.click();
    },
    previewFile() {
      if (
        0 < ((this.$refs["selectFile"] as any)["files"]["length"] as number)
      ) {
        this.selectFile = (this.$refs["selectFile"] as any)["files"][0] as null;
        let fileExt = ((this.selectFile as any)["name"] as string).substring(
          ((this.selectFile as any)["name"] as string).lastIndexOf(".") + 1
        );
        fileExt = fileExt.toLowerCase();
        if (
          ["jpeg", "png", "gif", "bmp"].includes(fileExt) &&
          this.selectFile != null &&
          this.selectFile["size"] <= 1048576
        ) {
          var reader = new FileReader();
          reader.onload = (e: any) => {
            console.log(e.target.result);
            this.store.state.uploadImageArr.push(e.target.result);
            console.log(this.store.state.uploadImageArr);
          };
          reader.readAsDataURL(this.selectFile);
        }
      }
    },
  },
});
</script>

<style scoped>
.table {
  width: 500px;
  background: #252954;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.svg {
  width: 45px;
  height: 45px;
  fill: currentColor;
  color: #9b9ca7;
  cursor: pointer;
}

.svg active,
.svg:hover {
  color: #4255d4;
}

.border {
  padding-top: 1em;
  padding-left: 3em;
  /* padding-bottom: ; */
}
</style>
