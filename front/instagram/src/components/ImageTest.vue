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
        height="420"
        width="640"
      />
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data: () => ({
    imageFiles: [],
    contents: ` is simply dummy text of the printing and typesetting industry.
                  Lorem Ipsum has been the industry's standard dummy text ever
                  since the 1500s, when an unknown printer took a galley of type
                  and scrambled it to make a type specimen book. It has survived
                  not only five centuries, but also the leap into electronic
                  typesetting, remaining essentially unchanged. It was
                  popularised in the 1960s with the release of Letraset sheets
                  containing Lorem Ipsum passages, and more recently with
                  desktop publishing software like Aldus PageMaker including
                  versions of Lorem Ipsum. Why do we use it? It is a long
                  established fact that a reader will be distracted by the
                  readable content of a page when looking at its layout. The
                  point of using Lorem Ipsum is that it has a more-or-less
                  normal distribution of letters, as opposed to using 'Content
                  here, content here', making it look like readable English.
                  Many desktop publishing packages and web page editors now use
                  Lorem Ipsum as their default model text, and a search for
                  'lorem ipsum' will uncover many web sites still in their
                  infancy. Various versions have evolved over the years,
                  sometimes by accident, sometimes on purpose (injected humour
                  and the like).`
  }),
  methods: {
    ...mapActions("feed", ["getMainFeeds", "addProfileFeed"]),
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
