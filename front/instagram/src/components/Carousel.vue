<template>
  <div id="carousel--wrap">
    <div id="carousel--box">
      <div @click="onClickLeft" class="dir-left-shape"></div>
      <div @click="onClickRight" class="dir-right-shape"></div>
      <label
        id="carousel--bg"
        :style="`background-image: url(${selectedImgUrl});`"
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
            class="carousel-image-idx--item carousel-image-idx-active--item"
            @click="onClickImagePosition(1)"
          ></li>
          <li
            class="carousel-image-idx--item"
            @click="onClickImagePosition(2)"
          ></li>
          <li
            class="carousel-image-idx--item"
            @click="onClickImagePosition(3)"
          ></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Carousel",
  data() {
    return {
      selectedImgUrl: null
    };
  },
  methods: {
    onClickLeft() {
      console.log(
        "[🐶 DDD] ~ file: Carousel.vue ~ line 32 ~ onClickLeft ~ onClickLeft"
      );
    },
    onClickRight() {
      console.log(
        "[🐶 DDD] ~ file: Carousel.vue ~ line 32 ~ onClickRight ~ onClickRight"
      );
    },
    onClickImagePosition(position) {
      console.log(
        "[🐶 DDD] ~ file: Carousel.vue ~ line 49 ~ onClickImagePosition ~ position",
        position
      );
    },
    onClickRegiImg() {
      console.log(
        "[🐶 DDD] ~ file: Carousel.vue ~ line 32 ~ onClickRegiImg ~ onClickRegiImg"
      );
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
