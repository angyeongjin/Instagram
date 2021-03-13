<template>
  <div>
    <div style="margin:15px">
      <button
        class="btn btn--back"
        style="background-color:#FF6160"
        @click="UserCommentDelete()"
      >
        삭제
      </button>
    </div>
    <div style="margin:15px">
      <button
        class="btn btn--back"
        style="background-color:#6D8793"
        @click="close()"
      >
        취소
      </button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: ["comment", "feedIdx", "commentIdx"],
  data: () => {
    return {};
  },
  methods: {
    ...mapActions("feed", ["deleteComment"]),

    UserCommentDelete() {
      this.deleteComment({
        field: "main",
        comment: this.comment,
        feedIdx: this.feedIdx,
        commentIdx: this.commentIdx
      });
      this.$store.commit("closePopup");
    },
    close() {
      this.$store.commit("closePopup");
    }
  }
};
</script>

<style></style>
