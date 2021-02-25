import request from "@/utils/request";

export const getMain = pageNum =>
  request({
    url: `feed/main/${pageNum}`,
    method: "get"
  });

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

export const insertComment = data =>
  request({
    url: "comment",
    method: "post",
    data
  });

export const insertSubComment = (id, data) =>
  request({
    url: `subComment/${id}`,
    method: "post",
    data
  });

export const like = feedId =>
  request({
    url: `like`,
    method: "post",
    data: {
      feedId
    }
  });
