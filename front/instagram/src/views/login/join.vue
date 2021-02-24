<template>
  <div id="test--popup">
    <h1>회원가입</h1>
    <div>
      <label>
        아이디 :
        <input type="text" placeholder="아이디를 입력해주세요." v-model="id" />
      </label>
      <label>
        비밀번호 :
        <input
          type="password"
          placeholder="비밀번호를 입력해주세요."
          v-model="pwd"
        />
      </label>
      <button @click="regiUser">회원가입</button>
      <button @click="backBtn">뒤로가기</button>
    </div>
    <h1>{{ title }}</h1>
    <p>
      {{ content }}
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      pwd: ""
    };
  },
  methods: {
    regiUser() {
      alert("추카합니다 회원가입입니다.");
    },
    backBtn() {
      this.$store.commit("closePopup");
    }
  }
};
</script>

<style>
#test--popup {
  width: 700px;
  height: 700px;
}
</style>
