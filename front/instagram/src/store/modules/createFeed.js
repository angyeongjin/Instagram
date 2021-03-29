export default {
  namespaced: true,
  state: {
    imageFiles: [],
    selectedImgUrl: undefined,
    selectedIdx: 0,
    filter: ""
  },
  mutations: {
    UPDATE_IMAGE_FILES(state, files) {
      for (var i = 0; i < files.length; i++) {
        if (
          !files[i].type.match("image/.*") ||
          state.imageFiles.find(x => x.info.name === files[i].name)
        ) {
          continue;
        }

        state.imageFiles.push({
          url: URL.createObjectURL(files[i]),
          info: files[i]
        });
        URL.revokeObjectURL(files[i]);
      }
      state.selectedImgUrl = state.imageFiles[state.selectedIdx].url;
    },
    UPDATE_SELECTED_IMG_URL(state, idx) {
      if (!(0 <= idx && idx <= state.imageFiles.length)) return;
      state.selectedIdx = idx;
      state.selectedImgUrl = state.imageFiles[idx].url;
    },
    CLEAR_IMAGE_FILES(state) {
      state.imageFiles = [];
      state.selectedImgUrl = undefined;
      state.selectedIdx = 0;
      state.filter = "";
    },
    SET_FILTER(state, filter = "") {
      state.filter = `filter-${filter}`;
    }
  }
};
