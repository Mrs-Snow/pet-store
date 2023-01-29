<template>
    <div class="header">
        <div class="base">
            <span class="welcome" ref="uname">您好, {{ $setUser() }}!</span>
            <cart :cartNum="5"></cart>
            <user></user>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, onMounted,ref,getCurrentInstance,defineExpose } from 'vue'
import user from './header/user.vue';
import cart from './header/cart.vue';
import { useRouter } from 'vue-router';
import { number, string } from 'vue-types';




export default defineComponent({
    
    name:'my-header',
    components: {
        user,
        cart
  },
    setup () {
        const uname = ref()
        const app = getCurrentInstance()
        
        
        const setUsername =()=> {
                const user = sessionStorage.getItem('user')
                console.log(user)
                if(user){
                    const {nickName} = JSON.parse(user)
                    console.log(nickName)
                    username.value=nickName
                }
           
        }

        defineExpose({setUsername})

        function userLoginOut(){

        }

        return {
            uname,
            setUsername,
            userLoginOut
        }
    }
})


</script>

<style scoped>
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