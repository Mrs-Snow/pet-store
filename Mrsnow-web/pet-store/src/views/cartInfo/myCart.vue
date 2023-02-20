<template>
    <div class="mycart">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: coral;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color: chocolate;">购物车</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>
        
        <div>
            <div v-if="hasCartInfo()" style="font-size: xxx-large; font-weight: 500; color: blueviolet; position: fixed; bottom: 500px; left: 550px;">🛒 购物车空空如也</div>
            
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { useRoute,useRouter } from 'vue-router';
import { Divider,message } from 'ant-design-vue';
import request from '../../utils/request';
export default defineComponent({
    components:{
        Divider
    },
    setup () {
        const route = useRoute()
        const router = useRouter()
        const cartData = ref()
        onMounted(()=>{
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/cart/myCart',{data: userId,current:1,pageSize:5}).then(res=>{
                    cartData.value = res.data.record
            })
                
            } else{
                message.info('请登录!')
                router.push({
                    path: '/login'
                })
            }
        })

        const handleBack =()=>{
            router.go(-1)
        }

        function hasCartInfo(){
            
            if(cartData.value){
                return false
            }
            return true
        }
        return {hasCartInfo,handleBack}
    }
})
</script>

<style scoped>
    .mycart{
        height: 800px;
    }
</style>