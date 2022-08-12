<template>
  <div class="paper">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      height="20"
      width="20"
      class="close-modal"
      fill="currentColor"
      @click="$emit('close-modal')"
    >
      <path
        d="m5.979 14.75-.687-.729 4-4.021-4-4.062.687-.73 4.042 4.063 4-4.063.687.73-4 4.062 4 4.021-.687.729-4-4.062Z"
      />
    </svg>
    <div class="paper-content">
      <textarea autofocus></textarea>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {};
  },
});
</script>
<style>
.paper {
  position: relative;
  width: 90%;
  max-width: 800px;
  min-width: 400px;
  height: 390px;
  margin: 0 auto;
  background: #fafafa;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}
.paper:before {
  content: "";
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  width: 60px;
  background: radial-gradient(#575450 6px, transparent 7px) repeat-y;
  background-size: 30px 30px;
  border-right: 3px solid #d44147;
  box-sizing: border-box;
}

.paper-content {
  position: absolute;
  top: 30px;
  right: 0;
  bottom: 30px;
  left: 60px;
  background: linear-gradient(transparent, transparent 28px, #91d1d3 28px);
  background-size: 30px 30px;
}

.paper-content textarea {
  width: 100%;
  max-width: 100%;
  height: 100%;
  max-height: 100%;
  line-height: 30px;
  padding: 0 10px;
  border: 0;
  outline: 0;
  background: transparent;
  color: rgb(2, 2, 24);
  font-family: "Handlee", cursive;
  /* font-weight: bold; */
  font-size: 18px;
  box-sizing: border-box;
  z-index: 1;
}

textarea::-webkit-scrollbar {
  width: 8px;
}

textarea.thin::-webkit-scrollbar {
  width: 2px;
}

::-webkit-scrollbar-track {
  border-radius: 10px;
  background: #eee;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background: #888;
}

.close-modal {
  float: right;
  cursor: pointer;
}
.close-modal:hover {
  color: red;
}
</style>
