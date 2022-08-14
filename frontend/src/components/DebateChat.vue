<template>
  <div class="chat-panel">
    <div class="chat-box p-2 d-flex flex-column h-100">
      <div class="header text-left">
        <span class="title"> 채팅 </span>
        <!-- <button class="btn close-btn" @click="toggleChatPanel">
          <i class="fas fa-times"></i>
        </button> -->
      </div>
      <!-- 채팅 내역 -->
      <div id="chat-area" :height="chatHeight">
        <div
          class="mt-2 text-left message"
          v-for="(messageOne, i) of messages"
          :key="i"
        >
          <div class="message-title">
            <span class="mr-2 message-header">{{ messageOne.sender }}</span>
            <span class="message-header">{{ messageOne.time }}</span>
          </div>
          <div>
            {{ messageOne.content }}
          </div>
        </div>
      </div>
      <div class="footer d-flex mt-auto">
        <div class="col-10 px-1 py-0">
          <input
            class="text-box"
            v-model="message"
            @keyup.enter="clickSendMessage"
          />
        </div>
        <div class="col-2 p-0">
          <button type="button" class="send-btn" @click="clickSendMessage">
            <i class="fas fa-paper-plane">메세지 전송</i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  name: "DebateChat",
  setup() {
    const store = useStore();

    return { store };
  },
  mounted() {
    this.store.state.session.on("signal:send-massage", (event: any) => {
      console.log(event.data);
      let message = JSON.parse(event.data);
      this.messages.push(message);
    });
  },
  data() {
    return {
      messages: [] as any[],
      // message: { sender: "", content: "", time: "" },
      message: "",
      chatHeight: "33vh",
    };
  },
  watch: {
    // messages() {
    //   setTimeout(() => {
    //     var chatDiv = document.getElementById("chat-area");
    //     if (chatDiv != null) {
    //       chatDiv.scrollTo({
    //         top: chatDiv.scrollHeight - chatDiv.clientHeight,
    //         behavior: "smooth",
    //       });
    //     }
    //   }, 50);
    // },
  },
  computed: {},
  methods: {
    clickSendMessage() {
      if (this.message.trim()) {
        let sendMessage = {
          sender: "",
          content: "",
          time: "",
        };
        sendMessage.content = this.message;
        sendMessage.sender = this.store.state.memberinfo.nickname;
        const now = new Date();
        let hours = String("0" + now.getHours()).slice(-2);
        let minutes = String("0" + now.getMinutes()).slice(-2);
        let seconds = String("0" + now.getSeconds()).slice(-2);
        let time = hours + ":" + minutes + ":" + seconds;
        sendMessage.time = time;
        console.log(sendMessage);

        this.store.state.session.signal({
          data: JSON.stringify(sendMessage),
          to: [],
          type: "send-massage",
        });
        this.message = "";
      }
    },
  },
});
</script>
<style scoped>
.chat-box {
  height: 100%;
}

.header {
  position: relative;
}

.close-btn {
  position: absolute;
  color: white;
  top: 3px;
  right: 10px;
}

.text-box {
  background-color: #d1d1d1;
  width: 100%;
  border-radius: 20px;
  color: black;
  padding-left: 10px;
}

.text-box:focus {
  outline: none;
}

.title {
  padding-left: 5%;
  font-family: "Jua" !important;
  font-size: 1rem !important;
  color: white;
}

.header {
  width: 100%;
  border-radius: 20px;
  box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.3);
  height: 4vh;
}
.message-title {
  font-size: 0.8rem;
}

.message-header {
  font-family: "Nanum Gothic", sans-serif;
}

.message {
  color: white;
}

.send-btn {
  color: white;
}

#chat-area {
  overflow-y: auto;
}

#chat-area::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

#chat-area::-webkit-scrollbar-track {
  background: #37474f;
}

#chat-area::-webkit-scrollbar-corner {
  background: #37474f;
}

#chat-area::-webkit-scrollbar-thumb {
  background: #b0a2c8;
}

#chat-area::-webkit-scrollbar-button {
  background-color: #37474f;
  height: 0;
}
</style>
