import * as feed from "@/api/feed";
export default {
  namespaced: true,
  state: {
    mainFeeds: [],
    mainPageNum: 1,
    profileFeeds: [],
    profilePageNum: 1
  },
  mutations: {
    // SET_MAIN_FEEDS: (state, feeds) => (state.mainFeeds = feeds),
    UPDATE_MAIN_FEEDS: (state, feeds) => state.mainFeeds.push(...feeds),
    UPDATE_LIKE_MAIN: (state, { idx, data }) =>
      (state.mainFeeds[idx].likeList = data),
    UPDATE_PAGENUM_MAIN: state => state.mainPageNum++,
    SET_PROFILE_FEEDS: (state, feeds) => (state.profileFeeds = feeds),
    UPDATE_PROFILE_FEEDS: (state, feed) => state.profileFeeds.push(feed),
    UPDATE_LIKE_PROFILE: (state, { idx, data }) =>
      (state.profileFeeds[idx].likeList = data),
    DELETE_PROFILE_FEEDS: (state, idx) => state.profileFeeds.splice(idx, 1)
  },
  actions: {
    getMainFeeds({ state, commit }) {
      return feed
        .getMain(state.mainPageNum)
        .then(res => {
          if (res.status === 204) return res.status;
          commit("UPDATE_MAIN_FEEDS", res.data);
          commit("UPDATE_PAGENUM_MAIN");
        })
        .catch(err => {
          console.log("getMainFeeds", err);
        });
    },
    getProfileFeeds({ commit }, memberId) {
      feed
        .getProfile(memberId)
        .then(res => {
          commit("SET_PROFILE_FEEDS", res.data);
        })
        .catch(err => {
          console.log("getProfileFeedds", err);
        });
    },
    addProfileFeed({ commit }, data) {
      const formData = new FormData();
      for (var i = 0; i < data.files.length; i++) {
        formData.append("images", data.files[i].info);
      }
      formData.append("contents", data.contents);

      console.log("전송중..");
      return feed.insert(formData).then(res => {
        commit("UPDATE_PROFILE_FEEDS", res.data);
        console.log("addProfileFeed success");
      });
    },
    deleteProfileFeed({ commit }, data) {
      console.log(data);
      feed
        .delFeed(data.feedId)
        .then(() => {
          commit("DELETE_PROFILE_FEEDS", data.idx);
        })
        .catch(err => {
          console.log("deleteProfileFeed", err);
        });
    },
    updateLike({ commit }, { field, feedId, idx }) {
      feed
        .like(feedId)
        .then(res => {
          field === "main"
            ? commit("UPDATE_LIKE_MAIN", { idx, data: res.data })
            : commit("UPDATE_LIKE_PROFILE", { idx, data: res.data });
        })
        .catch(err => {
          console.log("updateLike", err);
        });
    }
  }
};
