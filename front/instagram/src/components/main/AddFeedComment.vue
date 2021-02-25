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
  props: ["feedid", "idx"],
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
    ...mapActions("feed", ["insertComment", "insertSubComment"]),
    ctest() {
      this.comment.feedId = this.feedid;
      const data = {
        comment: this.comment,
        idx: this.idx
      };
      this.insertComment(data);
      this.comment.content = "";
    },
    subctest() {
      this.insertSubComment(this.subcomment);
    }
  }
};
</script>
