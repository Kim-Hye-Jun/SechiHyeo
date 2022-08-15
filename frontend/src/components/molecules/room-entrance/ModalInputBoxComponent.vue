<template>
  <div class="box">
    <input
      class="box__input"
      type="text"
      v-model="inputValue"
      name=""
      required
    />
    <label class="box__label">{{ labelName }}</label>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  props: {
    labelName: String,
  },
  data() {
    return {
      inputValue: "" as string,
    };
  },
  methods: {},
});
</script>

<style scoped>
.box__input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: rgb(255, 255, 255);
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid rgb(255, 255, 255);
  outline: none;
  background: transparent;
}

.box__label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: rgb(255, 255, 255);
  pointer-events: none;
  transition: 0.5s;
}

.box {
  position: relative;
  width: 30em;
  height: 5em;
}

.box__input:valid ~ .box__label,
.box__input:focus ~ .box__label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}
</style>
