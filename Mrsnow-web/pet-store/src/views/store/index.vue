<template>
    <search style="margin-top: 15px" @doSearch="keyValue" ref="searchRef"/>
    <guide style="margin-top: 15px" @showTab="showTab" ref="guideRef"/>
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
                <div>
                    <h3 style="color:darkorange; height: 3px;">🔍 搜 索 结 果</h3>
                    <div class="list_box">
                        <span style="margin-left: 80px;" v-for="goods in record.records">
                            <goodsItem  :id="goods.id" :goodsName="goods.goodsName" :goodsPic="goods.goodsPic"
                            :price="goods.price" :storeName="goods.storeName" :inventoryNum="goods.inventoryNum"/>
                        </span>
                    </div>
                    <Pagination v-model:current="current" :defaultPageSize="6" :total="record.total" show-less-items @change="changePage"/>        
                </div>
                
            </div>
            <div v-if="noInfo" style="color: darkorchid; font-size: xx-large; font-weight: 900; text-align: center;">
                <div style="margin-top:20% ;">😮‍💨没找到相关记录~</div>
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
import { Carousel, Pagination } from 'ant-design-vue';
import axios from 'axios';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search,
        guide,
        Carousel,
        AlibabaOutlined,
        goodsItem,
        Pagination
    },
    setup () {
        const showCarousel=ref(true)
        const showGoods=ref(false)
        const searchKey = ref('')
        const goodsList = ref<Goods[]>([])
        const record = ref();
        const guideRef = ref();
        const noInfo = ref(false);
        const current = ref(1);
        const searchKind = ref();
        const tabProxy = ref();
        const searchRef = ref();

        const showTab = (val:Ref) =>{
            tabProxy.value = val.value
            showGoods.value=true
            showCarousel.value=tabProxy.value.showCarousel
            doSearchByKind(tabProxy.value.tabName)
        }

        function changePage(page:number ,pagesize:number){
            current.value = page
            doSearchByKey(searchRef.value.searchKey)
        }

        // function getPagination(){
        //     let current=1;
        //     let pagesize=3;
        //     console.log()
        //     if(record.value){
        //         const pagination = {
        //         onChange: (page: number) => {
        //         console.log(page);
        //         },  
        //         pageSize: record.value.size,
        //         current: record.value.current,
        //         total: record.value.total,
        //         };
        //         return pagination
        //     }else{
        //         const pagination={
        //             onChange: (page: number) => {
        //             console.log(page);
        //         },  
        //             pageSize: pagesize,
        //             current: current,
        //         }
        //         return pagination;
        //     }

        // }

        

        function keyValue(key:Ref){
            showGoods.value=true
            showCarousel.value = false
            searchKey.value = key.value
            guideRef.value.allKinds()
            doSearchByKey(key.value)
        }

        async function doSearchByKey(key:String){
           const data =  await axios.post(
                 'http://127.0.0.1:17777/mrsnow/goods/searchGoods',
                 {
                    data: key,
                    current: current.value,
                    extra: tabProxy.value.tabName
                 }
            )
            record.value = data.data.data
            if(!data.data.data.records.length||data.data.data.records.length===0){
                noInfo.value=true
                showGoods.value=false
            }else{
                noInfo.value=false
            }  
            console.log(record) 
        }

        async function doSearchByKind(key:String){
           const data =  await axios.post(
                 'http://127.0.0.1:17777/mrsnow/goods/searchGoods',
                 {
                    data: key,
                    current: current.value,
                    extra: tabProxy.value.tabName
                 }
                )
            record.value = data.data.data
            if(!data.data.data.records.length||data.data.data.records.length===0){
                noInfo.value=true
                showGoods.value=false
            }else{
                noInfo.value=false
            }  
            console.log(record)  
        }
        return {
            showCarousel,
            showGoods,
            showTab,
            searchKey,
            keyValue,
            doSearchByKind,
            doSearchByKey,
            goodsList,
            record,
            guideRef,
            noInfo,
            current,
            changePage,
            searchRef
        }
    }
})
</script>

<style scoped>

    .list_box{
        display: flex;
        flex-wrap: wrap;
        padding-bottom: 10px;
    }
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