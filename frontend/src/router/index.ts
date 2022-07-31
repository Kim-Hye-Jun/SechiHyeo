import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

// import RoomEntranceSearchInputComponentVue from "@components/atoms/root-entrance/RoomEntranceSearchInputComponent.vue";
// import RoomEntranceThumbnailComponentVue from "@components/atoms/room-entrance/RoomEntranceThumbnailComponent.vue";
// import RoomEntranceInfoComponentVue from "@components/atoms/room-entrance/RoomEntranceInfoComponent.vue";
// import RoomEntranceNameComponentVue from "@components/atoms/room-entrance/RoomEntranceNameComponent.vue";
// import RoomEntranceComponentVue from "@components/molecules/room-entrance/RoomEntranceComponent.vue";
// import RoomEntranceBoardComponentVue from "@components/organisms/room-entrance/RoomEntranceBoardComponent.vue";
// import RoomEntranceSearchInputComponentVue from "@components/atoms/room-entrance/RoomEntranceSearchInputComponent.vue";
// import RoomEntranceSearchImageComponentVue from "@components/atoms/room-entrance/RoomEntranceSearchImageComponent.vue";
// import RoomEntrancePageComponentVue from "@components/templates/room-entrance/RoomEntrancePageComponent.vue";
// import ContainerComponentVue from "@components/atoms/common/ContainerComponent.vue";
// import UserProfileImageComponentVue from "@components/atoms/common/UserProfileImageComponent.vue";
// import UserVideoComponentVue from "@components/atoms/room-inside/UserVideoComponent.vue";
import RoomVideoComponentVue from "@components/organisms/room-inside/RoomVideoComponent.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/dev",
    name: "dev",
    component: RoomVideoComponentVue,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
