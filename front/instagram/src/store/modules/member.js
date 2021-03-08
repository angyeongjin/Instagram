import { getToken, setToken, removeToken, userInfo } from "@/utils/auth";
import { login, googlelogin } from "@/api/member";
import * as member from "@/api/member";

const state = {
  token: getToken(),
  name: userInfo?.name,
  email: userInfo?.email,
  memberId: userInfo?.memberId,
  picture: userInfo?.picture,
  address: "",
  phone: ""
  //팔로우 팔로워
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
    sessionStorage.removeItem("member");
    location.reload();
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
          const member = {
            name: res.data.name,
            memberId: res.data.memberId,
            picture: res.data.picture
          };
          sessionStorage.setItem("member", JSON.stringify(member));
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
  },
  follow({ commit }, data) {
    member
      .follow({ memberId: data })
      .then(res => {
        console.log(res);
        commit;
      })
      .catch(err => {
        console.log(err);
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
