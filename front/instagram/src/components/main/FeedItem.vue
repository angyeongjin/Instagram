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
      <a href="#" class="nick-name" @click.prevent
        ><b @click="goProfile">{{ feed.writer.memberId }}</b></a
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
      <carousel
        :selectedImgUrl="selectedImgUrl"
        :totalImgNumber="totalImgNumber"
        :selectedIdx="imgIdx"
        :filter="feed.filter"
        @moveImgLeft="moveImgLeft"
        @moveImgRight="moveImgRight"
        @onClickImagePosition="onClickImagePosition"
        :options="{ w: '614px', h: '614px' }"
      />
      <feed-util :idx="idx" field="main" />
    </div>
    <feed-contents :content="feed.contents" :writer="feed.writer" />
    <div class="feed-comments">
      <feed-comment
        :commentList="feed.commentList"
        :feedIdx="idx"
        :feed="feed"
      />
      <span style="font-size: 10px;letter-spacing: .2px;color: #8e8e8e;"
        >2일전</span
      >
    </div>
    <add-feed-comment :feedid="feed.id" :idx="idx" />
  </div>
</template>

<script>
import Carousel from "@/components/Carousel.vue";
import FeedUtil from "@/components/FeedUtil.vue";
import FeedComment from "@/components/main/FeedComment.vue";
import AddFeedComment from "@/components/main/AddFeedComment.vue";
import FeedContents from "@/components/main/FeedContents";
export default {
  data() {
    return {
      imgFiles: this.feed.images,
      imgIdx: 0
    };
  },
  props: ["feed", "idx"],
  components: { FeedComment, FeedUtil, AddFeedComment, FeedContents, Carousel },
  computed: {
    totalImgNumber() {
      return this.imgFiles?.length || 0;
    },
    selectedImgUrl() {
      return this.imgFiles[this.imgIdx];
    }
  },
  methods: {
    goProfile() {
      this.$router.push(this.feed.writer.memberId);
    },
    moveImgLeft() {
      this.imgIdx--;
    },
    moveImgRight() {
      this.imgIdx++;
    },
    onClickImagePosition(position) {
      this.imgIdx = position;
    }
  }
};
</script>
