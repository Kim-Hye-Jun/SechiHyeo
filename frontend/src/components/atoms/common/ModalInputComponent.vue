<template><input type="text" name="" required /></template>
<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  components: {},
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style>
input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
</style>
