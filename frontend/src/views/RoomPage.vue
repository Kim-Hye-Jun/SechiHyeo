<template>
  <button type="button" @click="leaveSession(), moveMain()">
    Leave session
  </button>
  <div>
    <debate-timer></debate-timer>
    <user-video :stream-manager="this.store.state.publisher" />
    <user-video
      v-for="sub in this.store.state.subscribers"
      :key="sub.stream.connection.connectionId"
      :stream-manager="sub"
    />
    <debate-chat></debate-chat>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
// import axios from "axios";
// import { OpenVidu } from "openvidu-browser";
// import { mapState } from "vuex";
// import { OPENVIDU_SERVER_URL, OPENVIDU_SERVER_SECRET } from "@/config/index";
import UserVideo from "@/components/Video/UserVideo.vue";
import DebateTimer from "@/components/DebateTimer.vue";
import DebateChat from "@/components/DebateChat.vue";
export default defineComponent({
  setup() {
    const store = useStore();

    return { store };
  },
  computed: {},
  components: {
    UserVideo,
    DebateTimer,
    DebateChat,
  },

  methods: {
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.store.state.session) this.store.state.session.disconnect();

      this.store.state.session = undefined;
      this.store.state.mainStreamManager = undefined;
      this.store.state.publisher = undefined;
      this.store.state.subscribers = [];
      this.store.state.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);

      // this.$router.replace({
      //   name: "Main",
      // });
    },

    moveMain() {
      this.$router.replace({
        name: "Main",
      });
    },
  },
});
</script>
<style scoped></style>
