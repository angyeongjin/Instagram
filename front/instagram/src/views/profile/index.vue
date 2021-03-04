<template>
  <div id="wrap">
    <insta-header v-if="this.$store.getters.token" />
    <div id="container" role="main">
      <user-profile :id="this.id" :feedNum="this.feeds.length" />
      <hr style="opacity: .3;" />
      <ul id="user-profile-feed__list">
        <user-feed-item
          v-for="(feed, index) in this.feeds"
          :key="feed.id"
          :feed="feed"
          :idx="index"
        />
      </ul>
    </div>
  </div>
</template>
<script>
import InstaHeader from "@/components/Header.vue";
import UserProfile from "@/components/profile/UserProfile.vue";
import UserFeedItem from "@/components/profile/UserFeedItem.vue";
import { mapState, mapActions } from "vuex";
export default {
  props: ["id"],
  components: { InstaHeader, UserProfile, UserFeedItem },
  computed: {
    ...mapState("feed", { feeds: state => state.profile.feeds })
  },
  created() {
    this.getProfileFeeds(this.id);
  },
  methods: {
    ...mapActions("feed", ["getProfileFeeds"])
  }
};
</script>
