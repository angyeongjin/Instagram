<template>
  <div id="wrap">
    <div id="container" role="main">
      <section id="main-left">
        <ul class="list feed__list">
          <feed-item v-for="feed in feeds" :key="feed.id" v-bind="feed" />
        </ul>
      </section>
      <aside id="main-right">
        <profile-box />
      </aside>
    </div>
    <footer role="contentinfo"></footer>
  </div>
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
