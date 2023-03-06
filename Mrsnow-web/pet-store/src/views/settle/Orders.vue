<template>
    <div class="main">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: cornflowerblue;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color:darkmagenta;">订单信息</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>

        <div class="content">
            <div v-for="item in record" class="order">
                    <div class="goods">
                        <a-image :width="100" :src="getImageUrl(item.goodsDetail.goodsPic)"/>
                        <span class="info1">{{ item.goodsDetail.goodsName }}</span>
                        <span class="info2">数量: {{ item.goodsNum }}</span>
                        <span class="info2" style="color: red;">单价: {{ item.goodsDetail.price }} ¥</span>
                        <span v-if="item.goodsDetail.preferential" class="info2" style="color: chocolate; margin-left: 60px;">{{ item.goodsDetail.preferential.comment }} </span>
                    </div>
                    <div v-if="item.address" class="address">
                        <span style=" margin-left: 300px; text-decoration: underline; color: blue; font-size: small; cursor: pointer;" @click="changeAddress">更换地址</span>
                        <div style="float: left; text-align: justify; height: 20px; color:crimson; font-weight: 700; margin-left: 5px;">收货信息</div>
                        <br/>
                        <div class="info3">收货人: {{item.address.consignee}}</div>
                        <div class="info3">手机号: {{item.address.consigneeTel}}</div>
                        <div class="info3">详细地址: {{item.address.deliveryAddress}}</div>
                    </div>
                    <div v-if="!item.address" class="address">
                        <h2 style="color: blueviolet;">您还没有设置收货地址😭</h2>
                        <a @click="changeAddress">去设置</a>
                    </div>
                    <div class="orderInfo">
                        <div class="info3" style="color:darkslategray; font-size: small;">原价: {{(item.goodsNum * item.goodsDetail.price).toFixed(2)}}</div>
                        <div class="info3" style="color:red;">需支付: {{item.amount}} 元</div>
                        <div class="info3" style="color:green; width: 320px;">{{item.remark}}</div>
                    </div>
                    <div class="buttons">
                        <Button style="background-color: salmon; color: white;" @click="cancelOrder">取消订单</Button>
                        <Button style="background-color: blueviolet; color: white; margin-left: 20px;" @click="pay(item)">立即支付</Button>
                    </div>
                    
                    
            </div>
        </div>
    </div>
    <div class="right">
            广告位招租
    </div>
    
</template>

<script lang="ts">
import { message } from 'ant-design-vue';
import { defineComponent,onBeforeMount,onMounted,ref } from 'vue'
import { useRoute, useRouter } from 'vue-router';
import request from '../../utils/request';

export default defineComponent({
    setup () {
        const route = useRoute()
        const router = useRouter()
        const record = ref()
        const address = ref()
        const userId = sessionStorage.getItem("userId")

        onBeforeMount(()=>{
            const ids= route.params.cartIds
            let array=ids.split(',');
            console.log(array)
            request.post('/order/buyFromCart',{data:array}).then(res=>{
                if(res.data.code===10){
                        message.info(res.data.message)
                        setTimeout(()=>{
                            location.replace('/')
                        },2000)
                    }
                    if(res.data.code===200){
                        record.value=res.data.data  
                    }
                
            })
        })
        function handleBack(){
            router.go(-1)
        }

        function getImageUrl(pic){
            return new URL('/src/assets/goods/'+pic,import.meta.url).href
        }
        
        function changeAddress(){
            router.push({
                path:'/personCenter',
                query: {
                  tabKey: '3'
                }
            })
        }

        function pay(order){
            request.post('/order/pay',{data:order}).then(res=>{
                message.info(res.data.message)
                record.value.splice(order,1)
            })
            if(record.value.length<1){
                router.go(-1)
            }
        }

        function cancelOrder(order){
            record.value.splice(order,1)
            message.success('取消订单成功')
            if(record.value.length<1){
                router.go(-1)
            }
        }
  

        return {
            handleBack,record,changeAddress,address,
            getImageUrl,cancelOrder,pay
        }
    }
})
</script>

<style scoped>
    .buttons{
        position: relative;
        text-align: justify;
        float: right;
        left: 300px;
        bottom: 35px;
        width: 300px;
        height: 50px;
    }
    .orderInfo{
        padding: 8px;
        text-align: justify;
        position: relative;
        float: right;
        right: 80px;
        bottom: 190px;
        width: 400px;
        height: 150px;
        background-color: rgba(118, 117, 113, 0.05);
    }
    .address{
        text-align: justify;
        padding: 10px;
        height: 170px;
        width: 500px;
        background-color: rgba(230, 200, 129, 0.1);
    }

    .right{
        position: fixed;
        width: 200px;
        background-color: rgb(242, 149, 189);
        height: 800px;
        float: right;
        font-weight: 600;
        font-size: large;
        color: blueviolet;
        left: 1320px;
        top: 50px;
    }
    .main{
        height: 850px;
        background-color: white;
    }
    .content{
        margin-left: 100px;
        height: 700px;
        width: 1200px;
        border: 12px solid rgba(146, 11, 169, 0.1);
        display: flexbox;
        border-radius: 12px;
        overflow-y: scroll;
    }
    .order{
        height: 300px;
        width: 1000px;
        margin-top: 20px;
        margin-left: 50px;
        border: 1px solid rgba(146, 11, 169, 0.1);
    }
    .goods{
        width: 100%;
        text-align: justify;
        /* border: 1px solid rgba(146, 11, 169, 0.1); */
    }


</style>