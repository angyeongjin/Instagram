import * as feed from "@/api/feed";
import { getUser } from "@/api/member";
export default {
  namespaced: true,
  state: {
    main: {
      feeds: [],
      pageNum: 1
    },
    profile: {
      //팔로워 팔로우
      user: {
        followerList: [],
        followingList: []
      },
      feeds: [],
      pageNum: 1
    }
  },
  mutations: {
    SET_PROFILE_USER: (state, user) => (state.profile.user = user),
    UPDATE_PROFILE_USER_FOLLOW: (state, user) =>
      state.profile.user.followerList.push({ memberId: user }),
    DELETE_PROFILE_USER_FOLLOW: (state, user) => {
      var idx = state.profile.user.followerList.findIndex(
        x => x.memberId === user
      );
      state.profile.user.followerList.splice(idx, 1);
    },
    UPDATE_MAIN_COMMENT: (state, { comment, idx }) =>
      state.main.feeds[idx].commentList.push(comment),
    SET_MAIN_FEEDS: (state, feeds) => (state.main.feeds = feeds),
    UPDATE_MAIN_FEEDS: (state, feeds) => state.main.feeds.push(...feeds),
    UPDATE_LIKE_MAIN: (state, { idx, data }) =>
      (state.main.feeds[idx].likeList = data),
    UPDATE_PAGENUM_MAIN: (state, next) => (state.main.pageNum = next),
    SET_PROFILE_FEEDS: (state, feeds) => (state.profile.feeds = feeds),
    UPDATE_PROFILE_FEEDS: (state, feed) => state.profile.feeds.push(feed),
    UPDATE_LIKE_PROFILE: (state, { idx, data }) =>
      (state.profile.feeds[idx].likeList = data),
    DELETE_PROFILE_FEEDS: (state, idx) => state.profile.feeds.splice(idx, 1)
  },
  actions: {
    getUser({ commit }, id) {
      getUser(id)
        .then(res => {
          console.log(res.data);
          commit("SET_PROFILE_USER", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    insertComment({ commit }, { comment, idx }) {
      feed
        .insertComment(comment)
        .then(res => {
          commit("UPDATE_MAIN_COMMENT", { comment: res.data, idx });
        })
        .catch(err => {
          console.log(err);
        });
    },
    insertSubComment({ commit }, data) {
      feed
        .insertSubComment(data.commentId, data)
        .then(res => {
          console.log(res);
          commit;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getMainFeeds({ commit }, page = 1) {
      feed
        .getMain(page)
        .then(res => {
          console.log(res);
          commit("SET_MAIN_FEEDS", res.data);
          commit("UPDATE_PAGENUM_MAIN", page + 1);
        })
        .catch(err => {
          console.log("getMainFeeds", err);
        });
    },
    getNextMainFeeds({ state, commit }) {
      const page = state.main.pageNum;
      return feed
        .getMain(page)
        .then(res => {
          if (res.status === 204) return res.status;
          commit("UPDATE_MAIN_FEEDS", res.data);
          commit("UPDATE_PAGENUM_MAIN", page + 1);
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
      formData.append("filter", data.filter);

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
    },
    follow({ commit }, data) {
      feed
        .follow({ memberId: data.memberId })
        .then(res => {
          console.log(res);
          commit("UPDATE_PROFILE_USER_FOLLOW", data.myId);
        })
        .catch(err => {
          console.log(err);
        });
    },
    unfollow({ commit }, data) {
      feed
        .unfollow({ memberId: data })
        .then(res => {
          console.log(res);
          commit("DELETE_PROFILE_USER_FOLLOW", data);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
