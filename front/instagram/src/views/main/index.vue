<template>
  <div id="wrap">
    <header />
    <div id="container" role="main">
      <section id="main-left">
        <ul class="list feed__list">
          <feed-item />
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
import Header from "@/components/Header.vue";
import FeedItem from "@/components/FeedItem.vue";
import ProfileBox from "@/components/ProfileBox.vue";

export default {
  name: "Main",
  components: {
    Header,
    FeedItem,
    ProfileBox
  }
};
</script>
