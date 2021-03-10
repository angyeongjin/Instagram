<template>
  <div id="filter--wrap">
    <div id="filter-list--wrap">
      <ul id="filter-list">
        <li class="filter-item">
          <strong>blur</strong>
          <img
            :width="width"
            :height="height"
            class="filter-blur"
            :src="selectedImg"
            alt="filter-blur"
          />
        </li>
        <li class="filter-item">
          <strong>grayscale</strong>
          <img
            :width="width"
            :height="height"
            class="filter-grayscale"
            :src="selectedImg"
            alt="filter-grayscale"
          />
        </li>
        <li class="filter-item">
          <strong>drop-shadow</strong>
          <img
            :width="width"
            :height="height"
            class="filter-drop-shadow"
            :src="selectedImg"
            alt="filter-drop-shadow"
          />
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "ImageFilter",
  props: {
    selectedImg: {
      default: "https://via.placeholder.com/200x200",
      type: String
    }
  },
  data() {
    return {
      height: 150,
      width: 150,
      selectedFilterClass: null
    };
  }
};
</script>

<style>
#filter-list--wrap {
  display: flex;
}
#filter-list {
  margin: 0 auto;
}
.filter-item {
  float: left;
  margin-right: 20px;
}
.filter-item:last-child {
  margin-right: 0;
}
.filter-item > strong {
  display: block;
  text-align: center;
}
.filter-blur {
  filter: blur(5px);
}
.filter-grayscale {
  filter: grayscale(80%);
}
.filter-drop-shadow {
  filter: drop-shadow(16px 16px 20px red) invert(75%);
}
</style>
