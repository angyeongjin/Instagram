<template>
  <div id="login" class="user">
    <div class="wrapC">
      <div style="font-size:40px; margin-bottom:20px">
        Instagram
      </div>
      <div class="input-with-label">
        <input
          id="email"
          v-model="email"
          :class="{
            error: error.email,
            complete: !error.email && email.length !== 0
          }"
          placeholder="이메일을 입력하세요."
          type="text"
          @keyup.enter="login"
        />
        <label for="email">이메일</label>
        <div v-if="error.email" class="error-text">
          {{ error.email }}
        </div>
      </div>

      <div class="input-with-label">
        <input
          id="password"
          v-model="password"
          type="password"
          :class="{
            error: error.password,
            complete: !error.password && password.length !== 0
          }"
          placeholder="비밀번호를 입력하세요."
          @keyup.enter="login"
        />
        <label for="password">비밀번호</label>
        <div v-if="error.password" class="error-text">
          {{ error.password }}
        </div>
      </div>
      <button
        class="btn btn--back btn--login"
        @click="login"
        :class="{ disabled: !isSubmit }"
      >
        로그인
      </button>
      <div id="google-signin-btn"></div>
      <div class="google-login">
        <button class="google-btn">
          <span style="color:#385185">Google로 로그인</span>
        </button>
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar" />
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
          <button class="btn--text">
            비밀번호 찾기
          </button>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <button class="btn--text">
            가입하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
export default {
  data: () => {
    return {
      value: false,
      email: "",
      password: "",
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false
    };
  },
  mounted() {
    gapi.signin2.render("google-signin-btn", {
      onsuccess: this.onSignIn
    });
  },
  //   created() {
  //     this.component = this;

  //     this.passwordSchema
  //       .is()
  //       .min(8)
  //       .is()
  //       .max(100)
  //       .has()
  //       .digits()
  //       .has()
  //       .letters();
  //   },
  //   watch: {
  //     password: function(v) {
  //       this.checkForm();
  //     },
  //     email: function(v) {
  //       this.checkForm();
  //     }
  //   },
  methods: {
    // checkForm() {
    //   if (this.email.length >= 0 && !EmailValidator.validate(this.email))
    //     this.error.email = "이메일 형식이 아닙니다.";
    //   else this.error.email = false;
    //   if (
    //     this.password.length >= 0 &&
    //     !this.passwordSchema.validate(this.password)
    //   )
    //     this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
    //   else this.error.password = false;
    //   let isSubmit = true;
    //   Object.values(this.error).map(v => {
    //     if (v) isSubmit = false;
    //   });
    //   this.isSubmit = isSubmit;
    // },
    onSignIn(googleUser) {
      console.log(googleUser);
    },
    login() {
      this.$store
        .dispatch("member/login")
        .then(res => {
          console.log(res.data);
          //this.$router.push({ path: "/about" });
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2d4053;
}
.google-login {
  color: rgba(var(--d69, 0, 149, 246), 1);
  margin: 20px;
}
.google-btn {
  box-sizing: border-box;
  cursor: pointer;
  font-weight: 600;
  text-align: center;
}
#google-signin-btn {
  text-align: center;
  margin: 20px;
}
</style>
