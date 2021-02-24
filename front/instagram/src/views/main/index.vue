<template>
  <div id="wrap">
    <insta-header v-if="this.$store.getters.token" />

    <div id="container" role="main">
      <section id="main-left">
        <h2 class="none">feed list</h2>
        <feed-item
          v-for="(feed, index) in mainFeeds"
          :key="feed.id"
          :feed="feed"
          :idx="index"
        />
      </section>
      <aside id="main-right">
        <profile-box />
      </aside>
    </div>
    <footer role="contentinfo"></footer>
  </div>
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
    intervalID: 0
  }),
  computed: {
    ...mapGetters(["token"]),
    ...mapState("feed", ["mainFeeds", "mainPageNum"]),
    CALL_POINT() {
      return Math.ceil(this.SCROLL_MAX_H * 0.6);
    }
  },
  created() {
    if (this.mainPageNum === 1) this.getMainFeeds();
  },
  mounted() {
    this.intervalID = setInterval(() => {
      this.handleScroll();
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
        const status = await this.getMainFeeds();
        if (status === 204) clearInterval(this.intervalID);
      }
    }
  }
};
</script>
