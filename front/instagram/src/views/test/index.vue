<template>
  <image-test />
  <h1 @click="test()">{{ mainFeeds }}</h1>
  <h2>{{ profileFeeds }}</h2>
</template>
<script>
import ImageTest from "@/components/ImageTest.vue";
import { mapState } from "vuex";
export default {
  components: { ImageTest },
  mounted() {
    // console.log(this.$store);
    console.log(this.$store.state.feed);
  },
  computed: {
    ...mapState({
      mainFeeds: state => state.feed.mainFeeds,
      profileFeeds: state => state.feed.profileFeeds
    })
  }
};
</script>
