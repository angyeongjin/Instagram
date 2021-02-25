<template>
  <div class="feed-comment" v-for="(comment, idx) in commentList" :key="idx">
    <a href="#" class="nick-name">{{ comment.writer.name }}</a>
    <p style="display: inline;">
      {{ comment.content }}
    </p>
  </div>
</template>
<script>
export default {
  props: ["commentList"]
};
</script>
