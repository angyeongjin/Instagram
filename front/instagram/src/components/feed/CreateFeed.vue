<template>
  <div id="select-image-wrap">
    <label for="select-image">여기서 이미지 넣기</label>
    <input
      type="file"
      id="select-image"
      class="none"
      @change="selectImage"
      multiple
    />
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  data: () => ({
    imageFiles: [],
    contents: ""
  }),
  methods: {
    ...mapActions("feed", ["addProfileFeed"]),
    selectImage(e) {
      const files = e.target.files;
      console.log(files.length);
      for (var i = 0; i < files.length; i++) {
        if (
          !files[i].type.match("image/.*") ||
          this.imageFiles.find(x => x.info.name === files[i].name)
        ) {
          continue;
        }

        this.imageFiles.push({
          url: URL.createObjectURL(files[i]),
          info: files[i]
        });
        URL.revokeObjectURL(files[i]);
      }
      e.target.value = null;
      console.log(this.imageFiles);
    },
    async uploadImage() {
      const data = {
        files: this.imageFiles,
        contents: this.contents
      };

      try {
        await this.addProfileFeed(data);
        this.imageFiles = [];
        // this.contents = ""; // contents reset
        /* 
          모달 on/off 하는 거
        */
      } catch (err) {
        console.log("uploadImage Error", err);
      }
    },
    deleteImage(idx) {
      if (confirm("삭제하시겠습니까?")) {
        this.imageFiles.splice(idx, 1);
      }
    }
  }
};
</script>
