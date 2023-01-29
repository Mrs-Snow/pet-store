<template>
    <search style="margin-top: 15px" @doSearch="searchByKey"/>
    <guide style="margin-top: 15px" @showTab="showTab"/>
    <div class="content">
        <div class="left">
            <div class="kefu">
                <AlibabaOutlined/>
                客服
            </div>
        </div>
        <div class="center">
        




            
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
import { AlibabaOutlined } from '@ant-design/icons-vue';
import { Carousel } from 'ant-design-vue';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search,
        guide,
        Carousel,
        AlibabaOutlined
    },
    setup () {
        const showCarousel=ref(true)

        const showTab = (val:Ref) =>{
            const proxy = val.value
            showCarousel.value=proxy.showCarousel
        }

        function searchByKey(key:Ref){

        }

        function doSearch(){

        }
        return {
            showCarousel,
            showTab,
            searchByKey,
            doSearch
        }
    }
})
</script>

<style scoped>
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
        border: 1px solid #000;
        height: 80vh;
        width: 100%;
    }
</style>