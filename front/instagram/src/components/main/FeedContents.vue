<template>
  <div class="feed-contents">
    <a href="#" class="nick-name">{{ writer.memberId }}</a>
    <p style="display: inline;">
      {{ content }}
      <br />
      <br />
      <a href="#" class="tag">#lorem Ipsum</a>
    </p>
  </div>
</template>
<script>
export default {
  props: ["content", "writer"]
};
</script>
