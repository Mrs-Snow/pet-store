<template>
    <search style="margin-top: 15px"/>
    <guide style="margin-top: 15px"/>
    
    <div class="content">
        <div class="left">
            <div class="kefu">
                <AlibabaOutlined/>
                客服
            </div>
        </div>
        <Carousel
        class="carousel"
        effect="fade"
        autoplay
        :dots=false
        >
            <img class="img" src="../../assets/cat1.jpeg" />
            <img class="img" src="../../assets/cat2.jpeg" />
            <img class="img" src="../../assets/cat3.jpg" />
        </Carousel>
        <div class="right"> sadsadsa</div>
    </div>
    
    
</template>

<script lang="ts">
import { defineComponent } from 'vue'
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
        

        return {}
    }
})
</script>

<style scoped>
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
    }
    .carousel{
        cursor: pointer;
        width: 80%;
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