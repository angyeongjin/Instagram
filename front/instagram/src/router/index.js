import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";

// 인증 없이 다른 페이지 못넘어가게
// const requireAuth = () => (to, from, next) => {
//   if (store.state.token) {
//     return next();
//   }
//   next("/");
// };

const routes = [
  {
    path: "/",
    name: "login",
    component: () => import("@/views/login")
  },
  {
    path: "/main",
    name: "Main",
    component: () => import("@/views/main")
  },
  {
    path: "/home",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
