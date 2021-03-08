<template>
  <div id="profile__box">
    <span class="instagram_profile56">
      <img :src="picture" alt="profile image" width="56" height="56" />
    </span>
    <div>
      <a href="#" class="nick-name" @clcik.prevent
        ><b @click="goProfile">{{ memberId }}</b></a
      >
      <p class="name">{{ name }}</p>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState("member", ["name", "memberId", "picture"])
  },
  methods: {
    goProfile() {
      this.$router.push(this.memberId);
    }
  }
};
</script>
