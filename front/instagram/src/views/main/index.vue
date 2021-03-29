<template>
  <insta-header v-if="this.$store.getters.token" />
  <div id="container" role="main">
    <section id="main-left">
      <h2 class="none">feed list</h2>
      <feed-item
        v-for="(feed, index) in feeds"
        :key="feed.id"
        :feed="feed"
        :idx="index"
      />
    </section>
    <aside id="main-right">
      <profile-box :name="name" :member-id="memberId" :picture="picture" />
    </aside>
  </div>
  <footer role="contentinfo"></footer>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import FeedItem from "@/components/main/FeedItem.vue";
import ProfileBox from "@/components/main/ProfileBox.vue";
import InstaHeader from "@/components/Header.vue";
export default {
  name: "Main",
  components: { FeedItem, ProfileBox, InstaHeader },
  data: () => ({
    SCROLL_MAX_H: 0,
    intervalID: 0,
    isLoading: false
  }),
  computed: {
    ...mapState("member", ["name", "memberId", "picture"]),
    ...mapGetters(["token"]),
    ...mapState("feed", {
      feeds: state => state.main.feeds,
      pageNum: state => state.main.pageNum
    }),
    CALL_POINT() {
      return Math.ceil(this.SCROLL_MAX_H * 0.6);
    }
  },
  created() {
    this.getMainFeeds();
  },
  mounted() {
    this.intervalID = setInterval(() => {
      if (!this.isLoading) this.handleScroll();
    }, 1000);
  },
  unmounted() {
    clearInterval(this.intervalID);
  },
  methods: {
    ...mapActions("feed", ["getMainFeeds", "getNextMainFeeds"]),
    async handleScroll() {
      const scroll = document.scrollingElement.scrollTop;
      this.SCROLL_MAX_H = document.scrollingElement.scrollHeight;
      if (scroll > this.CALL_POINT) {
        this.isLoading = true;
        const status = await this.getNextMainFeeds();
        if (status === 204) clearInterval(this.intervalID);
        this.isLoading = false;
      }
    }
  }
};
</script>
