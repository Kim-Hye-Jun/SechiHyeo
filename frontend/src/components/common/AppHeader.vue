<template>
  <header>
    <div>
      <router-link :to="headerLink" class="logo">
        세치혀
        <span v-if="isLogin">by {{ isMemberInfo.nickname }}</span>
      </router-link>
    </div>
    <div class="navigations">
      <!-- 1 -->
      <template v-if="isLogin">
        <router-link to="/entrance">Room &nbsp;&nbsp;</router-link>|
        <router-link to="/debate-board">Board &nbsp;&nbsp;</router-link>|
        <router-link to="/userPage">Mypage &nbsp;&nbsp;</router-link>|
        <a href="javascript:;" @click="logoutUser" class="logout-button">
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
import { deleteCookie } from "@/utils/cookies";
import { mapGetters, mapMutations } from "vuex";
export default defineComponent({
  computed: {
    ...mapGetters(["isLogin", "isMemberInfo"]),
    headerLink(): string {
      return this.isLogin ? "/main" : "/main";
    },
  },
  methods: {
    ...mapMutations(["CLEAR_USER_ID", "CLEAR_TOKEN", "CLEAR_MEMBER_INFO"]),
    logoutUser() {
      this.CLEAR_USER_ID();
      this.CLEAR_TOKEN();
      this.CLEAR_MEMBER_INFO();
      deleteCookie("sch_auth");
      deleteCookie("sch_user");
      this.$router.push("/login");
    },
  },
});
</script>
<style scoped>
* {
  font-family: "locus_sangsang";
  text-decoration: none;
  color: white;
}
/* .username {
  color: white;
} */

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: rgba(0, 38, 255, 0.121);
  z-index: 3;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  position: fixed;
  top: 0;
  /* width: 100% */
  left: 0;
  right: 0;
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

span {
  color: white;
}
</style>
