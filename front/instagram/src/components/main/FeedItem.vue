<template>
  <div class="feed__item feed__list__item">
    <div class="feed-header">
      <span class="instagram_profile32 feed-header_profile"
        ><img
          :src="feed.writer.picture"
          height="32"
          width="32"
          alt="profile image"
          draggable="false"
      /></span>
      <a href="#" class="nick-name"
        ><b>{{ feed.writer.memberId }}</b></a
      >
      <input id="feed-option-01" type="checkbox" style="display:none;" />
      <label class="feed-option" for="feed-option-01">
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
      <div>
        <div class="modal__box"></div>
        <label for="feed-option-01"></label>
      </div>
    </div>
    <div class="feed-img">
      <img :src="feed.images[0]" alt="피드 이미지" height="614" width="614" />
      <feed-util :idx="idx" />
    </div>
    <feed-contents :content="feed.contents" />
    <div class="feed-comments">
      <feed-comment />
      <span style="font-size: 10px;letter-spacing: .2px;color: #8e8e8e;"
        >2일전</span
      >
    </div>
    <add-feed-comment />
  </div>
</template>

<script>
import FeedUtil from "@/components/main/FeedUtil.vue";
import FeedComment from "@/components/main/FeedComment.vue";
import AddFeedComment from "@/components/main/AddFeedComment.vue";
import FeedContents from "@/components/main/FeedContents";

export default {
  props: ["feed", "idx"],
  components: { FeedComment, FeedUtil, AddFeedComment, FeedContents }
};
</script>
