<template>
  <li class="user-profile-feed__item">
    <div class="user-profil-feed-img">
      <input
        :id="`feed-detail__check-${idx}`"
        type="checkbox"
        style="display:none;"
        @click="onClickUserFeedPopup"
      />
      <label :for="`feed-detail__check-${idx}`" style="display: inline-block;">
        <img
          :src="feed.images[0]"
          alt="empty"
          height="293"
          width="293"
          :class="feed.filter"
        />
      </label>
    </div>
  </li>
</template>
<script>
import UserFeedPopup from "@/components/popup/UserFeedPopup";
export default {
  props: ["feed", "idx"],
  methods: {
    onClickUserFeedPopup() {
      this.$store.commit("showPopup", {
        component: UserFeedPopup,
        prop: {
          feed: this.feed,
          idx: this.idx
        },
        options: { windowSize: { w: "808px", h: "614px" } }
      });
    }
  }
};
</script>
