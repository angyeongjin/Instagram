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
    maxHeight: 0
  }),
  computed: {
    ...mapGetters(["token"]),
    ...mapState("feed", ["mainFeeds"]),
    scrollHeight() {
      return Math.ceil(this.maxHeight * 0.6);
    }
  },
  created() {
    this.getMainFeeds();
    // setInterval(() => {
    //   this.handleScroll();
    // }, 1000);
  },
  mounted() {
    // console.log(this.$store.getters.token);
    // console.log(this.token);
  },
  methods: {
    ...mapActions("feed", ["getMainFeeds", "getNextMainFeeds"]),
    handleScroll() {
      const scroll = document.scrollingElement.scrollTop;
      this.maxHeight = document.scrollingElement.scrollHeight;
      console.log(this.maxHeight, this.scrollHeight, scroll);
      if (scroll > this.scrollHeight) {
        this.getNextMainFeeds();
      }
    }
  }
};
</script>
