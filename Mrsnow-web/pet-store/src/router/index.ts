import { createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";
import Login from "@/views/login/Login.vue";
const routes: RouteRecordRaw[] =[
    {
        path: "/login",
        name: 'login',
        component: Login
    },
    {
        path: '/',
        name: 'index',
        component: ()=> import('../views/store/index.vue')
    },
    {
        path: "/register",
        component: ()=> import('../views/login/register.vue')
    },
    {
        path: "/findPassword",
        component: ()=> import('../views/login/findPassword.vue')
    },
    {   
        name: 'updatePassword',
        path: "/findPassword/updatePassword/:username",
        component: ()=> import('../views/login/updatePassword.vue')
    },
    {   
        name: 'goods',
        path: "/goods",
        component: ()=> import('../views/store/goods.vue')
    },
    {
        name: 'myCart',
        path: '/myCart',
        component: ()=> import('../views/cartInfo/myCart.vue')
    },
    {
        name: 'personCenter',
        path: '/personCenter',
        component: ()=> import('../views/personInfo/personCenter.vue')
    },
    {
        name: 'goodsDetail',
        path: '/goodsDetail',
        component: ()=> import('../views/goods/goodsDetail.vue')
    },
    {
        name: 'store',
        path: '/store',
        component: ()=> import('../views/store/store.vue')
    },
    {
        name: 'settle',
        path: '/settle',
        component: ()=> import('../views/settle/index.vue')
    },
    {
        name: 'orders',
        path: '/orders/:cartIds',
        component: ()=> import('../views/settle/Orders.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        component: ()=> import('../views/notFound.vue')
    }
];
const router = createRouter({
    routes,
    history:createWebHashHistory(),
})
//全局导航守卫
// router.beforeEach((to,from)=>{
//     var token = sessionStorage.getItem("token")
//     if(!token && to.name !== 'login'){
//         return {name: 'login'}
//     }
//     return from
// })
export default router;