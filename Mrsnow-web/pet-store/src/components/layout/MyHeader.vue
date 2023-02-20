<template>
    <div class="header">
        <div class="base">
            <span class="welcome" ref="uname">您好, {{ $setUser() }}!</span>
            <span class="exit" v-if="showExit" @click="exit">退出登录</span>
            <cart :cartNum="5"></cart>
            <user></user>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, onMounted,ref,getCurrentInstance,inject } from 'vue'
import user from './header/user.vue';
import cart from './header/cart.vue';
import { useRouter } from 'vue-router';
import { number, string } from 'vue-types';
import { message } from 'ant-design-vue';




export default defineComponent({
    
    name:'my-header',
    components: {
        user,
        cart
  },
    setup () {
        const uname = ref()
        const app = getCurrentInstance()
        const showExit=ref(false);
        const router = useRouter();
        const reload = inject('reload')

        onMounted(()=>{
            const id = sessionStorage.getItem('userId')
            if(id){
                showExit.value=true;
            }else{
                showExit.value=false;
            }
        })

        function exit(){
            sessionStorage.clear()
            router.push({
                path: '/login'
            })
            reload()
            message.info('已退出登录~')
        }
        
        
        
        // const setUsername =()=> {
        //         const user = sessionStorage.getItem('user')
        //         // console.log(user)
        //         if(user){
        //             const {nickName} = JSON.parse(user)
        //             console.log(nickName)
        //             username.value=nickName
        //         }
           
        // }

        function userLoginOut(){

        }

        return {
            uname,
            userLoginOut,
            showExit,
            exit
        }
    }
})


</script>

<style scoped>
    .exit{
        color: rgb(248, 248, 248);
        text-decoration:underline;
        cursor: pointer;
    }
    .welcome{
        color: rgb(248, 248, 248);
        font-weight: 700;
        margin-right: 2%;
    }
    .header {
        flex-direction: row-reverse;
        width: 100%;
        background-color:rgb(189, 36, 176);
    }
    .base{
        width: 100%;
        margin-left: 40%;
    }
</style>