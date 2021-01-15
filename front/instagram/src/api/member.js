import request from "@/utils/request";

export const login = () => {
  return request({
    url: "test/getMsg",
    method: "get"
  });
};
