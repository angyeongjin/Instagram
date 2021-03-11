<template>
  <div id="filter--wrap">
    <div id="filter-list--wrap">
      <ul id="filter-list">
        <li class="filter-item" v-for="filter in filters" :key="filter">
          <strong>{{ filter }}</strong>
          <img
            :width="width"
            :height="height"
            :class="`filter-${filter}`"
            :src="selectedImg"
            :alt="`filter-${filter}`"
            @click="changeFilter(filter)"
          />
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
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
      selectedFilterClass: null,
      filters: ["blur", "grayscale", "drop-shadow"]
    };
  },
  methods: {
    ...mapMutations("createFeed", ["SET_FILTER"]),
    changeFilter(filter) {
      this.SET_FILTER(filter);
    }
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
