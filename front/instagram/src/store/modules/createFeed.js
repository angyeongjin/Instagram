export default {
  namespaced: true,
  state: {
    imageFiles: [],
    contents: "",
    selectedImgUrl: "",
    selectedIdx: 0
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
      state.selectedIdx = idx;
      state.selectedImgUrl = state.imageFiles[idx].url;
    }
  }
};
