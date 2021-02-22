<template>
  <div id="popup__wrap">
    <component
      :is="POPUP_COMPONENT"
      v-bind="POPUP_PROP"
      class="popup__box"
    ></component>
    <div id="popup--bg" @click="onClickClose"></div>
  </div>
</template>

<script>
export default {
  name: "FullPopup",
  computed: {
    POPUP_COMPONENT() {
      console.log(
        "[🐶 DDD] ~ file: FullPopup.vue ~ line 19 ~ POPUP_COMPONENT ~ this.$store.state.popupcompo",
        this.$store.state.popupCompo
      );
      return this.$store.state.popupCompo;
    },
    POPUP_PROP() {
      console.log(
        "[🐶 DDD] ~ file: FullPopup.vue ~ line 26 ~ POPUP_PROP ~ this.$store.state.popupProp",
        this.$store.state.popupProp
      );
      return this.$store.state.popupProp;
    }
  },
  methods: {
    onClickClose() {
      this.$store.commit("closePopup");
      console.log(
        "[🐶 DDD] ~ file: FullPopup.vue ~ line 15 ~ onClickClose ~ this.$store.state.isPupop",
        this.$store.state.isPupop
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
