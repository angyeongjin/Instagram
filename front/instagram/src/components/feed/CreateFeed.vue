<template>
  <div id="create-feed--wrap">
    <h2>Feed Registe</h2>
    <carousel />
    <image-filter
      v-show="page === 1"
      style="margin-bottom: 30px;"
    ></image-filter>
    <div v-show="page === 2">
      <textarea
        id="feed-text-box"
        cols="55"
        rows="5"
        placeholder="내용을 입력해주세요."
      ></textarea>
    </div>
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
export default {
  data: () => ({
    // imageFiles: [],
    // contents: "",
    page: 1
  }),
  components: { Carousel, ImageFilter },
  methods: {
    goNext() {
      this.page = 2;
    },
    goBack() {
      this.page = 1;
    },
    completed() {
      console.log(
        "[🐶 DDD] ~ file: CreateFeed.vue ~ line 105 ~ completed ~ completed"
      );
    }
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
