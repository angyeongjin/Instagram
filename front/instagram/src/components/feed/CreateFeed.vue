<template>
  <div id="create-feed--wrap">
    <h2>Feed Registe</h2>
    <carousel
      @moveImgLeft="moveImgLeft"
      @moveImgRight="moveImgRight"
      @onClickImagePosition="onClickImagePosition"
      @onClickRegiImg="onClickRegiImg"
      :filter="filter"
      :selected-img-url="selectedImgUrl"
      :selected-idx="selectedIdx"
      :total-img-number="imageFiles.length"
    />
    <image-filter
      v-show="page === 1"
      style="margin-bottom: 30px;"
      :selectedImg="selectedImgUrl"
    ></image-filter>
    <div v-show="page === 2">
      <textarea
        id="feed-text-box"
        cols="55"
        rows="5"
        placeholder="내용을 입력해주세요."
        v-model="contents"
      ></textarea>
    </div>
    <button
      v-if="page === 1"
      class="create-feed-next-btn"
      @click="defaultFilter"
      type="button"
    >
      복원
    </button>
    <button
      v-if="page === 1"
      class="create-feed-next-btn"
      @click="goNext"
      type="button"
    >
      다음
    </button>
    <button
      v-if="page === 2"
      @click="goBack"
      class="create-feed-next-btn"
      type="button"
    >
      뒤로가기
    </button>
    <button
      v-if="page === 2"
      @click="completed"
      class="create-feed-next-btn"
      type="button"
    >
      등록
    </button>
  </div>
</template>
<script>
import Carousel from "@/components/Carousel";
import ImageFilter from "@/components/ImageFilter";
import { mapActions, mapMutations, mapState } from "vuex";
export default {
  name: "CreateFeed",
  data: () => ({
    contents: "",
    page: 1
  }),
  components: { Carousel, ImageFilter },
  computed: {
    ...mapState("createFeed", [
      "imageFiles",
      "selectedImgUrl",
      "selectedIdx",
      "filter"
    ])
  },
  methods: {
    ...mapMutations("createFeed", [
      "UPDATE_IMAGE_FILES",
      "UPDATE_SELECTED_IMG_URL"
    ]),
    ...mapMutations("createFeed", ["CLEAR_IMAGE_FILES", "SET_FILTER"]),
    ...mapActions("feed", ["addProfileFeed"]),
    defaultFilter() {
      this.SET_FILTER();
    },
    goNext() {
      this.page = 2;
    },
    goBack() {
      this.page = 1;
    },
    moveImgLeft() {
      this.UPDATE_SELECTED_IMG_URL(this.selectedIdx - 1);
    },
    moveImgRight() {
      this.UPDATE_SELECTED_IMG_URL(this.selectedIdx + 1);
    },
    onClickImagePosition(position) {
      this.UPDATE_SELECTED_IMG_URL(position);
    },
    completed() {
      this.uploadImage();
    },
    onClickRegiImg(files) {
      this.UPDATE_IMAGE_FILES(files);
    },
    async uploadImage() {
      const data = {
        files: this.imageFiles,
        contents: this.contents,
        filter: this.filter
      };

      try {
        await this.addProfileFeed(data);
        this.CLEAR_IMAGE_FILES();
        this.$store.commit("closePopup");
      } catch (err) {
        console.log("uploadImage Error", err);
      }
    }
  },
  unmounted() {
    this.CLEAR_IMAGE_FILES();
  }
};
</script>

<style scoped>
#create-feed--wrap * {
  margin-bottom: 20px;
}
#create-feed--wrap {
  text-align: center;
  padding: 30px 30px 20px 30px;
}
.create-feed-next-btn {
  width: 119px;
  height: 51px;
  background: linear-gradient(
    90deg,
    #ddc5f4 0%,
    rgba(230, 199, 235, 0.53) 100%
  );
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  margin-right: 20px;
}
.create-feed-next-btn:last-child {
  margin-right: 0;
}
#feed-text-box {
  margin-top: 30px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  padding: 10px;
}
</style>
