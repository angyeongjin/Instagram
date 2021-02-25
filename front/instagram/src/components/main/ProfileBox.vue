<template>
  <div id="profile__box">
    <span class="instagram_profile56">
      <img :src="picture" alt="profile image" width="56" height="56" />
    </span>
    <div>
      <a href="#" class="nick-name"
        ><b>{{ memberId }}</b></a
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
  }
};
</script>
