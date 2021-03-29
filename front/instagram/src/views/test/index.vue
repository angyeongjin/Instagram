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
    ...mapState("feed", {
      mainFeeds: state => state.main.feeds,
      profileFeeds: state => state.profile.feeds
    })
  },
  methods: {
    moveToProfile() {
      this.$router.push("/mjprojectid");
    }
  }
};
</script>
