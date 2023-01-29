import request from "../utils/request";

export default function doLogin(user: User){
    return request.post('/user/login',user)
}

export function findPassword(user: User){
    return request.post('/user/findPassword',user)
}

export function register(user: User){
    return request.post('/user/register',user)
}

export function updatePassword(data: JO){
    return request.post('/user/findPassword/updatePassword',data)
}

export function setUsername(){
    // console.log("运行了全局方法")
    const userInfo = sessionStorage.getItem('user')
    if(userInfo){
        const info = JSON.parse(userInfo)
        // console.log(info)
        return info.data.nickName
    }else{
        return '游客'
    }
}

