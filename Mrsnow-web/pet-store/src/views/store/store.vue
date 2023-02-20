<template>
     <div class="mycart">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color: coral;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 50px; margin-top: 10px; color: chocolate;">{{}}</h1>
            <a-divider style="margin-left: 150px; margin-top: -10px;"></a-divider>
        </div>
        
        <div>
            
            <div style="width: 1000px; margin-left:220px; height: 750px;">
                    <h2 style="color:darkorange; height: 3px;">本 店 商 品</h2>
                    <div class="list_box">
                        <span style="margin-left: 80px;" v-for="goods in record.records">
                            <goodsItem  :id="goods.id" :goodsName="goods.goodsName" :goodsPic="goods.goodsPic"
                            :price="goods.price" :storeName="goods.storeName" :inventoryNum="goods.inventoryNum"/>
                        </span>
                    </div>
                    <Pagination v-model:current="current" :defaultPageSize="6" :total="record.total" show-less-items @change="changePage"/>        
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { useRoute,useRouter } from 'vue-router';
import { Pagination } from 'ant-design-vue';
import goodsItem from './goodsItem.vue';
import request from '../../utils/request';
export default defineComponent({
    components:{
        Pagination,
        goodsItem
    },
    setup () {
        const record = ref()
        const route = useRoute()
        const router = useRouter()
        const storeData = ref()
        const current = ref(1)
        const handleBack =()=>{
            router.go(-1)
        }
        onMounted(()=>{
            getStore()
            
        })
        async function getStore(){
            request.post('/goods/getStore',{
                data: route.query.storeId
            }).then(res=>{
                record.value=res.data.data
            })
        }
        function changePage(page:number ,pagesize:number){
            current.value = page
            getStore()
        }

        return {handleBack,current,record,changePage}
    }
})
</script>

<style scoped>
    .list_box{
        margin-top: 20px;
        display: flex;
        flex-wrap: wrap;
        padding-bottom: 10px;
    }
</style>