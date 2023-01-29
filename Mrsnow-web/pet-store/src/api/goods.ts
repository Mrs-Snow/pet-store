import request from "../utils/request";

export default function searchByKey(key: JO){
    request.post('/goods/searchByKey',key)
}