import request from "../utils/request";

export default function doLogin(user: User){
    return request.post('/user/login',user)
}