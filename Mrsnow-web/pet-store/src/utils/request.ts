import axios from "axios"
import { message } from "ant-design-vue";
const request = axios.create({
    baseURL: import.meta.env.VITE_APP_API_BASEURL,
    timeout:5000,
    timeoutErrorMessage:'请求超时!'
});
request.interceptors.request.use((c) => {
    if(c.url){
        const url = c.url
        if(url.indexOf("/user")<0){
            const token = sessionStorage.getItem('token')
            if(token){
                c.headers={
                    ...c.headers,
                    token:token
                }
                return c
            } else{
                 message.info("请登录!")
              }       
        }
    }
    // const token = sessionStorage.getItem('Token')
    //         if(token){
    //             c.headers={
    //                 ...c.headers,
    //                 Token:'1'
    //             }
    //         }   
    return c;
})

request.interceptors.response.use(
    (res) => {
        if(res.data.code===-1){
            message.error(res.data.message)
        }
        if(res.data.data.token){
            sessionStorage.setItem('token',res.data.data.token)
        }
        return Promise.resolve(res)
    },
    (e) => {
        message.error(e.response.data.message);
        return Promise.reject(e);
    }
)
export default request;
