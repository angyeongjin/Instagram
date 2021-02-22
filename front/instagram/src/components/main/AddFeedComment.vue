<template>
  <div class="feed-input-comments">
    <input
      class="no__input"
      type="text"
      placeholder="댓글 달기..."
      v-model="this.subcomment.content"
    />
    <button class="no__btn" @click="subctest()">게시</button>
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  data: () => {
    return {
      comment: {
        id: 37,
        content: ""
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
      console.log(this.comment);
      this.commenttest(this.comment);
    },
    subctest() {
      this.subCommentTest(this.subcomment);
    }
  }
};
</script>
