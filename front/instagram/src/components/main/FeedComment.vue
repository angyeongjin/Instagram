<template>
  <div class="feed-comment" v-for="(comment, idx) in commentList" :key="idx">
    <a href="#" class="nick-name">{{ comment.writer.memberId }}</a>
    <p style="display: inline; padding: 0 0 0 10px;">
      {{ comment.content }}
    </p>
    <label
      v-if="feed.writer.memberId === memberId"
      class="feed-option"
      @click="onClickUserFeedCommentPopup(idx)"
    >
      <svg
        aria-label="옵션 더 보기"
        class="_8-yf5 "
        fill="#262626"
        height="16"
        viewBox="0 0 48 48"
        width="16"
      >
        <circle
          clip-rule="evenodd"
          cx="8"
          cy="24"
          fill-rule="evenodd"
          r="4.5"
        ></circle>
        <circle
          clip-rule="evenodd"
          cx="24"
          cy="24"
          fill-rule="evenodd"
          r="4.5"
        ></circle>
        <circle
          clip-rule="evenodd"
          cx="40"
          cy="24"
          fill-rule="evenodd"
          r="4.5"
        ></circle>
      </svg>
    </label>
  </div>
</template>
<script>
import UserFeedCommentPopupVue from "@/components/popup/UserFeedCommentPopup.vue";
import { mapState } from "vuex";
export default {
  props: ["commentList", "feedIdx", "feed"],
  computed: {
    ...mapState("member", ["memberId"])
  },
  methods: {
    onClickUserFeedCommentPopup(idx) {
      this.$store.commit("showPopup", {
        component: UserFeedCommentPopupVue,
        prop: {
          comment: this.commentList[idx],
          feedIdx: this.feedIdx,
          commentIdx: idx
        },
        options: { windowSize: { w: "300px", h: "150px" } }
      });
    }
  }
};
</script>
