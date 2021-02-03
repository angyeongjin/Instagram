import * as feed from "@/api/feed";

export default {
  namespaced: true,
  state: {
    mainFeeds: [],
    profileFeeds: []
  },
  mutations: {
    SET_MAIN_FEEDS: (state, feeds) => (state.mainFeeds = feeds),
    UPDATE_MAIN_FEEDS: (state, feeds) => state.mainFeeds.push(feeds),
    SET_PROFILE_FEEDS: (state, feeds) => (state.profileFeeds = feeds),
    UPDATE_PROFIEL_FEEDS: (state, feed) => state.profileFeeds.push(feed)
  },
  actions: {
    getMainFeeds({ commit }, id) {
      feed
        .getMain(id)
        .then(res => {
          console.log(res);
          commit("SET_MAIN_FEEDS");
          console.log("getMainFeeds success");
        })
        .catch(err => {
          console.log("getMainFeeds", err);
        });
    },
    getNextMainFeeds({ commit }) {
      commit("UPDATE_MAIN_FEEDS");
    },
    getProfileFeeds({ commit }) {
      console.log("getProfileFeedds");
      commit("SET_PROFILE_FEEDS");
    },
    addProfileFeed({ commit }, data) {
      feed
        .insert(data)
        .then(res => {
          commit("UPDATE_PROFIEL_FEEDS", res);
          console.log("addFeed success", res);
        })
        .catch(err => {
          console.log("addFeed", err);
        });
    }
  }
};
