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
        name: 'store',
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