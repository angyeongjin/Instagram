<template>
  <article class="feed__item">
    <div class="feed-header">
      <span class="instagram_profile32 feed-header_profile"
        ><img
          src="http://placehold.it/32x32"
          height="32"
          width="32"
          alt="profile image"
          draggable="false"
      /></span>
      <a href="#" class="nick-name"><b>lee.ddd05</b></a>
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
      <img
        src="http://placehold.it/614x614"
        alt="피드 이미지"
        height="614"
        width="614"
      />
      <feed-util />
    </div>
    <div class="feed-contents">
      <a href="#" class="nick-name">Lorem Ipsum</a>
      <p style="display: inline;">
        is simply dummy text of the printing and typesetting industry. Lorem
        Ipsum has been the industry's standard dummy text ever since the 1500s,
        when an unknown printer took a galley of type and scrambled it to make a
        type specimen book. It has survived not only five centuries, but also
        the leap into electronic typesetting, remaining essentially unchanged.
        It was popularised in the 1960s with the release of Letraset sheets
        containing Lorem Ipsum passages, and more recently with desktop
        publishing software like Aldus PageMaker including versions of Lorem
        Ipsum. Why do we use it? It is a long established fact that a reader
        will be distracted by the readable content of a page when looking at its
        layout. The point of using Lorem Ipsum is that it has a more-or-less
        normal distribution of letters, as opposed to using 'Content here,
        content here', making it look like readable English. Many desktop
        publishing packages and web page editors now use Lorem Ipsum as their
        default model text, and a search for 'lorem ipsum' will uncover many web
        sites still in their infancy. Various versions have evolved over the
        years, sometimes by accident, sometimes on purpose (injected humour and
        the like).
        <br />
        <br />
        <a href="#" class="tag">#lorem Ipsum</a>
      </p>
    </div>
    <div class="feed-comments">
      <feed-comment />
    </div>
    <add-feed-comment />
  </article>
</template>

<script>
import FeedUtil from "@/components/FeedUtil.vue";
import FeedComment from "@/components/FeedComment.vue";
import AddFeedComment from "@/components/AddFeedComment.vue";
export default {
  props: ["id", "feedContent"],
  components: { FeedComment, FeedUtil, AddFeedComment }
};
</script>
