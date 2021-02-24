import * as feed from "@/api/feed";
export default {
  namespaced: true,
  state: {
    main: {
      feeds: [],
      pageNum: 1
    },
    profile: {
      feeds: [],
      pageNum: 1
    }
  },
  mutations: {
    UPDATE_MAIN_FEEDS: (state, feeds) => state.main.feeds.push(...feeds),
    UPDATE_LIKE_MAIN: (state, { idx, data }) =>
      (state.main.feeds[idx].likeList = data),
    UPDATE_PAGENUM_MAIN: state => state.main.pageNum++,
    SET_PROFILE_FEEDS: (state, feeds) => (state.profile.feeds = feeds),
    UPDATE_PROFILE_FEEDS: (state, feed) => state.profile.feeds.push(feed),
    UPDATE_LIKE_PROFILE: (state, { idx, data }) =>
      (state.profile.feeds[idx].likeList = data),
    DELETE_PROFILE_FEEDS: (state, idx) => state.profile.feeds.splice(idx, 1)
  },
  actions: {
    getMainFeeds({ state, commit }) {
      return feed
        .getMain(state.main.pageNum)
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
