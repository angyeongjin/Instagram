<template>
  <div>
    <router-view />
    <full-popup v-if="IS_POPUP" />
  </div>
</template>
<script>
import InstaHeader from "@/components/Header.vue";
import FullPopup from "./views/FullPopup.vue";

export default {
  name: "app",
  components: { InstaHeader, FullPopup },
  data() {
    return {};
  },
  computed: {
    IS_POPUP() {
      return this.$store.state.isPopup;
    }
  }
};
</script>
