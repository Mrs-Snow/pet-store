import axios from "axios"
import { message } from "ant-design-vue";
const request = axios.create({
    baseURL: import.meta.env.VITE_APP_API_BASEURL,
    timeout:5000,
});
request.interceptors.request.use((c) => {
    if(c.url){
        const url = c.url
        console.log(url.indexOf("/user"))
        if(url.indexOf("/user")<0){
            const token = sessionStorage.getItem('token')
            if(token){
                c.headers={
                    ...c.headers,
                    token
                }
            } else{
                 message.info("请登录!")
              }       
        }
    }
    return c;
})

request.interceptors.response.use(
    (res) => {
        console.log(Promise.resolve(res.data.data))
        if(res.data.code===-1){
            message.error(res.data.message)
        }
        return Promise.resolve(res)
    },
    (e) => {
        message.error(e.response.data.message);
        return Promise.reject(e);
    }
)
export default request;
