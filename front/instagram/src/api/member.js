import request from "@/utils/request";

export const headerTest = () => {
  return request({
    url: "member",
    method: "get"
  });
};

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
