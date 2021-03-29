<template>
  <div>
    <div class="feed__item" style="overflow: hidden;">
      <div style="float: left;">
        <carousel
          :selectedImgUrl="selectedImgUrl"
          :totalImgNumber="totalImgNumber"
          :selectedIdx="imgIdx"
          :filter="feed.filter"
          @moveImgLeft="moveImgLeft"
          @moveImgRight="moveImgRight"
          @onClickImagePosition="onClickImagePosition"
          :options="{ w: '480px', h: '614px' }"
        />
      </div>
      <div style="float: left; width: 320px; height: 614px;">
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
          <input
            :id="`feed-option-${idx}`"
            type="checkbox"
            style="display:none;"
          />
          <label class="feed-option" :for="`feed-option-${idx}`">
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
            <div class="modal__box" @click="deleteFeed"></div>
            <label :for="`feed-option-${idx}`"></label>
          </div>
        </div>

        <div class="feed-main">
          <div class="feed-contents">
            <a href="#" class="nick-name" @click.prevent>{{
              feed.writer.memberId
            }}</a>
            <p style="display: inline;">
              {{ feed.contents }}
              <br />
              <br />
              <a href="#" class="tag">#lorem Ipsum</a>
            </p>
          </div>
          <div class="feed-comments">
            <div
              class="feed-comment"
              v-for="(comment, idx) in feed.commentList"
              :key="idx"
            >
              <a href="#" class="nick-name">{{ comment.writer.name }}</a>
              <p style="display: inline; padding: 0 0 0 10px;">
                {{ comment.content }}
              </p>
            </div>
            <span style="font-size: 10px;letter-spacing: .2px;color: #8e8e8e;"
              >2일전</span
            >
          </div>
        </div>

        <div style="height: 80px;">
          <feed-util :idx="idx" field="profile" />
        </div>

        <div class="feed-input-comments">
          <input
            class="no__input"
            type="text"
            placeholder="댓글 달기..."
            style="width: 89%;"
            v-model="comment.content"
          />
          <button class="no__btn" @click="addComment()" style="color: #0095f6;">
            게시
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import FeedUtil from "@/components/FeedUtil.vue";
import Carousel from "@/components/Carousel.vue";
export default {
  data() {
    return {
      comment: {
        content: "",
        feedId: ""
      },
      imgFiles: this.feed.images,
      imgIdx: 0
    };
  },
  props: {
    feed: {
      require: true,
      type: Object
    },
    idx: {
      require: true,
      type: Number
    }
  },
  components: { FeedUtil, Carousel },
  computed: {
    totalImgNumber() {
      return this.imgFiles?.length || 0;
    },
    selectedImgUrl() {
      return this.imgFiles[this.imgIdx];
    }
  },
  methods: {
    ...mapActions("feed", [
      "deleteProfileFeed",
      "insertComment",
      "deleteComment"
    ]),
    async deleteFeed() {
      if (confirm("삭제하시겠습니까?")) {
        const data = {
          feedId: this.feed.id,
          idx: this.idx
        };
        await this.deleteProfileFeed(data);
        this.$store.commit("closePopup");
      }
    },
    addComment() {
      this.comment.feedId = this.feed.id;
      const data = {
        field: "profile",
        comment: this.comment,
        idx: this.idx
      };
      this.insertComment(data);
      this.comment.content = "";
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

<style></style>
