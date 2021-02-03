import request from "@/utils/request";

export const getMain = id =>
  request({
    url: `feed/${id}`,
    method: "get"
  });

// export const getNextMain
export const insert = data =>
  request({
    url: "feed",
    method: "post",
    data,
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });
