<template>
  <div class="feed-comment" v-for="(comment, idx) in commentList" :key="idx">
    <a href="#" class="nick-name">{{ comment.writer.memberId }}</a>
    <p style="display: inline; padding: 0 0 0 10px;">
      {{ comment.content }}
    </p>
  </div>
</template>
<script>
export default {
  props: ["commentList"]
};
</script>
