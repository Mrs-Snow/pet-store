<template>
    <div class="mycart">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: coral;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color: chocolate;">购物车</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>
        
        <div>
            
            <div v-if="hasCartInfo()" style="font-size: xxx-large; font-weight: 500; color: blueviolet; position: fixed; bottom: 500px; left: 550px;">🛒 购物车空空如也</div>
            <div class="cart">
                <Button style="float: right;" @click="reload()" size="large" shape="circle"><RedoOutlined/></Button>
                <div v-for="(item,index) in cartData.records" class="items">
                    <a-checkbox style="margin-top: 40px; margin-left: 20px;" @change="onSelect" :id="index">{{ index+1 }}</a-checkbox>
                    <div class="image"><Image  :src="getImageUrl(item.goodsDetail.goodsPic)" :width="80"/></div>
                    <div class="name">{{ item.goodsDetail.goodsName }}</div>
                    <div class="num">
                        <a-popconfirm
                        :visible="visible[index]"
                        title="是否要移除此商品?"
                        ok-text="是的"
                        cancel-text="还想买"
                        @confirm="confirmDec(index)"
                        @cancel="cancelDec(index)"
                        ><Button class="numButton1" @click="numDec(index)">-</Button></a-popconfirm>
                        <div>数量: {{ item.goodsNum }}</div>
                        <Button class="numButton2" @click="numInc(index)">+</Button>
                    </div>
                    <div class="price">{{ item.amountMoney }} 元</div>
                </div>
                <div>
                    <div style="font-size: large; color: blueviolet; text-align: justify; margin-left: 100px; margin-top: 5px;">已选择 {{ selectList.length }} 件商品</div>
                    <div style="font-size: large; color: red; text-align: justify; margin-left: 100px; margin-top: 5px; font-weight:600;" >合计:{{ countAll }}元</div>
                    <Button style="float: right; margin-right: 100px;"  type="primary" danger @click="goSettle">去 结 算</Button>
                </div>
                
                <Pagination :current="current" :total="cartData.total" :page-size="4" @change="pageChange" class="page"/>
            </div>
        </div>
        <div class="right">
            <h2 style="color:mediumorchid;">广告区</h2>
            <div style="width: 100px; border: 1px solid #000; margin-left: 50px; border-radius: 10px; color: aliceblue; margin-top: 300px;
            background-color: blueviolet; cursor: pointer;">联系客服</div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,onBeforeMount,onMounted,ref } from 'vue'
import { useRoute,useRouter } from 'vue-router';
import { Divider,message,Button,Pagination,Image } from 'ant-design-vue';
import { RedoOutlined } from '@ant-design/icons-vue';
import request from '../../utils/request';
export default defineComponent({
    components:{
        Divider,Button,Pagination,RedoOutlined,Image
    },
    setup () {
        const visible =ref([])
        const countAll=ref(0)
        let selectList = ref([])
        const route = useRoute()
        const current =ref(1)
        const router = useRouter()
        const cartData = ref()
        onBeforeMount(()=>{
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/cart/myCart',{data: userId,current:1,pageSize:5}).then(res=>{
                    cartData.value = res.data.data
                    for(var i=0;i<cartData.value.records.length;i++){
                        visible.value.push(false)
                    }
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

        function numInc(index){
            const max =  cartData.value.records[index].goodsDetail.inventoryNum
            if(cartData.value.records[index].goodsNum<max){
                cartData.value.records[index].goodsNum+=1
                request.post('/cart/edit',{data:cartData.value.records[index]}).then(res=>{
                    reload()
                })
            }
        }

        

        function numDec(index){
            if(cartData.value.records[index].goodsNum>0){
                if(cartData.value.records[index].goodsNum - 1===0){
                    visible.value[index]=true
                    return;
                }
                cartData.value.records[index].goodsNum-=1
                request.post('/cart/edit',{data:cartData.value.records[index]}).then(res=>{
                    reload()
                })
                
            }
        }
        
        function reload(){
            selectList.value=[]
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/cart/myCart',{data: userId,current:1,pageSize:5}).then(res=>{
                    cartData.value = res.data.data
                    for(var i=0;i<cartData.value.records.length;i++){
                        visible.value.push(false)
                    }     
            })
            }
        }

        function getImageUrl(pic){
            return new URL('/src/assets/goods/'+pic,import.meta.url).href
        }

        function confirmDec(index){
            cartData.value.records[index].goodsNum -= 1
            if(cartData.value.records[index].goodsNum===0){
                    request.post('/cart/remove',{data:cartData.value.records[index].id}).then(res=>{
                    reload()
                })
                }
            visible.value[index]=false
        }

        function cancelDec(index){
            visible.value[index]=false
        }

        function onSelect(e){
           const goods = cartData.value.records[e.target.id]
           if(e.target.checked){
                selectList.value.push(goods)
           }else{
                const index = selectList.value.indexOf(goods)
                selectList.value.splice(index,1)
           }
           console.log(selectList.value)
           countAll.value=0
           selectList.value.map(g=>{
             countAll.value+=g.amountMoney

           })
        }

        function pageChange(page,pageSize){
            current.value=page
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/cart/myCart',{data: userId,current:page,pageSize:5}).then(res=>{
                    cartData.value = res.data.data
                    for(var i=0;i<cartData.value.records.length;i++){
                        visible.value.push(false)
                    }     
            })
            }
        }

        function goSettle(){
            if(selectList.value.length===0){
                message.error('请选择至少一件商品!')
            }
            // router.push('/settle')
        }

        function hasCartInfo(){
            if(cartData.value){
                return false
            }
            return true
        }
        return {selectList,hasCartInfo,handleBack,cartData,reload,current,getImageUrl,onSelect,numInc, numDec,visible,confirmDec,cancelDec,pageChange,countAll,goSettle}
    }
})
</script>

<style scoped>
    .mycart{
        height: 800px;
    }

    .cart{
        display: flexbox;
        margin-left: 60px;
        width: 1200px;
        height: 600px;
        border: 3px solid rgba(221, 16, 224, 0.3);
    }
    .items{
        width: 1000px;
        height: 100px;
        border: 3px solid rgba(234, 114, 9, 0.3);
        margin-left: 100px;
        margin-top: 20px;
        display: flex;
        border-radius: 10px;
    }
    .page{
    }

    .image{
        margin-top: 8px;
        margin-left: 30px;
        float: left;
    }

    .name{
        font-size: large;
        font-weight: 500;
        width: 140px;
        color: rgb(224, 53, 14);
        height: 50px;
        margin-top: 36px;
        text-align: justify;
        margin-left: 20px;
    }

    .price{
        font-size: large;
        font-weight: 500;
        width: 140px;
        color: rgb(250, 4, 4);
        height: 50px;
        margin-top: 26px;
        text-align: justify;
        margin-left: 200px;
        float: right;
    }

    .num{

        font-weight: 500;
        width: 180px;
        color: rgb(102, 101, 101);
        height: 50px;
        margin-top: 36px;
        text-align: justify;
        margin-left: 120px;
    }

    .numButton1{
        margin-top: -6px;
        margin-right: 10px;

        float: left;
    }
    .numButton2{
        margin-top: -28px;
        margin-right: 20px;
        float: right;
    }
    .right{
        position: fixed;
        height: 750px;
        background-color: rgb(151, 210, 236);
        width: 210px;
        top: 60px;
        left: 1310px;
    }
</style>