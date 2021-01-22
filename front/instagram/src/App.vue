<template>
  <template v-if="login">
    <router-view />
  </template>
  <template v-else>
    <insta-header />
    <router-view name="main" />
  </template>
</template>
<script>
import InstaHeader from "@/components/Header.vue";
export default {
  name: "app",
  components: { InstaHeader },
  data() {
    return {
      login: false
    };
  }
};
</script>
