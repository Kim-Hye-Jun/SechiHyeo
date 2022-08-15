<template>
  <div class="app">
    <app-header></app-header>
    <div class="app-contents">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import AppHeader from "@/components/common/AppHeader.vue";

export default defineComponent({
  components: {
    AppHeader,
  },
});
</script>

<style></style>
