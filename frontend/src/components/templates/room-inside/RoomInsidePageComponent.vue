<template>
  <div class="flexA">
    <debate-topic-component></debate-topic-component>
    <debate-title-tab-component
      class="room__inside__class1"
    ></debate-title-tab-component>
    <suspense>
      <room-video-component
        class="room__inside__class2"
        :publisher="publisher"
        :subscribers="subscribers"
        :roomAndUserData="testReturnData"
        :userSideOrderMap="mapUserClassName"
      ></room-video-component>
    </suspense>
    <menu-tab-component class="room__inside__class3"></menu-tab-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import DebateTitleTabComponent from "@components/molecules/room-inside/DebateTitleTabComponent.vue";
import RoomVideoComponent from "@components/organisms/room-inside/RoomVideoComponent.vue";
import MenuTabComponent from "@components/organisms/room-inside/MenuTabComponent.vue";
import DebateTopicComponent from "@components/atoms/room-inside/DebateTopicComponent.vue";

import http from "@/http";
import * as openVidu from "openvidu-browser";
import { RoomJoinResponseInfo } from "@type/types";
import { useRoute } from "vue-router";

export default defineComponent({
  components: {
    RoomVideoComponent,
    MenuTabComponent,
    DebateTitleTabComponent,
    DebateTopicComponent,
  },
  async setup(): Promise<any> {
    // 뭘 반응형으로 설정해야 할지...?
    let OV: openVidu.OpenVidu | undefined = new openVidu.OpenVidu();
    let session: openVidu.Session | undefined = OV.initSession();
    let subscribers: openVidu.Subscriber[] = [];
    let testReturnData: RoomJoinResponseInfo | undefined = undefined;

    let mapUserClassName = new Map();

    const apiCall = async () => {
      try {
        console.log("here");
        testReturnData = (
          await http.get(`/sessions/${useRoute().params.roomId}/connection`)
        ).data;
        console.log(testReturnData?.token);
        console.log(testReturnData);
      } catch (err) {
        console.log(err);
      }
    };
    await apiCall();

    let mainStreamManager: openVidu.Publisher | undefined = undefined;
    let publisher: openVidu.Publisher | undefined = undefined;

    session.on("streamCreated", ({ stream }) => {
      if (session) {
        const subscriber = session.subscribe(
          stream,
          undefined as unknown as HTMLElement
        );
        console.log("subscript create @@ ", subscriber);
        mapUserClassName.set(
          JSON.parse(subscriber.stream.connection.data)["userId"],
          testReturnData?.userSideOrder
        );
        subscribers.push(subscriber);
      }
    });

    session.on("streamDestroyed", ({ stream }) => {
      // Remove the stream from 'subscribers' array
      const index = subscribers.indexOf(
        stream.streamManager as openVidu.Subscriber,
        0
      );
      mapUserClassName.delete(
        JSON.parse(subscribers[index].stream.connection.data)["userId"]
      );
      if (index >= 0) {
        subscribers.splice(index, 1);
      }
    });

    session.on("exception", (exception) => {
      console.warn(exception);
    });

    // // 2번째 매개변수에 userInfo넣기
    console.log(testReturnData);
    if (testReturnData === undefined) return;
    console.log("no return");
    await session
      .connect(testReturnData["token"], testReturnData)
      .then(() => {
        // --- Get your own camera stream with the desired properties ---
        if (OV) {
          publisher = OV.initPublisher(undefined as unknown as HTMLElement, {
            audioSource: undefined, // The source of audio. If undefined default microphone
            videoSource: undefined, // The source of video. If undefined default webcam
            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
            publishVideo: true, // Whether you want to start publishing with your video enabled or not
            resolution: "640x480", // The resolution of your video
            frameRate: 30, // The frame rate of your video
            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
            mirror: false, // Whether to mirror your local video or not
          });
          console.log("pub create : ", publisher);

          if (session) {
            session.publish(publisher as openVidu.Publisher);
          }

          mainStreamManager = publisher;
          // --- Publish your stream ---
        }
      })
      .catch((error) => {
        console.log(
          "There was an error connecting to the session:",
          error.code,
          error.message
        );
      });

    function leaveSession() {
      if (session) session.disconnect();
      session = undefined;
      mainStreamManager = undefined;
      publisher = undefined;
      subscribers = [];
      OV = undefined;
      mapUserClassName = new Map();

      window.removeEventListener("beforeunload", leaveSession);
      window.removeEventListener("beforeunload", removeUser);
    }

    function removeUser() {
      // 서버에서 session : token 제거
    }

    window.addEventListener("beforeunload", leaveSession);
    window.addEventListener("beforeunload", removeUser);
    console.log("...?");
    console.log("pub : ", publisher);
    console.log("sub : ", subscribers);
    return {
      publisher,
      testReturnData,
      subscribers,
      mapUserClassName,
    };
  },
});
</script>

<style>
body {
  margin: 0;
  background: #0e0e23;
}
.flexA {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* align-content: center; */
  height: 100%;
}
.room__inside__class1 {
  box-sizing: border-box;
  width: 100%;
  padding: 20px;
}
.room__inside__class2 {
  position: relative;
  width: 100%;
}
.room__inside__class3 {
  width: 100%;
  height: 50px;
  background: #0e0e23;
}
</style>
