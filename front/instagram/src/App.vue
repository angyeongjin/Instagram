<template>
  <div id="wrap">
    <insta-header v-if="login" />
    <router-view />
  </div>
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
