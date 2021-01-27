<template>
  <insta-header v-if="this.$store.getters.token" />
  <router-view />
</template>
<script>
import InstaHeader from "@/components/Header.vue";
export default {
  name: "app",
  components: { InstaHeader },
  data() {
    return {
      login: true
    };
  }
};
</script>
