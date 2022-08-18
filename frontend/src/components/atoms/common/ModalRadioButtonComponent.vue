<template>
  <li class="list">
    <input
      class="list__input"
      type="radio"
      :id="labelId"
      :value="labelValue"
      :name="selector"
      :disabled="disabled"
      v-model="picked"
      @change="radioChange"
    />
    <label class="list__label" :for="labelId">{{ labelId }}</label>
    <div class="check"></div>
  </li>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  props: {
    labelId: String,
    labelValue: String,
    selector: String,
    disabled: Boolean,
  },
  data() {
    return {
      picked: "",
    };
  },
  methods: {
    radioChange(event: Event): void {
      this.$emit("method", (event.target as HTMLInputElement).value);
    },
  },
});
</script>

<style scoped>
.list {
  color: #aaaaaa;
  display: block;
  position: relative;
  float: left;
  width: 100%;
  height: 100px;
  border-bottom: 1px solid #333;
}

.list__input {
  position: absolute;
  visibility: hidden;
}

.list__label {
  display: block;
  position: relative;
  font-weight: 300;
  font-size: 1.35em;
  padding: 25px 25px 25px 80px;
  margin: 10px auto;
  height: 30px;
  z-index: 9;
  cursor: pointer;
  -webkit-transition: all 0.25s linear;
}

.list:hover .list__label {
  color: #ffffff;
}

.check {
  display: block;
  position: absolute;
  border: 5px solid #aaaaaa;
  border-radius: 100%;
  height: 25px;
  width: 25px;
  top: 30px;
  left: 20px;
  z-index: 5;
  transition: border 0.25s linear;
  -webkit-transition: border 0.25s linear;
}

.list:hover .check {
  border: 5px solid #ffffff;
}

.check::before {
  display: block;
  position: absolute;
  content: "";
  border-radius: 100%;
  height: 17px;
  width: 16px;
  /* top: 1px;
  left: 1px; */
  margin: auto;
  transition: background 0.25s linear;
  -webkit-transition: background 0.25s linear;
}
.list__input[type="radio"]:disabled ~ .check {
  border: 4px solid red;
}

.list__input[type="radio"]:disabled ~ .check::before {
  background: red;
}

.list__input[type="radio"]:disabled ~ .list__label {
  color: red;
}

.list__input[type="radio"]:checked ~ .check {
  border: 5px solid #0dff92;
}

.list__input[type="radio"]:checked ~ .check::before {
  background: #0dff92;
}

.list__input[type="radio"]:checked ~ .list__label {
  color: #0dff92;
}
</style>
