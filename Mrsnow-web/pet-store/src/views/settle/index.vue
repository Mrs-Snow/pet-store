<template>
    <div class="main">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: cornflowerblue;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color:darkmagenta;">订单信息</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>

        <div class="content">
            <!--box1 收获信息-->
            <div class="box1">
                <span style="float: left; text-align: justify; margin-left: 20px; height: 20px; color: firebrick; width: 100px; font-weight: 700;">收货信息</span>
                <span v-if="address" style=" margin-left: 200px; text-decoration: underline; color: blue; font-size: small; cursor: pointer;" @click="changeAddress">更换地址</span>
                <div v-if="address" class="box2">
                    <div class="info1">收货人: {{address.consignee}}</div>
                    <div class="info1">手机号: {{address.consigneeTel}}</div>
                    <div class="info1">详细地址: {{address.deliveryAddress}}</div>
                </div>
                <div v-if="!address" class="box2">
                    <h2 style="color: blueviolet;">您还没有设置收货地址😭</h2>
                    <a @click="changeAddress">去设置</a>
                </div>
                <span style="color: gray; font-weight: 500;" >请仔细核对您的收获信息</span>
            </div>
            <!--box3,商品信息-->
            <div class="box3">
                <span style="float: left; text-align: center; margin-left: 20px; height: 20px; color:blueviolet; width: 100%; font-weight: 700; font-size: x-large;">商品信息</span>
                <div>
                    <div style="float: left; margin-left: 20px; width: 97%; text-align: justify;
                        margin-top: 30px;">
                        <a-image :width="120" :src="getImageUrl(record.goodsDetail.goodsPic)"/>
                        <span style="margin-left: 20px; color:brown; font-size: large;">{{ record.goodsDetail.goodsName }}</span>
                        <span style="margin-left: 20px; color:gray;">X {{ record.goodsDetail.num }}</span>
                        <span style="margin-left: 20px; color:red;">单价: {{ record.goodsDetail.price }}元</span>
                    </div>
                    <div>
                        <div class="info2">商家:<span style="margin-left: 10px; color: blue;">{{record.goodsDetail.storeName}}</span> </div>
                        <div class="info2">优惠信息:<span style="margin-left: 10px; color:green;">{{record.remark}}</span> </div>
                        <div class="info2">总金额:<span style="margin-left: 10px; color: red; font-weight: 600;">{{record.amount}}元</span> </div>
                    </div>
                </div>
            </div>
            <!--button区-->
            <div class="buttons">
                <a-button style="background-color: blueviolet; color: white;" size="large" @click="pay">立即支付</a-button>
            </div>
            <!--商家发货信息区-->
            <div class="box4">
                <div>
                    <div class="info2">发货人:<span style="margin-left: 10px; color:blueviolet;">{{record.shipAddress.consigner}}</span> </div>
                    <div class="info2">联系方式:<span style="margin-left: 10px; color:blue;">{{record.shipAddress.consignerPhone}}</span> </div>
                    <div class="info2">发货地址:<span style="margin-left: 10px; color: grey; font-weight: 600;">{{record.shipAddress.consignerAddress}}元</span> </div>
                </div>
            </div>
    </div>
    <div class="right">
            广告位招租
        </div>
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
            request.post('/address/getDefault',{data:userId}).then(res=>{
                address.value=res.data.data
                request.post('/order/buyFromDetail',{data:{
                    userId:userId,
                    goodsId:goodsId,
                    addressId:address?.value.id,
                    num: num
                }}).then(res=>{
                    console.log(res.data.data)
                    record.value=res.data.data          
                })
            })
            const goodsId = route.query.goodsId
            const num = route.query.num
            
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

        function pay(){
            request.post('/order/pay',{data:record.value}).then(res=>{
                message.success(res.data.message)
                router.replace({
                    path:'/'
                })
            })
            
        }

        return {
            handleBack,record,changeAddress,address,
            getImageUrl,pay
        }
    }
})
</script>

<style scoped>

    .buttons{
        float: right;
        width: 200px;
        margin-top: 80px;
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
    }

    .box1{
        padding-top: 2px;
        padding-left: 3px;
        margin-top: 10px;
        margin-left: 30px;
        width: 500px;
        height: 200px;
        background-color: rgba(253, 136, 18, 0.15);
        border-radius: 10px;
    }

    .box4{
        padding-left: 6px;
        padding-top: 5px;
        margin-left: 20px;
        height: 120px;
        width: 800px;
        background-color: rgb(242, 149, 189,0.1);
        border-radius: 12px;
    }

    .box2{
        margin-top: 10px;
        height: 140px;
        width: 500px;
    }

    .info1{
        font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
        text-align: justify;
        margin-top: 12px;
        color: darkblue;
        margin-left: 20px;
    }

    .info2{
        font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
        text-align: justify;
        margin-top: 12px;
        color: rgb(24, 2, 28);
        margin-left: 20px;
        font-weight: 400;
    }

    .box3{
        height: 300px;
        width: 900px;
        margin-left: 30px;
        margin-top: 20px;
        background-color: rgb(248, 250, 251);
        border-radius: 12px;
    }
</style>