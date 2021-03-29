import Axios from "axios";
import store from "@/store";
import { getToken } from "@/utils/auth";

// axios 객체 생성
const service = Axios.create({
  baseURL: "http://52.78.154.74:8080/"
  // baseURL: "http://221.166.248.234:8080/"
  // baseURL: "http://instaclone.kro.kr:8080/"
  // baseURL: "http://192.168.43.132:8080/" //url = base url + request url
  //timeout: 5000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      // config.headers["Access-Control-Allow-Origin"] = "*";
      config.headers["id_token"] = getToken();
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default service;
