import request from "@/utils/request";

export const getMain = () =>
  request({
    url: `feed/main`,
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

export const getProfile = memberId =>
  request({
    url: `feed/member/${memberId}`,
    method: "get"
  });

export const delFeed = feedId =>
  request({
    url: `feed/${feedId}`,
    method: "delete"
  });
