<template>
  <div id="popup__wrap">
    <component
      :is="POPUP_COMPONENT"
      v-bind="POPUP_PROP"
      class="popup__box"
      :style="POPUP_WINDOW_SIZE"
    ></component>
    <div id="popup--bg" @click="onClickClose"></div>
  </div>
</template>

<script>
export default {
  name: "FullPopup",
  computed: {
    POPUP_COMPONENT() {
      return this.$store.state.popupCompo;
    },
    POPUP_PROP() {
      return this.$store.state.popupProp;
    },
    POPUP_WINDOW_SIZE() {
      const { height, h, width, w } =
        this.$store.state.popupOptions?.windowSize || {};
      return `width: ${width || w || "500px"}; height: ${height ||
        h ||
        "800px"}`;
    }
  },
  methods: {
    onClickClose() {
      this.$store.commit("closePopup");
      console.log(
        "[🐶 DDD] ~ file: FullPopup.vue ~ line 15 ~ onClickClose ~ this.$store.state.isPupop",
        this.$store.state.isPopup
      );
    }
  }
};
</script>

<style scoped>
#popup__wrap {
  width: 100%;
  height: 100%;
}
#popup--bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1000;
  background: rgba(0, 0, 0, 0.9);
  width: 100%;
  height: 100%;
}
.popup__box {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  min-width: 500px;
  min-height: 300px;
  background: #fff;
  z-index: 1001;
}
</style>
