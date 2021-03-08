import request from "@/utils/request";

export const login = () => {
  return request({
    url: "test/getMsg",
    method: "get"
  });
};

export const googlelogin = data => {
  return request({
    url: "member/google",
    method: "post",
    data
  });
};

export const getUser = memberId => {
  return request({
    url: `member/${memberId}`,
    method: "get"
  });
};

export const getUsers = input => {
  return request({
    url: `member/search/${input}`,
    method: "get"
  });
};

export const follow = data => {
  return request({
    url: "follow",
    method: "post",
    data
  });
};
