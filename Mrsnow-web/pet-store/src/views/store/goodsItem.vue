<template>
    <div class="box">
        <div class="goodsImage">
            <a-image
                :width="215"
                :src="getImageUrl(goodsPic)"
            />
        </div>
        <div style="cursor: pointer;" @click="goodsDetail()">
            <div class="price">
                <span style="color: blueviolet; float: left; margin-left: 5px;">{{ goodsName }}</span>
            {{ price }}¥
            </div>
             <div class="inventoryNum">
                <span style="margin-left: 5px;">库存: {{ inventoryNum }} 件</span>
                <span style="width: 50%; color: gray; margin-left: 35px;">
                月销量: 999 +
                </span>
            </div>
            <div class="sales">
                <span class="store">
                    <div>🏠: {{ storeName }}</div>
                </span>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { number, string } from 'vue-types';
import { Image } from 'ant-design-vue';
import { useRouter } from 'vue-router';

export default defineComponent({
    name: 'goodsItem',
    components:{
        Image
    },
    props:{
        id:{
            type: string,
            default: ''
        },
        goodsName:{
            type: string,
            defalt: '商品'
        },
        goodsPic:{
            type: string,
            defalt: 'food2.jpg'
        },
        price:{
            type: string,
            defalt: '0.00'
        },
        storeName:{
            type: string,
            defalt: ''
        },
        inventoryNum:{
            type: number,
            defalt: 99
        }
    },
    setup (props) {
        const router = useRouter()
        function getImageUrl(name:any){
            return new URL('/src/assets/goods/'+name,import.meta.url).href
        }

        function goodsDetail(){
            const id = props.id.toString()
            router.push({
                path:'/goodsDetail',
                query: {id: id}
            })
            console.log('搜索id',props.id)
        }

        return {getImageUrl,goodsDetail}
    }
})
</script>

<style scoped>
    .box {
        margin-top: 17px;
        width: 215px;
        height: 300px;
        background-color: rgb(249, 251, 252);
        display: 'flexbox';
        border: 1px solid #2a272a;
    }

    .goodsImage{
        width: 100%;
        height: 215px;
        /* border: 1px solid #10130f; */
    }

    .inventoryNum{
        text-align: justify;
        font-weight: 800;
        color: rgb(222, 97, 170);
        width: 100%;
        margin-top: 5px;
    }
    
    .price{
        margin-top: 2px;
        font-size: large;
        height: 28px;
        text-shadow: #ba11a3;
        font-weight: 800;
        color: rgb(243, 35, 49);
    }

    .store{
        font-weight: 400;
        color: #20739d;
        cursor: pointer;
        height: 20px;
        width: 100%;
        text-align: center;
        text-decoration:underline
    }
    .sales{
        margin-top: 6px;
        color: rgb(137, 128, 128);
        width: 100%;
        text-align: justify;
    }

    .describe{
        border: 1px solid #5be11d;
        font-size: small;
        color: rgb(212, 21, 142);
        height: 100%;
        
    }
</style>