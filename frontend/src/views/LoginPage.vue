<template>
  <div>
    <h1 class="page-header">로그인 페이지</h1>
    <login-form></login-form>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import LoginForm from "@/components/LoginForm.vue";

export default defineComponent({
  components: {
    LoginForm,
  },
});
</script>
<style scoped></style>
