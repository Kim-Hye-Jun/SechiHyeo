<template>
  <div>
    <div class="main list-container contents">
      <h1 class="page-header">세치혀 입니다.</h1>
      <img class="page-header" src="../assets/logo.png" />
      <div class="form-wrapper form-wrapper-sm">
        <form @submit.prevent class="form">
          <div>
            <label for="sessionName">세션 이름:</label>
            <input
              id="sessionName"
              type="text"
              v-model="mySessionId"
              placeholder="세션 이름을 입력하세요."
            />
            <label for="NickName">닉네임:</label>
            <input
              id="NickName"
              type="text"
              v-model="nickName"
              placeholder="닉네임을 입력하세요."
            />
          </div>
          <button type="button" class="btn" @click="joinSession()">
            세션 참가하기
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
// import { mapState } from "vuex";
import { OPENVIDU_SERVER_URL, OPENVIDU_SERVER_SECRET } from "@/config/index";

export default defineComponent({
  setup() {
    const store = useStore();

    return { store };
  },
  data() {
    return {
      mySessionId: "SessionA",
      nickName: "",
    };
  },
  computed: {},
  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.store.state.OV = new OpenVidu();

      // console.log(this.store.state.OV);

      this.store.state.session = this.store.state.OV.initSession();

      // console.log(this.store.state.session);

      this.store.state.myNickName = this.nickName;

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.store.state.session.on("streamCreated", ({ stream }: any) => {
        const subscriber = this.store.state.session.subscribe(stream);
        this.store.state.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.store.state.session.on("streamDestroyed", ({ stream }: any) => {
        const index = this.store.state.subscribers.indexOf(
          stream.streamManager,
          0
        );
        if (index >= 0) {
          this.store.state.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.store.state.session.on("exception", ({ exception }: any) => {
        console.warn(exception);
      });

      this.store.state.session.on("signal:countdown", (event: any) => {
        console.log(event);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.store.state.session
          .connect(token, { clientData: this.store.state.myNickName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.store.state.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.store.state.mainStreamManager = publisher;
            this.store.state.publisher = publisher;

            // --- Publish your stream ---

            this.store.state.session.publish(this.store.state.publisher);
          })
          .catch((error: any) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);

      this.$router.push({
        name: "Room",
        params: { joinCode: this.mySessionId },
      });
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.store.state.session) this.store.state.session.disconnect();

      this.store.state.session = undefined;
      this.store.state.mainStreamManager = undefined;
      this.store.state.publisher = undefined;
      this.store.state.subscribers = [];
      this.store.state.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    //세션 이름 넣으면 토큰을 주는 함수
    getToken(mySessionId: string) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },
    // 세션 생성 함수(우리가 정한 방 이름으로 세션 아이디 생성)
    // 생성된 아이디 리턴
    createSession(sessionId: string) {
      return new Promise((resolve, reject) => {
        console.log(sessionId);

        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // 토큰 생성 함수(서버에서 생성한 세션 ID로 생성) 원래 서버단에서 구현해야함
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId: any) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
});
</script>
<style scoped>
img {
  display: block;
  margin: 0px auto;
}
</style>
