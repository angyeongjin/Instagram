<template>
  <div id="test">
    <input type="file" @change="selectImage" multiple id="selectedImages" />
    <button type="button" @click="uploadImage">
      &nbsp;파일 전송&nbsp;
    </button>
    <div id="preview">
      <img
        v-for="(img, idx) in imageFiles"
        :key="`image${idx}`"
        :src="img.url"
        @click="deleteImage(idx)"
      />
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    imageFiles: []
  }),
  methods: {
    selectImage(e) {
      const files = e.target.files;
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
      console.log("imageFiles", this.imageFiles);
    },
    uploadImage() {
      const formData = new FormData();
      const files = this.imageFiles;

      for (var i = 0; i < files.length; i++) {
        formData.append("image", files[i].info);
      }

      console.log(formData);
      console.log("formData", formData.getAll("image"));
    },
    deleteImage(idx) {
      if (confirm("삭제하시겠습니까?")) {
        this.imageFiles.splice(idx, 1);
      }
    }
  }
};
</script>
