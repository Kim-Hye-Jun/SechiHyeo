<template>
  <div class="menu__tab__table">
    <menu-tab-mic-icon-component
      v-if="mic"
      class="svg"
      @click="micOff"
    ></menu-tab-mic-icon-component>
    <menu-tab-mic-off-icon-component
      v-else
      class="svg"
      @click="micOn"
    ></menu-tab-mic-off-icon-component>
    <menu-tab-cam-icon-component
      class="svg"
      v-if="cam"
      @click="camOff"
    ></menu-tab-cam-icon-component>
    <menu-tab-cam-off-icon-component
      class="svg"
      v-else
      @click="camOn"
    ></menu-tab-cam-off-icon-component>
    <menu-tab-doc-icon-component
      class="svg"
      @click="screenShare"
    ></menu-tab-doc-icon-component>
    <!-- <menu-tab-chat-icon-component class="svg"></menu-tab-chat-icon-component> -->
    <input
      type="file"
      id="file"
      ref="selectFile"
      @change="previewFile"
      style="display: none"
    />
    <menu-tab-share-icon-component
      class="svg"
      @click="clickButton"
    ></menu-tab-share-icon-component>
    <menu-tab-exit-icon-component
      class="svg"
      @click="exitRoom"
    ></menu-tab-exit-icon-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MenuTabMicIconComponent from "@/components/molecules/room-inside/icon/MenuTabMicIconComponent.vue";
import MenuTabDocIconComponent from "@/components/molecules/room-inside/icon/MenuTabDocIconComponent.vue";
import MenuTabCamIconComponent from "@/components/molecules/room-inside/icon/MenuTabCamIconComponent.vue";
// import MenuTabChatIconComponent from "@components/molecules/room-inside/icon/MenuTabChatIconComponent.vue";
import MenuTabExitIconComponent from "@/components/molecules/room-inside/icon/MenuTabExitIconComponent.vue";
import MenuTabShareIconComponent from "@/components/molecules/room-inside/icon/MenuTabShareIconComponent.vue";
import MenuTabMicOffIconComponent from "@/components/molecules/room-inside/icon/MenuTabMicOffIconComponent.vue";
import MenuTabCamOffIconComponent from "@/components/molecules/room-inside/icon/MenuTabCamOffIconComponent.vue";

import { useStore } from "vuex";
import http from "@/http";
import { member2 } from "@/api/index";

export default defineComponent({
  components: {
    MenuTabMicIconComponent,
    MenuTabDocIconComponent,
    MenuTabCamIconComponent,
    // MenuTabChatIconComponent,
    MenuTabExitIconComponent,
    MenuTabShareIconComponent,
    MenuTabMicOffIconComponent,
    MenuTabCamOffIconComponent,
  },
  setup() {
    const store = useStore();
    return { store };
  },
  data() {
    return {
      selectFile: null,
      previewImgUrl: null,
      fileIndex: -1,
      mic: true,
      cam: true,
    };
  },
  mounted() {
    console.log("session mounted menutab :", this.store.state.session);

    this.store.state.session?.on("signal:image-share", (event: any) => {
      console.log("?");
      (document.getElementById("shareImg") as HTMLImageElement).src =
        event.data;
    });
  },
  methods: {
    camOn() {
      this.cam = true;
      this.store.state.publisher.publishVideo(true);
    },
    camOff() {
      this.cam = false;
      this.store.state.publisher.publishVideo(false);
    },
    micOn() {
      this.mic = true;
      this.store.state.publisher.publishAudio(true);
    },
    micOff() {
      this.mic = false;
      this.store.state.publisher.publishAudio(false);
    },
    exitRoom() {
      member2.get(`/sessions/${this.store.state.roomId}/disconnect`);

      if (this.store.state.session) this.store.state.session.disconnect();
      this.store.state.session = undefined;
      // mainStreamManager = undefined;
      // publisher = undefined;
      // subscribers.value = [];
      // OVCamera = undefined;
      // mapUserClassName.value = new Map();
      // mapClassNameUser.value = new Map();
      this.$router.push({
        name: "RoomEntrancePage",
      });
    },
    screenShare() {
      console.log("화면 공유");
      if (this.store.state.selectedFileIndex > -1) {
        let proof = new FormData();
        proof.append(
          "proof",
          this.store.state.uploadImageFileArr[
            this.store.state.selectedFileIndex
          ]
        );
        http
          .post("https://i7a508.p.ssafy.io/api/sessions/uploadProof", proof, {
            headers: {
              "Content-Type": "multipart/form-data",
              "access-token": this.store.state.token,
            },
          })
          .then((res) => {
            console.log(res);
            // 시그널
            console.log("singal");
            console.log(this.store.state.session);
            this.store.state.session?.signal({
              data: res.data,
              to: [],
              type: "image-share",
            });
          });
      }

      // console.log("next session : ", this.sessionScreen);
      // console.log("next oV : ", this.OVScreen);
      // console.log("next token : ", this.tokenScreen);
      // this.sessionScreen
      //   ?.connect(this.tokenScreen)
      //   .then(() => {
      //     let publisher = this.OVScreen?.initPublisher(
      //       document.getElementById("shareImg"),
      //       {
      //         videoSource: "screen",
      //       }
      //     );
      //     console.log("PUBLISHER INITPUBLISHER", publisher);
      //     publisher.once("accessAllowed", (error: any) => {
      //       publisher.stream
      //         .getMediaStream()
      //         .getVideoTracks()[0]
      //         .addEventListener("ended", () => {
      //           console.log('User pressed the "Stop sharing" button');
      //         });
      //       this.sessionScreen?.publish(publisher);
      //     });

      //     publisher.once("accessDenied", (error: any) => {
      //       console.warn("ScreenShare: Access Denied");
      //     });
      //   })
      //   .catch((error: any) => {
      //     console.log(error);
      //     console.warn(
      //       "There was an error connecting to the session:",
      //       error.code,
      //       error.message
      //     );
      //   });
    },
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
            this.store.state.uploadImageFileArr.push(this.selectFile);
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
.menu__tab__table {
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

  margin-top: 15px;
  margin-left: 50px;
}

.svg active,
.svg:hover {
  color: #4255d4;
}

/* .border {
  padding-top: 1em;
  padding-left: 3em;
  /* padding-bottom: ; */
/* } */
</style>
