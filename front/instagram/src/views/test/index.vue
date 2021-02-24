<template>
  <image-test /><br />
  <h1 @click="moveToProfile">메인으로</h1>
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
