import * as feed from "@/api/feed";
// const xor = (arr, item) =>
//   arr.includes(item) ? arr.filter(i => i !== item) : [...arr, item];
export default {
  namespaced: true,
  state: {
    mainFeeds: [],
    profileFeeds: []
  },
  mutations: {
    SET_MAIN_FEEDS: (state, feeds) => (state.mainFeeds = feeds),
    UPDATE_MAIN_FEEDS: (state, feeds) => state.mainFeeds.push(...feeds),
    UPDATE_LIKE: (state, { idx, data }) =>
      (state.mainFeeds[idx].likeList = data),
    SET_PROFILE_FEEDS: (state, feeds) => (state.profileFeeds = feeds),
    UPDATE_PROFILE_FEEDS: (state, feed) => state.profileFeeds.push(feed),
    DELETE_PROFILE_FEEDS: (state, idx) => state.profileFeeds.splice(idx, 1)
  },
  actions: {
    getMainFeeds({ commit }) {
      feed
        .getMain()
        .then(res => {
          console.log("getMainFeeds", res);
          commit("SET_MAIN_FEEDS", res.data);
          console.log("getMainFeeds success");
        })
        .catch(err => {
          console.log("getMainFeeds", err);
        });
    },
    getNextMainFeeds({ commit }) {
      feed
        .getNextMain()
        .then(res => {
          // console.log("getNextMainFeeds", res);
          console.log("current", document.scrollingElement.scrollTop);
          commit("UPDATE_MAIN_FEEDS", res.data);
          console.log("height", document.scrollingElement.scrollHeight);
          // console.log("getNextMainFeeds success");
        })
        .catch(err => {
          console.log("getMainFeeds", err);
        });
    },
    getProfileFeeds({ commit }, memberId) {
      console.log("가저오는중..");
      feed
        .getProfile(memberId)
        .then(res => {
          console.log("getProfileFeedds", res);
          commit("SET_PROFILE_FEEDS", res.data);
          console.log("getProfileFeedds success");
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
        console.log("addProfileFeed", res);
        commit("UPDATE_PROFILE_FEEDS", res.data);
        console.log("addProfileFeed success");
      });
    },
    deleteProfileFeed({ commit }, data) {
      console.log(data);
      feed
        .delFeed(data.feedId)
        .then(res => {
          console.log("deleteProfileFeed", res);
          commit("DELETE_PROFILE_FEEDS", data.idx);
          console.log("deleteProfileFeed success");
        })
        .catch(err => {
          console.log("deleteProfileFeed", err);
        });
    },
    updateLike({ commit }, { feedId, idx }) {
      feed
        .like(feedId)
        .then(res => {
          console.log("updateLike", res);
          commit("UPDATE_LIKE", { idx, data: res.data });
          console.log("updateLike success");
        })
        .catch(err => {
          console.log("updateLike", err);
        });
    }
  }
};
