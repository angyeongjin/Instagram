<template>
  <div>
    <div class="feed__item" style="overflow: hidden;">
      <div style="float: left;">
        <img :src="feed.images[0]" alt="피드 이미지" height="614" width="480" />
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
            <a href="#" class="nick-name">Lorem Ipsum</a>
            <p style="display: inline;">
              {{ feed.contents }}
              <br />
              <br />
              <a href="#" class="tag">#lorem Ipsum</a>
            </p>
          </div>
          <div class="feed-comments">
            <div class="feed-comment">
              <a href="#" class="nick-name">lee.ddd05</a>
              <p style="display: inline;">
                피드가 제 마음에 쏙 드네용~~^^
              </p>
            </div>
            <div class="feed-comment">
              <a href="#" class="nick-name">lee.ddd05</a>
              <p style="display: inline;">
                안녕하세요 반갑습니닷 사진보고 저도 모르게 하트를 누르고
                말았네요~~♥
              </p>
            </div>
            <span style="font-size: 10px;letter-spacing: .2px;color: #8e8e8e;"
              >2일전</span
            >
          </div>
        </div>

        <div style="height: 80px;">
          <div class="feed-util">
            <button class="no__btn">
              <svg
                aria-label="좋아요"
                class="_8-yf5 "
                fill="#262626"
                height="24"
                viewBox="0 0 48 48"
                width="24"
              >
                <path
                  d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z"
                ></path>
              </svg>
            </button>
            <p style="margin-top: 8px;" class="font-blod">
              좋아요 <span>{{ LIKE_NUM }}</span
              >개
            </p>
            <!-- actived -->
            <!-- <button class="no__btn">
														<svg aria-label="좋아요 취소" class="_8-yf5 " fill="#ed4956" height="24" viewBox="0 0 48 48" width="24"><path d="M34.6 3.1c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5s1.1-.2 1.6-.5c1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z"></path></svg>
													</button> -->
          </div>
        </div>

        <div class="feed-input-comments">
          <input
            class="no__input"
            type="text"
            placeholder="댓글 달기..."
            style="width: 89%;"
          />
          <button class="no__btn btn-off" style="color: #0095f6;" disabled>
            게시
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
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
  computed: {
    LIKE_NUM() {
      return this.feed.likeList.length;
    }
  },
  methods: {
    ...mapActions("feed", ["deleteProfileFeed"]),
    async deleteFeed() {
      if (confirm("삭제하시겠습니까?")) {
        const data = {
          feedId: this.feed.id,
          idx: this.idx
        };
        await this.deleteProfileFeed(data);
        this.$store.commit("closePopup");
      }
    }
  }
};
</script>

<style></style>
