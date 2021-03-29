<template>
  <div id="user-profile">
    <div id="user-profile-img">
      <img
        :src="this.profile.picture"
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
      <button
        @click="followGo(this.profile.memberId)"
        v-if="this.profile.memberId != this.memberId && !this.followflag"
        type="button"
        class="follow__btn"
      >
        팔로우
      </button>
      <button
        @click="unfollowGo(this.profile.memberId)"
        v-if="this.profile.memberId != this.memberId && this.followflag"
        type="button"
        class="follow__btn"
      >
        언팔로우
      </button>

      <div id="user-profile-side-info">
        <span id="feed-num"
          >게시글 <span class="font-blod">{{ feeds.length }}</span></span
        >
        <span id="follower-num"
          >팔로워
          <span class="font-blod">{{ profile.followerList.length }}</span></span
        >
        <span id="follow-num"
          >팔로우
          <span class="font-blod">
            {{ profile.followingList.length }}</span
          ></span
        >
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
import { mapState, mapActions } from "vuex";
export default {
  computed: {
    ...mapState("feed", {
      feeds: state => state.profile.feeds,
      profile: state => state.profile.user
    }),
    ...mapState("member", ["memberId"]),
    followflag() {
      return this.profile.followerList.find(x => x.memberId === this.memberId);
    }
  },
  methods: {
    ...mapActions("feed", ["follow", "unfollow"]),
    moveToTest() {
      this.$router.push("/test");
    },
    followGo(id) {
      this.follow({ memberId: id, myId: this.memberId });
    },
    unfollowGo(id) {
      this.unfollow(id);
    }
  }
};
</script>
