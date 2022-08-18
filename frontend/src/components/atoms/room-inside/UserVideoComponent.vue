<template>
  <div>
    <video autoplay :class="returnVideoClassName(xx)"></video>
    <debate-memo-modal-component
      :nickname="returnNickname()"
    ></debate-memo-modal-component>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import DebateMemoModalComponent from "@components/molecules/room-inside/DebateMemoModalComponent.vue";
import { useStore } from "vuex";

export default defineComponent({
  components: {
    DebateMemoModalComponent,
  },
  setup() {
    const store = useStore();
    return { store };
  },
  props: {
    streamManager: Object,
    xx: String,
    // isRoomAdmin: Boolean,
  },
  mounted() {
    console.log("추가", this.store.state.publisher);
    console.log("EL : ", this.$el);
    console.log("selector : ", document.querySelector("video"));
    // this.streamManager?.addVideoElement(
    //   document.getElementById(this.xx as string)
    // );
    console.log(document.getElementsByClassName(this.$props.xx as string)[0]);

    if (this.xx === undefined) return;

    console.log(this.streamManager);

    this.streamManager?.addVideoElement(
      document.getElementsByClassName(
        this.returnVideoClassName(this.$props.xx as string)
      )[0]
    );
  },
  data() {
    return {};
  },
  methods: {
    returnNickname(): string {
      if (this.streamManager === undefined) return "";
      return JSON.parse(
        this.streamManager?.stream.connection.data.split("%/%")[1]
      )["nickname"];
    },
    returnVideoClassName(xx: string | undefined): string {
      return xx + "__video";
    },
  },
});
</script>

<style scoped>
video {
  width: 100%;
  background: black;
  border-radius: 10px;
  padding: 5px;
  box-sizing: border-box;
  background-clip: content-box;
}

/* .flex {
  display: flex;
  flex-direction: column;
  align-items: center;
} */
</style>
