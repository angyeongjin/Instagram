<template>
  <div id="carousel--wrap">
    <div id="carousel--box">
      <div
        @click="onClickLeft"
        :class="{ 'dir-left-shape': isShowLeftDir }"
      ></div>
      <div
        @click="onClickRight"
        :class="{ 'dir-right-shape': isShowRightDir }"
      ></div>
      <label
        id="carousel--bg"
        :style="`background-image: url(${selectedImgUrl});`"
        :class="filter"
      >
        <div v-if="!selectedImgUrl" class="plus-shape"></div>
        <input
          type="file"
          id="carousel__input"
          @change="onClickRegiImg"
          multiple
        />
      </label>
      <div class="carousel-image-idx--wrap">
        <ul class="carousel-image-idx--list">
          <li
            v-for="(item, idx) in imageFiles"
            :key="idx"
            class="carousel-image-idx--item"
            :class="{ 'carousel-image-idx-active--item': idx === selectedIdx }"
            @click="onClickImagePosition(idx)"
          ></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
export default {
  name: "Carousel",
  computed: {
    ...mapState("createFeed", [
      "imageFiles",
      "selectedImgUrl",
      "selectedIdx",
      "filter"
    ]),
    isShowLeftDir() {
      return this.selectedIdx !== 0;
    },
    isShowRightDir() {
      return (
        this.selectedImgUrl && this.selectedIdx !== this.imageFiles?.length - 1
      );
    }
  },
  methods: {
    ...mapMutations("createFeed", [
      "UPDATE_IMAGE_FILES",
      "UPDATE_SELECTED_IMG_URL"
    ]),
    onClickLeft() {
      this.UPDATE_SELECTED_IMG_URL(this.selectedIdx - 1);
    },
    onClickRight() {
      this.UPDATE_SELECTED_IMG_URL(this.selectedIdx + 1);
    },
    onClickImagePosition(position) {
      this.UPDATE_SELECTED_IMG_URL(position);
    },
    onClickRegiImg(e) {
      const files = e.target.files;
      this.UPDATE_IMAGE_FILES(files);
      e.target.value = null;
    }
  }
};
</script>

<style scoped>
.plus-shape {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.dir-right-shape,
.dir-left-shape {
  position: absolute;
  top: 43%;
}
.dir-right-shape {
  right: 3%;
}
.dir-left-shape {
  left: 3%;
}
.dir-right-shape::after {
  content: "→";
  font-size: 40px;
}
.dir-left-shape::after {
  content: "←";
  font-size: 40px;
}
.plus-shape::after {
  display: inline-block;
  content: "➕";
  font-size: 40px;
}
.carousel-image-idx--wrap {
  position: absolute;
  width: 100%;
  bottom: 3%;
  display: flex;
}
.carousel-image-idx--list {
  margin: 0 auto;
}
.carousel-image-idx--list::after {
  content: "";
  display: block;
  clear: both;
}
.carousel-image-idx--item {
  display: block;
  float: left;
  font-size: 15px;
  margin-right: 2px;
  padding: 2px 3px;
}
.carousel-image-idx--item:last-child {
  margin-right: 0;
}
.carousel-image-idx--item::before {
  content: "⚪️";
}
.carousel-image-idx-active--item::before {
  content: "⚫️";
}
#carousel--box {
  display: inline-block;
  position: relative;
  height: 350px;
  width: 350px;
}
#carousel--bg {
  display: block;
  height: 100%;
  width: 100%;
  background: #e0e0e0;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
#carousel__input {
  display: none;
}
</style>
