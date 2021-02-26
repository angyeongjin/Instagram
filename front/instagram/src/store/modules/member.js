import { getToken, setToken, removeToken } from "@/utils/auth";
import { login, googlelogin, headerTest } from "@/api/member";

const state = {
  token: getToken(),
  name: "",
  email: "",
  memberId: "",
  picture: "",
  address: "",
  phone: ""
};

const mutations = {
  SET_TOKEN: (state, token) => (state.token = token),
  SET_NAME: (state, name) => (state.name = name),
  SET_EMAIL: (state, email) => (state.email = email),
  SET_MEMBER_ID: (state, id) => (state.memberId = id),
  SET_PICTURE: (state, picture) => (state.picture = picture),
  SET_PHONE: (state, phone) => (state.phone = phone),
  SET_PROFILEFILE: (state, profileFile) => (state.profileFile = profileFile)
};

const actions = {
  logout() {
    removeToken();
    location.reload();
  },
  headerTest() {
    return new Promise((resolve, reject) => {
      headerTest()
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  },
  googlelogin({ commit }, data) {
    return new Promise((resoleve, reject) => {
      googlelogin({
        idToken: data
      })
        .then(res => {
          setToken(data);
          commit("SET_TOKEN", data);
          commit("SET_NAME", res.data.name);
          commit("SET_MEMBER_ID", res.data.memberId);
          commit("SET_PICTURE", res.data.picture);
          resoleve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  },
  login({ commit }) {
    return new Promise((resolve, reject) => {
      login()
        .then(res => {
          commit("SET_TOKEN", res.data.uc.id_token);
          setToken(res.data.uc.id_token);
          resolve(res);
        })
        .catch(err => {
          console.log(err);
          reject(err);
        });
    });
  }
};

// 만약 모듈이 독립적이거나 재사용되기를 원한다면, namespaced: true라고 네임스페이스에 명시
export default {
  namespaced: true,
  state,
  mutations,
  actions
};
