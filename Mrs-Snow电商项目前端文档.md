# Mrs-Snow电商项目前端文档

## 一、 环境搭建



本项目技术栈:   Vue3、Vite、Ant-Design-Vue、Typescript



### 1.开始

使用vite脚手架搭建vue3项目

```
$ npm create vite@latest
```

然后根据提示搭建TS VUE3项目



### 2.项目准备



创建.env文件,填入全局配置. 根据实际需求,创建开发环境配置,测试环境配置文件.env.development等

![image-20230117164314650](/Users/dongzhen/Library/Application Support/typora-user-images/image-20230117164314650.png)



#### 2.1 main.ts

```typescript
import { createApp } from 'vue'

import './style.css'

import App from './App.vue'

import Antd from 'ant-design-vue';

import router from './router';

import 'ant-design-vue/dist/antd.css';



const app = createApp(App);



app.use(Antd).use(router).mount('#app');
```

#### 2.2 app.vue

引入ant-design-vue, use(router)是关于下一步的路由配置

```typescript
<template>
    <my-header></my-header>
    <router-view></router-view>
    <my-footer></my-footer>
</template>
<script setup lang="ts">
import MyFooter from './components/layout/MyFooter.vue';
import MyHeader from './components/layout/MyHeader.vue';
</script>


<style scoped>

</style>
```

这里的页面布局可以自己去定义,本示例项目采用了上中下三个横向的模块布局,中间使用动态路由

#### 2.3 router

在src目录下创建router文件夹,新建index.ts文件

```typescript
import { createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";

import Login from "@/views/login/Login.vue";

//不想每次都引入可以写成 component: ()=> import('../views/store/index.vue')

const routes: RouteRecordRaw[] =[

​    {

​        path: "/login",

​        name: 'login',

​        component: Login

​    }

]

const router = createRouter({

​    routes,

​    history:createWebHashHistory(),

})

//全局导航守卫 可选
// router.beforeEach((to,from)=>{
//     var token = sessionStorage.getItem("token")
//     if(!token && to.name !== 'login'){
//         return {name: 'login'}
//     }
//     return from
// })

export default router;
```

#### 2.4 封装axios

```typescript
import axios from "axios"

import { message } from "ant-design-vue";

const request = axios.create({

​    baseURL: import.meta.env.VITE_APP_API_BASEURL,

​    timeout:5000,

});

request.interceptors.request.use((c) => {

​    if(c.url){

​        const url = c.url

​        console.log(url.indexOf("/user"))

​        if(url.indexOf("/user")<0){

​            const token = sessionStorage.getItem('token')

​            if(token){

​                c.headers={

​                    ...c.headers,

​                    token

​                }

​            } else{

​                 message.info("请登录!")

​              }       

​        }

​    }

​    return c;

})



request.interceptors.response.use(

​    (res) => {

​        console.log(Promise.resolve(res.data.data))

​        if(res.data.code===-1){

​            message.error(res.data.message)

​        }

​        return Promise.resolve(res)

​    },

​    (e) => {

​        message.error(e.response.data.message);

​        return Promise.reject(e);

​    }

)

export default request;
```

然后就可以封装自己的api了,不封装直接使用也行

```typescript
import request from "../utils/request";



export default function doLogin(user: User){

​    return request.post('/user/login',user)

}
```



## 二、 运行

## 三、打包