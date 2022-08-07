import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import store from "@/store/index";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "memberLogIn",
    component: () => import("@/views/LoginPage.vue"),
  },
  {
    path: "/signup",
    name: "memberSignUp",
    component: () => import("@/views/SignupPage.vue"),
  },
  {
    path: "/main",
    component: () => import("@/views/MainPage.vue"),
    meta: { auth: true }, //라우터 네비게이터 가드 확인용 변수
  },
  {
    path: "/:catchAll(.*)", // vue3부터는 정규식으로 표현
    component: () => import("@/views/NotFoundPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
// 라우트 네비게이션 가드
router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters.isLogin) {
    // 로그인 안된 유저라면
    alert("인증이 필요합니다");
    next("/login");
    return;
  }
  next();
});

export default router;
