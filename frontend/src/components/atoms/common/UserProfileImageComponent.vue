<template>
  <div>
    <span class="account-user"
      >Quan Ha
      <img
        src="https://images.genius.com/2326b69829d58232a2521f09333da1b3.1000x1000x1.jpg"
        alt=""
        class="account-profile"
      />
      <span>▼</span>
    </span>
  </div>
</template>
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

<style scoped>
.account-user {
  display: inline-flex;
  align-items: center;
  color: #64677a;
  font-weight: 600;
}
.account-user span {
  font-size: 10px;
  font-weight: normal;
}

.account-profile {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  margin: 0 10px;
}
</style>
