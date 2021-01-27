import { getToken, setToken } from "@/utils/auth";
import { login } from "@/api/member";

const state = {
  token: getToken(),
  name: "",
  email: "",
  address: "",
  phone: ""
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_EMAIL: (state, email) => {
    state.email = email;
  },
  SET_PHONE: (state, phone) => {
    state.phone = phone;
  },
  SET_PROFILEFILE: (state, profileFile) => {
    state.profileFile = profileFile;
  }
};

const actions = {
  googlelogin({ commit }, data) {
    setToken(data.uc.id_token);
    commit("SET_TOKEN", data.uc.id_token);
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
