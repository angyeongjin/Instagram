import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "@/views/login";
import Main from "@/views/main";
import Test from "@/views/test";
import Profile from "@/views/profile";
import store from "@/store";

// 인증 없이 다른 페이지 못넘어가게
const requireAuth = () => (to, from, next) => {
  if (store.getters.token) {
    return next();
  }
  next("/");
};

const routes = [
  {
    path: "/",
    name: "login",
    component: Login
  },
  {
    path: "/main",
    name: "Main",
    component: Main,
    beforeEnter: requireAuth()
  },
  {
    path: "/:id",
    name: "Profile",
    component: Profile,
    props: true
  },
  {
    path: "/test",
    name: "Test",
    component: Test
  },
  {
    path: "/home",
    name: "Home",
    component: Home
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savePosition) {
    return savePosition || { left: 0, top: 0 };
  }
});

export default router;
