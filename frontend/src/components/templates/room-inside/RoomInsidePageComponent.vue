<template>
  <div class="flexA">
    <debate-topic-component :debateTopic="debateTopic"></debate-topic-component>
    <debate-title-tab-component
      class="room__inside__class1"
      :sideA="sideA"
      :sideB="sideB"
    ></debate-title-tab-component>
    <debate-timer-component
      :roomAndUserData="testReturnData"
    ></debate-timer-component>
    <suspense>
      <room-video-component
        class="room__inside__class2"
        :subscribers="subscribers"
        :roomAndUserData="testReturnData"
        :userSideOrderMap="mapUserClassName"
        :emptyVideoClasses="emptyVideoArr"
      ></room-video-component>
    </suspense>
    <menu-tab-component class="room__inside__class3"></menu-tab-component>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, toRaw } from "vue";
import DebateTitleTabComponent from "@components/molecules/room-inside/DebateTitleTabComponent.vue";
import RoomVideoComponent from "@components/organisms/room-inside/RoomVideoComponent.vue";
import MenuTabComponent from "@components/organisms/room-inside/MenuTabComponent.vue";
import DebateTopicComponent from "@components/atoms/room-inside/DebateTopicComponent.vue";
import { member2 } from "@/api/index";
import DebateTimerComponent from "@components/organisms/room-inside/DebateTimerComponent.vue";

import http from "@/http";
import * as openVidu from "openvidu-browser";
import { RoomJoinResponseInfo, RoomJoinRequestInfo } from "@type/types";
import { useRoute } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  components: {
    RoomVideoComponent,
    MenuTabComponent,
    DebateTitleTabComponent,
    DebateTopicComponent,
    DebateTimerComponent,
  },
  async setup(): Promise<any> {
    const store = useStore();
    // 뭘 반응형으로 설정해야 할지...?
    let OVCamera: openVidu.OpenVidu | undefined = new openVidu.OpenVidu();
    store.state.session = OVCamera.initSession();

    // let OVScreen: openVidu.OpenVidu | undefined = new openVidu.OpenVidu();
    // let sessionScreen: openVidu.Session | undefined = OVScreen.initSession();

    const subscribers = ref([]);
    // let subscribers: openVidu.Subscriber[] = [];
    let testReturnData: RoomJoinResponseInfo | undefined = undefined;

    const mapUserClassName = ref(new Map());
    const mapClassNameUser = ref(new Map());

    const apiCall = async () => {
      try {
        console.log("here", `${useRoute().params.roomId}`);
        if (useRoute().params.side && useRoute().params.side) {
          const request: RoomJoinRequestInfo = {
            roomId: useRoute().params.roomId as string,
            side: useRoute().params.side as string,
            order: useRoute().params.order as unknown as number,
          };
          console.log("방 만들기 -> 방에 참가 request : ", request);
          testReturnData = (
            await member2.post(`/sessions/connection_select`, request)
          ).data;
          console.log(
            "testReturnData tokenCamera : ",
            testReturnData?.tokenCamera
          );
          console.log("testReturnData : ", testReturnData);
        } else {
          const request: RoomJoinRequestInfo = {
            roomId: useRoute().params.roomId as string,
            side: useRoute().params.side as string,
            order: useRoute().params.order as unknown as number,
          };
        }

        store.state.roomId = testReturnData?.roomId;
        store.state.isRoomHost = testReturnData?.roomHost;
        store.state.mySideOrder = testReturnData?.userSideOrder;
      } catch (err) {
        alert("이미 신청된 진영,순서 입니다.");
        return;
      }
    };
    await apiCall();
    const emptyVideoArr = ref([]);
    for (const data of (testReturnData as unknown as RoomJoinResponseInfo)[
      "emptySideOrderList"
    ]) {
      (emptyVideoArr.value as string[]).push(data);
    }

    let mainStreamManager: openVidu.Publisher | undefined = undefined;
    // let publisher: openVidu.Publisher | undefined = undefined;

    store.state.session.on("streamCreated", (event: any) => {
      // 1. 서버에 추가 요청 => x
      // 완료
      if (event.stream.typeOfVideo == "CAMERA") {
        if (store.state.session) {
          const subscriber = store.state.session.subscribe(
            event.stream,
            undefined as unknown as HTMLElement
          );

          // (##구현) emptyArr에 sub의 class 제거
          console.log("subscript create @@ ", subscriber);
          console.log(
            JSON.parse(subscriber.stream.connection.data.split("%/%")[0])
          );
          console.log(
            JSON.parse(subscriber.stream.connection.data.split("%/%")[1])
          );
          // 2. map user class name 추가
          mapUserClassName.value.set(
            JSON.parse(subscriber.stream.connection.data.split("%/%")[1])[
              "userId"
            ],
            JSON.parse(subscriber.stream.connection.data.split("%/%")[0])[
              "userSideOrder"
            ]
          );
          mapClassNameUser.value.set(
            JSON.parse(subscriber.stream.connection.data.split("%/%")[0])[
              "userSideOrder"
            ],
            JSON.parse(subscriber.stream.connection.data.split("%/%")[1])[
              "userId"
            ]
          );
          (subscribers.value as openVidu.Subscriber[]).push(subscriber);

          // 3. empty arr 삭제

          const index = (emptyVideoArr.value as string[]).indexOf(
            JSON.parse(subscriber.stream.connection.data.split("%/%")[0])[
              "userSideOrder"
            ]
          );
          if (index >= 0) (emptyVideoArr.value as string[]).splice(index, 1);
          console.log("MAP : ", mapUserClassName);
          console.log("SUBSCRIBERS : ", subscribers);
          console.log("EMPTY VIDEO ARR : ", emptyVideoArr);
        }
      }
    });

    // sessionScreen.on("streamCreated", (event) => {
    //   if (event.stream.typeOfVideo == "SCREEN") {
    //     // Subscribe to the Stream to receive it. HTML video will be appended to element with 'container-screens' id
    //     var subscriberScreen = sessionScreen?.subscribe(
    //       event.stream,
    //       "shareImg"
    //     );
    //     // When the HTML video has been appended to DOM...
    //     subscriberScreen?.on("videoElementCreated", (event) => {
    //       // Add a new <p> element for the user's nickname just below its video
    //       // appendUserData(event.element, subscriberScreen?.stream.connection);
    //     });
    //   }
    // });

    store.state.session.on("streamDestroyed", (event: any) => {
      // Remove the stream from 'subscribers' array

      // 1. 서버에 삭제 요청 *****
      // member2.get(`${store.state.roomId}/disconnect`);

      // 2. map user-class name 삭제
      const index = (subscribers.value as openVidu.Subscriber[]).indexOf(
        event.stream.streamManager as openVidu.Subscriber,
        0
      );

      // 3. empty arr 에 추가

      const currentUserSideOrder = mapUserClassName.value.get(
        JSON.parse(
          (subscribers.value as openVidu.Subscriber[])[
            index
          ].stream.connection.data.split("%/%")[1]
        )["userId"]
      );

      (emptyVideoArr.value as string[]).push(currentUserSideOrder);

      mapUserClassName.value.delete(
        JSON.parse(
          (subscribers.value as openVidu.Subscriber[])[
            index
          ].stream.connection.data.split("%/%")[1]
        )["userId"]
      );
      mapClassNameUser.value.delete(currentUserSideOrder);

      if (index >= 0) {
        subscribers.value.splice(index, 1);
      }
    });

    store.state.session.on("exception", (exception: any) => {
      console.warn(exception);
    });

    // session.on("signal:UPDATE_SIDE_ORDER", (event) => {
    //   // user(login_id)가 진영이 어디로 바뀌었는지 시그널 받음
    //   // 1. map user classname 변경
    //   // 2. empty로 갔으면 empty 삭제 or 추가
    //   // 기존 side, order 을 받아야 될듯
    //   // ***** 변경필요
    //   console.log(event);

    //   if (typeof event.data === "string") {
    //     const data = JSON.parse(event.data);
    //     // data["preSideOrder"] 와 data["newSideOrder"]의 교환
    //     const preSideOrder = data["preSideOrder"];
    //     const newSideOrder = data["newSideOrder"];

    //     console.log("UPDATE_SIDE_ORDER", preSideOrder, newSideOrder);

    //     console.log(mapClassNameUser);
    //     mapClassNameUser.value.set(
    //       preSideOrder,
    //       mapUserClassName.value.get(newSideOrder)
    //     );
    //     console.log(mapClassNameUser);
    //     mapClassNameUser.value.set(
    //       newSideOrder,
    //       mapUserClassName.value.get(preSideOrder)
    //     );
    //     console.log(mapClassNameUser);

    //     console.log(mapUserClassName);
    //     mapUserClassName.value.set(
    //       mapClassNameUser.value.get(preSideOrder),
    //       preSideOrder
    //     );
    //     console.log(mapUserClassName);
    //     mapUserClassName.value.set(
    //       mapClassNameUser.value.get(newSideOrder),
    //       newSideOrder
    //     );
    //     console.log(mapUserClassName);
    //   }
    // });

    // // 2번째 매개변수에 userInfo넣기
    console.log(testReturnData);
    if (testReturnData === undefined) return;
    console.log("no return");
    const tokenCamera = testReturnData["tokenCamera"];
    // const tokenScreen = testReturnData["tokenScreen"];

    await store.state.session
      .connect(tokenCamera, testReturnData)
      .then(() => {
        // --- Get your own camera stream with the desired properties ---
        if (OVCamera) {
          store.state.publisher = OVCamera.initPublisher(
            undefined as unknown as HTMLElement,
            {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "300x150", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            }
          );
          console.log("pub create : ", store.state.publisher);

          if (store.state.session) {
            store.state.session.publish(
              store.state.publisher as openVidu.Publisher
            );
          }

          mainStreamManager = store.state.publisher;
          // --- Publish your stream ---
        }
      })
      .catch((error: any) => {
        console.log(
          "There was an error connecting to the session:",
          error.code,
          error.message
        );
      });

    function leaveSession() {
      // 퇴실 서버에 request
      console.log(store.state.roomId, "disconnect");
      alert("LEAVE SESSION");
      member2.get(`/${store.state.roomId}/disconnect`);

      if (store.state.session) store.state.session.disconnect();
      store.state.session = undefined;
      mainStreamManager = undefined;
      store.state.publisher = undefined;
      subscribers.value = [];
      OVCamera = undefined;
      mapUserClassName.value = new Map();
      mapClassNameUser.value = new Map();

      window.removeEventListener("beforeunload", leaveSession);
      // window.removeEventListener("beforeunload", removeUser);
    }

    // function removeUser() {
    //   // 서버에서 session : token 제거
    // }

    window.addEventListener("beforeunload", leaveSession);
    // window.addEventListener("beforeunload", removeUser);
    console.log("...?");
    console.log("pub : ", store.state.publisher);
    console.log("sub : ", subscribers);

    console.log("empty Video Arr : ", emptyVideoArr);
    console.log("subscribers Value : ", toRaw(subscribers.value));
    // for (let i = 0; i < toRaw(subscribers.value).length; i++) {
    //   const sideOrder = JSON.parse(
    //     (
    //       toRaw(subscribers.value)[i] as openVidu.Subscriber
    //     ).stream.connection.data.split("%/%")[0] as any
    //   )["userSideOrder"];
    //   const index = emptyVideoArr.value.indexOf((sideOrder as string));
    // }
    // console.log("pre OV : ", OVScreen);
    // console.log("pre SESSION : ", sessionScreen);
    // console.log("pre token : ", tokenScreen);

    const sideA: string = testReturnData["sideA"];
    const sideB: string = testReturnData["sideB"];
    const debateTopic: string = testReturnData["debateTopic"];
    const nickname: string = testReturnData["nickname"];
    return {
      nickname,
      sideA,
      sideB,
      debateTopic,
      store,
      testReturnData,
      subscribers,
      mapUserClassName,
      mapClassNameUser,
      emptyVideoArr,
      // sessionCamera,
      // sessionScreen,
      OVCamera,
      // OVScreen,
      tokenCamera,
      // tokenScreen,
    };
  },
});
</script>

<style scoped>
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
  width: 100%;
  height: 100%;
  background: radial-gradient(circle, #141834 0%, #13162f 100%);
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
  height: 100px;
  background: #0e0e23;
}
</style>
