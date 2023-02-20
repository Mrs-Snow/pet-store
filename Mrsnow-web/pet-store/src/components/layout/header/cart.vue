<template>
    <span class="header" @click="myCart">
        <a-tooltip class="cart" title="购物车">
            <a-button shape="circle" size="large">
            <template #icon><ShoppingCartOutlined :style="{fontSize: '27px'}" />
                <!-- <a-badge
                 :count="cartNum"
                 :offset=[8,-49]
                ></a-badge> -->
            </template>
            </a-button>
        </a-tooltip>
    </span>
</template>

<script lang="ts">
import { ShoppingCartOutlined } from '@ant-design/icons-vue';
import axios from 'axios';
import { defineComponent, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import { number } from 'vue-types';
import { message } from "ant-design-vue";
import request from '../../../utils/request';
export default defineComponent({
    name:'header-cart',
    props:{
        cartNum:{
            type:Number,
            default: 0
        }
    },
    components: {
    ShoppingCartOutlined
},
    setup (cartNum) {
        const router = useRouter();

        function myCart(){
            
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/cart/myCart',{data: userId,current:1,pageSize:5}).then(res=>{
                    router.push({
                        path: '/myCart',
                        params: {
                            cartInfo: res.data
                        }
                })
                    
            })
                
            } else{
                message.info('请登录!')
                router.push({
                    path: '/login'
                })
            }
            
        }
        return {myCart}
    }
})
</script>

<style scoped>
    .cart{
        margin-right: 30px;
    }
</style>