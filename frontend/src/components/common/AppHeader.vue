<template>
  <header>
    <div>
      <router-link v-if="isUserLogin()" to="/main" class="logo">
        세치혀
        <span>by {{ this.store.state.memberinfo.nickname }}</span>
      </router-link>
      <router-link v-else to="/" class="logo">
        세치혀
        <span v-if="isUserLogin()"
          >by {{ this.store.state.memberinfo.nickname }}</span
        >
      </router-link>
    </div>
    <div class="navigations">
      <!-- 1 -->
      <template v-if="isUserLogin()">
        <a href="javascript:;" @click="logoutMember" class="logout-button">
          Logout
        </a>
      </template>
      <!-- 2 -->
      <template v-else>
        <router-link to="/login">로그인</router-link>
        <router-link to="/signup">회원가입</router-link>
      </template>
    </div>
  </header>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const store = useStore();

    return { store };
  },
  methods: {
    isUserLogin() {
      return this.store.state.isLogin;
    },
    logoutMember() {
      this.store.commit("MEMBER_LOGOUT");
      this.$router.push("/login");
    },
  },
});
</script>
<style scoped>
.username {
  color: white;
}
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #927dfc;
  z-index: 2;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
}
a {
  color: #dedede;
  font-size: 18px;
}
a.logo {
  font-size: 30px;
  font-weight: 900;
  color: white;
}
.logo > span {
  font-size: 14px;
  font-weight: normal;
}
.navigations a {
  margin-left: 10px;
}
.fixed {
  position: fixed;
  top: 0;
  width: 100%;
}
.logout-button {
  font-size: 18px;
}
a.router-link-exact-active {
  color: white;
  font-weight: bold;
}
</style>
