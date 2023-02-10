<template>
    <div class="box">
        <span class="goodsImage">
            <Image width="20" :src=goodsPic />
        </span>
        <span class="goodsName">{{ goodsName }}</span>
        <span class="store">店铺: {{ storeName }}</span>
        <span class="sales">月销量: 999 +</span>
        <span class="price">{{ price }}¥</span>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { number, string } from 'vue-types';
import { Image } from 'ant-design-vue';
import { describe } from 'node:test';
export default defineComponent({
    name: 'goodsItem',
    components:{
        Image
    },
    props:{
        goodsName:{
            type: string,
            defalt: '商品'
        },
        goodsPic:{
            type: string,
            defalt: '../../assets/goods/food1.webp'
        },
        price:{
            type: string,
            defalt: '0.00'
        },
        storeName:{
            type: string,
            defalt: ''
        }
    },
    setup (props) {
        

        return {}
    }
})
</script>

<style scoped>
    .box {
        margin-top: 17px;
        width: 100%px;
        height: 100px;
        border: 1px solid #ba11a3;
        background-color: rgb(250, 252, 252);
        display: flex;
    }

     .goodsImage{
        width: 11%;
        height: 100%;
        border: 1px solid #000;
    }

    .goodsName{
        text-align: justify;
        margin-top: 35px;
        margin-left: 20px;
        font-size: larger;
        font-weight: 800;
        color: rgb(222, 97, 170);
        width: 30%;
        height: 100%;
    }
    
    .price{
        height: 100%;
        text-shadow: #ba11a3;
        margin-top: 40px;
        margin-left: 300px;
        font-size: larger;
        font-weight: 800;
        color: rgb(243, 35, 49);
    }

    .store{
        font-size: medium;
        font-weight: 400;
        color: #20739d;
        margin-top: 35px;
        margin-left: -100px;
        cursor: pointer;
        height: 100%;
    }
    .sales{
        margin-left: 100px;
        margin-top: 80px;
        font-size: small;
        color: rgb(137, 128, 128);
        font-size: small;
    }

    .describe{
        margin-left: 100px;
        margin-top: 80px;
        font-size: small;
        color: rgb(212, 21, 142);
        height: 100%;
        
    }
</style>