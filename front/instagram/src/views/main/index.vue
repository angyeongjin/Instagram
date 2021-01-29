<template>
  <div id="container" role="main">
    <section id="main-left">
      <h2 class="none">feed list</h2>
      <feed-item v-for="feed in feeds" :key="feed.id" v-bind="feed" />
    </section>
    <aside id="main-right">
      <profile-box />
    </aside>
  </div>
  <footer role="contentinfo"></footer>
</template>

<script>
import FeedItem from "@/components/main/FeedItem.vue";
import ProfileBox from "@/components/ProfileBox.vue";

export default {
  name: "Main",
  components: { FeedItem, ProfileBox },
  setup() {
    let feeds = [
      { id: 1, feedContent: "dfsdf" },
      { id: 2, feedContent: "wwwwwww" }
    ];
    // const getFeeds = async () => {
    //   feeds = await getFeeds(props.user);
    // };
    return {
      feeds
    };
  }
};
</script>
