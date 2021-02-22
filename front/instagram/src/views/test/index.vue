<template>
  <image-test />
  <h1 @click="moveToProfile()">{{ mainFeeds }}</h1>
  <label for="chkBox" />
</template>
<script>
import ImageTest from "@/components/ImageTest.vue";
import { mapState } from "vuex";
export default {
  components: { ImageTest },
  computed: {
    ...mapState({
      mainFeeds: state => state.feed.mainFeeds,
      profileFeeds: state => state.feed.profileFeeds
    })
  },
  methods: {
    moveToProfile() {
      this.$router.push("/mjprojectid");
    }
  }
};
</script>
