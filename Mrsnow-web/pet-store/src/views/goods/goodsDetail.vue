<template>
    <div class="mycart">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: coral;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color: chocolate;">商品详情</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>
        <div class="store">
            <h1 v-if="goodsData" style="color: blueviolet; cursor: pointer;" @click="goStore">欢迎光临 {{ goodsData.storeName }} </h1>
        </div>
        <div class="right">
            <h2 style="color:mediumorchid;">商家广告区</h2>
            <div style="width: 100px; border: 1px solid #000; margin-left: 100px; border-radius: 10px; color: aliceblue; 
            background-color: blueviolet; cursor: pointer;" @click="kefu">联系客服</div>
        </div>
        <div class="detail">
            <div class="detail_img">
                <Image v-if="goodsData" :src="getImageUrl()"/>
            </div>
            <div class="goods_title">
                <h2 v-if="goodsData" style="color: coral;">{{ goodsData.goodsName }}</h2>
            </div>
            <div class="price">
                <div class="price_title1">原价:
                    <span style="text-decoration:line-through; margin-left: 10px; font-size: small;">{{ goodsData.price }} 元</span>
                </div>
                <div class="price_title2">特惠价:
                    <span style=" margin-left: 10px; font-size: x-large; color: red;">{{ price }} 元</span>
                    <span v-if="goodsData.preferential" style=" color:deeppink; position: fixed; top:360px; left: 620px;">-{{ goodsData.preferential.preferentialPrice }} 元</span>
                    <span v-if="discount!==100" style=" color:blueviolet; position: fixed; top:334px; left: 710px;">{{ discount }} 折</span>
                </div>
                <div class="price_title3">月销量: 999+</div>
                <div class="price_title4">💥 火 爆 销 售 💥</div>
                <div v-if="goodsData.preferential" class="price_title5">活动:
                    <span style="color: cadetblue; margin-left: 6px;">{{ goodsData.preferential.comment }}</span>
                </div>
            </div>
            <div class="account">
                <span style="margin-top: 6px; color: gray; font-weight: 500;">数量 :</span>
                 <a-input-number style="margin-left: 6px; font-weight: 800;"
                 :min="1" :max="goodsData.inventoryNum"
                 v-model:value="num"
                 ></a-input-number>
                 <span style="margin-top: 6px; color: gray; font-weight: 500; margin-left: 6px;">件</span>
                 <span v-if="isMax()" style="margin-top: 6px; color:blueviolet; font-weight: 500; margin-left: 6px;">库存就这么多啦!!😑</span>
            </div>
            <div class="buttons">
                <span class="button1" @click="buy">立即购买</span>
                <span class="button2" @click="addCart">加入购物车</span>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,onBeforeMount,ref,onMounted } from 'vue'
import { useRoute,useRouter } from 'vue-router';
import { Divider,message } from 'ant-design-vue';
import request from '../../utils/request';
import { Image,Input } from 'ant-design-vue';
export default defineComponent({
    components:{
        Divider,
        Image,
        Input
    },
    setup () {
        const route = useRoute()
        const router = useRouter()
        const goodsData = ref()
        const num = ref<number>(1);
        const price = ref<any>(0);
        const discount = ref(100);

        onBeforeMount(async ()=>{
            const goodsId = route.query.id
            console.log(route.query)
            goodsData.value = (await request.post('/goods/goodsDetail',{data:goodsId})).data.data
            const preferential = goodsData.value.preferential
            price.value = goodsData.value.price
            if(preferential){
                discount.value = preferential.discount
                if(preferential.discount<100){
                    price.value = (goodsData.value.price * preferential.discount).toFixed(2)
                }
                if(preferential.preferentialPrice>0){
                    price.value = (goodsData.value.price - preferential.preferentialPrice).toFixed(2)
                }
            }else{return;}
        })

        function getImageUrl(){
            return new URL('/src/assets/goods/'+goodsData.value.goodsPic,import.meta.url).href
        }

        function isMax(){
            if(num.value>=goodsData.value.inventoryNum){
                return true
            }
            return false
        }

        function buy(){
            
        }

        function kefu(){
            message.info('暂未开放😮‍💨')
        }

        function goStore(){
            router.push({
                path: '/store',
                query: goodsData.value.storeId
            })
        }

        const handleBack =()=>{
            router.push({
                path: '/'
            })
        }

        function addCart(){
            message.success('已添加到购物车!')
        }

        
        return {getImageUrl,handleBack,goodsData,num,isMax,price,buy,addCart,goStore,kefu,discount}
    }
})
</script>

<style scoped>
    .right{
        position: fixed;
        height: 500px;
        background-color: rgb(138, 203, 233);
        width: 310px;
        left: 1210px;
    }
    .store{
        height: 80px;
    }
    .account{
        display: flex;
        height: 30px;
        width: 600px;
        position: fixed;
        left: 600px;
        top: 480px;
    }
    .info{
        height: 30px;
        width: 600px;
        position: fixed;
        left: 600px;
        top: 445px;
        border: 1px solid #000;
    }
    .mycart{
        height: 740px;
    }
    .detail{
        height: 500px;
        width: 1100px;
        margin-left: 10%;
        border: 1px solid rgba(200,200,200,0.35);
        display: flexbox;
    }
    .detail_img{
        margin-top: 20px;
        margin-left: 30px;
        width: 350px;
    }
    .button1{
        height: 32px;
        width: 160px;
        border: 1px solid #000;
        font-weight: 600; font-size:large;
        background-color:rgb(242, 69, 21);
        color: rgb(255, 255, 255);
        text-align: center;
        transition: all 0.1s;
        cursor: pointer;
    }

    .button1:hover{
        transform: matrix(1.2,0,0,1.2,0,3);
    }

    .button2{
        margin-left: 30px;
        height: 32px;
        width: 160px;
        border: 1px solid #000;
        font-weight: 600; font-size:large;
        background-color:rgb(223, 24, 216);
        color: rgb(255, 255, 255);
        text-align: center;
        transition: all 0.1s;
        cursor: pointer;
    }

    .button2:hover{
        transform: matrix(1.2,0,0,1.2,0,3);
    }

    .goods_title{
        height: 30px;
        width: 600px;
        position: fixed;
        left: 600px;
        top: 285px;
    }

    .price{
        background-color: rgb(249, 243, 227);
        height: 100px;
        width: 600px;
        position: fixed;
        left: 600px;
        top: 330px;
    }

    .price_title1{
        font-weight: 600;
        position: fixed;
        left: 610px;
        top: 336px;
        color: rgb(141, 140, 137);
        font-size: x-small;
    }
    .price_title2{
        font-weight: 600;
        position: fixed;
        left: 610px;
        top: 380px;
        color: rgb(141, 140, 137);
    }

    .price_title3{
        font-weight: 600;
        position: fixed;
        left: 1110px;
        top: 330px;
        color: rgb(141, 140, 137);
    }

    .price_title4{
        font-weight: 600;
        position: fixed;
        left: 1000px;
        top: 390px;
        color: rgb(244, 243, 239);
        background-color: rgb(239, 54, 91);
        border-radius: 10px;
    }

    .price_title5{
        font-weight: 600;
        position: fixed;
        left: 790px;
        top: 330px;
        color: rgb(218, 39, 33);
    }

    .buttons{
        height: 50px;
        width: 450px;
        position: fixed;
        left: 600px;
        top: 550px;
        display: flex;
    }
</style>