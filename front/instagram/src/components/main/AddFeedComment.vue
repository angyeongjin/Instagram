<template>
  <div class="feed-input-comments">
    <input
      class="no__input"
      type="text"
      placeholder="댓글 달기..."
      v-model="this.comment.content"
    />
    <button class="no__btn" @click="ctest()">게시</button>
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  props: ["feedid"],
  data: () => {
    return {
      comment: {
        id: 37,
        content: "",
        feedId: ""
      },
      subcomment: {
        commentId: 37,
        id: "",
        content: ""
      }
    };
  },
  methods: {
    ...mapActions("feed", ["commenttest", "subCommentTest"]),

    ctest() {
      this.comment.feedId = this.feedid;
      console.log(this.comment);
      this.commenttest(this.comment);
      this.comment = "";
    },
    subctest() {
      this.subCommentTest(this.subcomment);
    }
  }
};
</script>
