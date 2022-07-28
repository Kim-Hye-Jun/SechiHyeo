import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import TestAtom from "../components/atoms/TestAtom.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/test",
    name: "test",
    component: TestAtom,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
