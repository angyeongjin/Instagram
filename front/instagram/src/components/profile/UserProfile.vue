<template>
  <div id="user-profile">
    <div id="user-profile-img">
      <img
        :src="profile.picture"
        alt="empty"
        class="instagram_profile"
        height="150"
        width="150"
      />
    </div>
    <div id="user-profile-info">
      <a href="" class="nick-name" @click.prevent
        ><h2 style="font-size: 28px;display:inline;margin-right: 20px;">
          {{ profile.memberId }}
        </h2></a
      >
      <button type="button" class="follow__btn">팔로우</button>
      <div id="user-profile-side-info">
        <span id="feed-num">게시글 <span class="font-blod">182</span></span>
        <span id="follower-num"
          >팔로워 <span class="font-blod">1,290</span></span
        >
        <span id="follow-num">팔로우 <span class="font-blod"> 39</span></span>
      </div>
      <div id="user-info">
        <h2 id="name" style="font-size: inherit;">{{ profile.name }}</h2>
        <span @click="moveToTest">
          😽 Scottish straight (♀)
          <br />
          🍰 2019.09.04
          <br />
          ❤ 귀여움
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { getUser } from "@/api/member";
export default {
  props: ["id"],
  data: () => ({
    profile: {}
  }),
  created() {
    getUser(this.id)
      .then(res => {
        this.profile = res.data;
      })
      .catch(err => console.log(err));
  },
  computed: {
    ...mapState("feed", { feeds: state => state.profile.feeds })
  },
  methods: {
    moveToTest() {
      this.$router.push("/test");
    }
  }
};
</script>
