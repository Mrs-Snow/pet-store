<template>
    <search style="margin-top: 15px" @doSearch="keyValue"/>
    <guide style="margin-top: 15px" @showTab="showTab"/>
    <div class="content">
        <div class="left">
            <div class="kefu">
                <AlibabaOutlined/>
                客服
            </div>
        </div>
        <div class="center">
            <!-- <span v-if="showGoods" style="font-size: large; font-weight: 700; color: blueviolet;">搜索结果</span> -->
            <div v-if="showGoods" class="goodsList">
                <div v-for="(item,index) in goodsList" >
                <goodsItem :goodsName="item.goodsName" :goodsPic="item.goodsPic" :price="item.price" :storeName="item.storeName"/>
                </div>
            </div>
            
            <Carousel
                v-if="showCarousel"
                class="carousel"
                effect="fade"
                autoplay
                :dots=false
            >
                <img class="img" src="../../assets/cat1.jpeg" />
                <img class="img" src="../../assets/cat2.jpeg" />
                <img class="img" src="../../assets/cat3.jpg" />
            </Carousel>
        </div>
       
        <div class="right">
            广告位招租
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,Ref,ref } from 'vue'
import MyFooter from '../../components/layout/MyFooter.vue';
import MyHeader from '../../components/layout/MyHeader.vue';
import search from '../../components/store/content/search.vue';
import guide from '../../components/store/content/guide.vue';
import goodsItem from './goodsItem.vue';
import { AlibabaOutlined } from '@ant-design/icons-vue';
import { Carousel } from 'ant-design-vue';
import searchByKey from '@/api/goods';
import axios from 'axios';
import { url } from 'inspector';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search,
        guide,
        Carousel,
        AlibabaOutlined,
        goodsItem
    },
    setup () {
        const showCarousel=ref(true)
        const showGoods=ref(false)
        const searchKey = ref('')
        const goodsList = ref<Goods[]>([])

        const showTab = (val:Ref) =>{
            const proxy = val.value
            showGoods.value=true
            showCarousel.value=proxy.showCarousel
            doSearch(proxy.tabName)
        }

        function keyValue(key:Ref){
            showGoods.value=true
            showCarousel.value = false
            searchKey.value = key.value
            doSearch(key.value)
        }

        async function doSearch(key:String){
           const data =  await axios.post(
                 'http://127.0.0.1:17777/mrsnow/goods/searchByKey',
                 {
                    data: key
                 }
                )
            goodsList.value=data.data.data    
        }
        return {
            showCarousel,
            showGoods,
            showTab,
            searchKey,
            keyValue,
            doSearch,
            goodsList
        }
    }
})
</script>

<style scoped>
    .goodsList {
        margin-left: 10%;
        width: 80%;
        height: 700px;
    }
    .center{
        width: 80%;
        height: 100%;
    }
    .kefu{
        margin-left: 10%;
        margin-top: 50%;
        cursor: pointer;
        width: 70%;
        height: 25px;
        border: 1px solid rgb(189, 36, 176);
        background-color: rgb(207, 104, 178);
        border-radius: 9px;
        font-weight: 500;
        font-family:'Times New Roman', Times, serif;
        color: rgb(255, 255, 255);
    }
    .left {
        display: flex;
        width: 10%;
        background-color: rgb(242, 149, 189);
        height: 100%;
    }
    .right {
        width: 10%;
        background-color: rgb(242, 149, 189);
        height: 100%;
        float: right;
        font-weight: 600;
        font-size: large;
        color: blueviolet;
    }
    .carousel{
        cursor: pointer;
        width: 100%;
    }

    .img{
        width: 100%;
        height: 645px;
    }

    .content {
        display: flex;
        height: 80vh;
        width: 100%;
    }
</style>