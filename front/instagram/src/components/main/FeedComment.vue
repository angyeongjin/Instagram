<template>
  <div class="feed-comment">
    <a href="#" class="nick-name">lee.ddd05</a>
    <p style="display: inline;">
      피드가 제 마음에 쏙 드네용~~^^
    </p>
  </div>
  <div class="feed-comment">
    <a href="#" class="nick-name">lee.ddd05</a>
    <p style="display: inline;">
      안녕하세요 반갑습니닷 사진보고 저도 모르게 하트를 누르고 말았네요~~♥
    </p>
  </div>
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
