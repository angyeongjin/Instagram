<template>
  <header>
    <div id="header-wrap">
      <div id="header-search-wrap">
        <a href="#" style="display:inline-block;"
          ><h1>
            <img
              src="https://www.instagram.com/static/images/web/mobile_nav_type_logo.png/735145cfe0a4.png"
              alt="Instagram"
              @click="goMain"
            /><span class="none">Instagram</span>
          </h1></a
        >
        <input type="text" id="search--input" placeholder="검색" />
      </div>
      <nav id="gnb">
        <a href="#" class="gnb__menu"
          ><svg
            @click="logout"
            aria-label="홈"
            class="_8-yf5 "
            fill="#262626"
            height="22"
            viewBox="0 0 48 48"
            width="22"
          >
            <path
              d="M45.5 48H30.1c-.8 0-1.5-.7-1.5-1.5V34.2c0-2.6-2.1-4.6-4.6-4.6s-4.6 2.1-4.6 4.6v12.3c0 .8-.7 1.5-1.5 1.5H2.5c-.8 0-1.5-.7-1.5-1.5V23c0-.4.2-.8.4-1.1L22.9.4c.6-.6 1.6-.6 2.1 0l21.5 21.5c.3.3.4.7.4 1.1v23.5c.1.8-.6 1.5-1.4 1.5z"
            ></path></svg
          ><span class="none">go to instagram</span></a
        >
        <button type="button" class="gnb__menu">
          <svg
            aria-label="새 게시물"
            class="_8-yf5 "
            fill="#262626"
            height="24"
            viewBox="0 0 48 48"
            width="24"
          >
            <path
              d="M31.8 48H16.2c-6.6 0-9.6-1.6-12.1-4C1.6 41.4 0 38.4 0 31.8V16.2C0 9.6 1.6 6.6 4 4.1 6.6 1.6 9.6 0 16.2 0h15.6c6.6 0 9.6 1.6 12.1 4C46.4 6.6 48 9.6 48 16.2v15.6c0 6.6-1.6 9.6-4 12.1-2.6 2.5-5.6 4.1-12.2 4.1zM16.2 3C10 3 7.8 4.6 6.1 6.2 4.6 7.8 3 10 3 16.2v15.6c0 6.2 1.6 8.4 3.2 10.1 1.6 1.6 3.8 3.1 10 3.1h15.6c6.2 0 8.4-1.6 10.1-3.2 1.6-1.6 3.1-3.8 3.1-10V16.2c0-6.2-1.6-8.4-3.2-10.1C40.2 4.6 38 3 31.8 3H16.2z"
            ></path>
            <path
              d="M36.3 25.5H11.7c-.8 0-1.5-.7-1.5-1.5s.7-1.5 1.5-1.5h24.6c.8 0 1.5.7 1.5 1.5s-.7 1.5-1.5 1.5z"
            ></path>
            <path
              d="M24 37.8c-.8 0-1.5-.7-1.5-1.5V11.7c0-.8.7-1.5 1.5-1.5s1.5.7 1.5 1.5v24.6c0 .8-.7 1.5-1.5 1.5z"
            ></path>
          </svg>
        </button>
        <button type="button" class="no__btn gnb__menu" @click="goRouterAbout">
          <svg
            aria-label="활동 피드"
            class="_8-yf5 "
            fill="#262626"
            height="22"
            viewBox="0 0 48 48"
            width="22"
          >
            <path
              d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z"
            ></path></svg
          ><span class="none">alert popup</span>
        </button>
        <span class="instagram_profile22 gnb__menu" @click="goProfile"
          ><img
            :src="picture"
            height="22"
            width="22"
            alt="profile image"
            draggable="false"
        /></span>
      </nav>
    </div>
  </header>
</template>

<script>
import { gapi } from "gapi-script";
import { mapState } from "vuex";
import { getUsers } from "@/api/member.js";

export default {
  created() {
    gapi.load("auth2", () => gapi.auth2.init());
  },
  computed: {
    ...mapState("member", ["picture"])
  },
  mounted() {
    let timeoutID = null;
    const search = document.querySelector("#search--input");
    search.addEventListener("input", e => {
      if (timeoutID) {
        clearTimeout(timeoutID);
      }
      timeoutID = setTimeout(() => {
        if (e.target.value.length) {
          getUsers(e.target.value)
            .then(res => console.log(res.data))
            .catch(err => console.log(err));
        }
      }, 800);
    });
  },
  methods: {
    goProfile() {
      this.$router.push(`/mjprojectid`);
    },
    async logout() {
      var auth2 = await gapi.auth2.getAuthInstance();
      auth2.signOut().then(this.removeToken());
    },
    removeToken() {
      this.$store
        .dispatch("member/logout")
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    test() {
      this.$store
        .dispatch("member/headerTest")
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    goMain() {
      this.$router.push("/main");
    }
  }
};
</script>
