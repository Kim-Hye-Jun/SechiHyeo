<template>
  <div class="grid">
    <user-video-component-vue class="a1"></user-video-component-vue>
    <user-video-component-vue class="a2"></user-video-component-vue>
    <user-video-component-vue class="a3"></user-video-component-vue>
    <user-video-component-vue class="b1"></user-video-component-vue>
    <user-video-component-vue class="b2"></user-video-component-vue>
    <user-video-component-vue class="b3"></user-video-component-vue>
    <div class="c"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import http from "@/http";
import * as openVidu from "openvidu-browser";
import UserVideoComponentVue from "@/components/atoms/room-inside/UserVideoComponent.vue";
export default defineComponent({
  components: { UserVideoComponentVue },
  data() {
    return {
      OV: undefined as openVidu.OpenVidu | undefined,
      session: undefined as openVidu.Session | undefined,
      mainStreamManager: undefined as openVidu.Publisher | undefined,
      publisher: undefined as openVidu.Publisher | undefined,
      subscribers: [] as openVidu.Subscriber[],
      token: this.getToken() as string,
    };
  },
  methods: {
    onVideo(): void {
      if (this.publisher) {
        this.publisher.publishVideo(true);
      }
    },
    offVideo(): void {
      if (this.publisher) {
        this.publisher.publishVideo(false);
      }
    },
    offMic(): void {
      if (this.publisher) {
        this.publisher.publishAudio(false);
      }
    },
    onMic(): void {
      if (this.publisher) {
        this.publisher.publishAudio(true);
      }
    },
    getToken(): string {
      // http.post("/api/token",{});
      // (#구현 해야 할것) 해당 sessionId로 token을 받아와서 return 해줘야 됨
      return "temp";
    },
    joinRoom() {
      // 방에 참여할 때, 이건 create때 해야됨
      // 참여할 때 연결 다 해놓고, 데이터 바인딩까지 해놓고 mount 때 DOM 생성할거임
      this.OV = new openVidu.OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", (event) => {
        if (this.session) {
          let subscriber = this.session.subscribe(
            event.stream,
            "video-container"
          );
          subscriber.on("videoElementCreated", (event) => {
            // appendUserData(event.element, subscriber.stream.connection);
          });
          this.subscribers.push(subscriber);
        }
      });

      this.session.on("streamDestroyed", (event) => {
        // removeUserData(event.stream.connection);
      });

      this.session.on("exception", (exception) => {
        console.warn(exception);
      });

      // 2번째 매개변수에 userInfo넣기
      this.session
        .connect(this.token, { clientData: "user" })
        .then(() => {
          // --- Get your own camera stream with the desired properties ---
          if (this.OV) {
            console.log("OV is not null");
            let publisher = this.OV.initPublisher("dd", {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            if (this.session) {
              console.log("session is not null");
              this.session.publish(this.publisher as openVidu.Publisher);
            }
          }
        })
        .catch((error) => {
          console.log(
            "There was an error connecting to the session:",
            error.code,
            error.message
          );
        });

      window.addEventListener("beforeunload", this.leaveSession);
      window.addEventListener("beforeunload", this.removeUser);
    },
    leaveSession(): void {
      if (this.session) this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      window.removeEventListener("beforeunload", this.removeUser);
    },
    removeUser(): void {
      // 서버에서 session : token 제거
    },
  },
});
</script>

<style>
.grid {
  background: radial-gradient(circle, #141834 0%, #13162f 100%);
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  /* gap: 1.5em; */
  grid-template-areas:
    "a1 c b1"
    "a2 c b2"
    "a3 c b3";
}
.a1 {
  width: 100%;
  grid-area: a1;
}
.a2 {
  width: 100%;
  grid-area: a2;
}
.a3 {
  width: 100%;
  grid-area: a3;
}
.b1 {
  width: 100%;
  grid-area: b1;
}
.b2 {
  width: 100%;
  grid-area: b2;
}
.b3 {
  width: 100%;
  grid-area: b3;
}
.c {
  width: 100%;
  grid-area: c;
  background: red;
  padding: 20px;
  box-sizing: border-box;
  background-clip: content-box;
}
</style>
