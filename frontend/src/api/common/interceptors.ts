// 로그인 인증이 필요해서 header에 JWT 토큰을 실어 보내는 axios intercepter
import store from "@/store/index";
// import { getAuthFromCookie } from "@/utils/cookies"

export function setInterceptors(instance: any) {
  // Add a request interceptor
  instance.interceptors.request.use(
    function (config: any) {
      // Do something before request is sent
      // console.log(config);
      config.headers["access-token"] = store.state.token;
      config.headers["Content-Type"] = "multipart/form-data";
      console.log(config);
      return config;
    },
    function (error: any) {
      // Do something with request error
      return Promise.reject(error);
    }
  );

  // // Add a response interceptor
  // instance.interceptors.response.use(
  //   function (response) {
  //     // Any status code that lie within the range of 2xx cause this function to trigger
  //     // Do something with response data
  //     return response;
  //   },
  //   function (error) {
  //     // Any status codes that falls outside the range of 2xx cause this function to trigger
  //     // Do something with response error
  //     return Promise.reject(error);
  //   }
  // );
  return instance;
}
