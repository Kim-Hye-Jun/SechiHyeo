<template>
  <div>
    <modal-radio-button-component></modal-radio-button-component>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import ModalRadioButtonComponent from "@/components/atoms/common/ModalRadioButtonComponent.vue";
export default defineComponent({
  components: { ModalRadioButtonComponent },
  data() {
    return {
      example: "",
    };
  },
  methods: {},
});
</script>

<style></style>
