<template>
    <div class="left_menu">
        <Tabs tab-position="left" @change="changeTab">
            <TabPane key="1" tab="商店管理" >
                <BaseForm ref="form"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="2" tab="优惠活动">
                <Preferential ref="preferentialRef"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="3" tab="商品管理">
                <Goods ref="goodsRef"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="4" tab="订单中心">
                <Orders ref="ordersRef"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="5" tab="发货信息">
                <ShipAddress ref="shipAddressRef"/>
            </TabPane>
        </Tabs>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { message,TabPane,Tabs } from 'ant-design-vue';
import request from '../../../utils/request';
import BaseForm from './BaseForm.vue';
import Preferential from './Preferential.vue';
import ShipAddress from './ShipAddress.vue';
import Goods from './Goods.vue';
import Orders from './Orders.vue';
export default defineComponent({
    name: 'Store',

    components:{
        TabPane,
        Tabs,
        BaseForm,
        Preferential,
        ShipAddress,
        Goods,
        Orders
    },
    setup () {
        const ordersRef=ref()
        const goodsRef=ref()
        const hasStore = ref(true)
        const store = ref()
        const form = ref()
        const shipAddressRef=ref()
        const preferentialRef = ref()

        const changeTab=(e)=>{
            console.log(e)
            if(e==='1'){
                form.value.reload()
            }
            if(e==='2'){
                preferentialRef.value.reload()
            }
            if(e==='3'){
                goodsRef.value.reload()
            }
            if(e==='4'){
                ordersRef.value.reload()
            }
            if(e==='5'){
                shipAddressRef.value.reload()
            }
        }

        onMounted(()=>{
            const userId = sessionStorage.getItem('userId')
            if(userId){
                request.post('/store/storeInfo',{data:userId}).then(res=>{
                store.value = res.data.data
                if(store.value.id){
                    hasStore.value=false
                    form.value.load(store.value)
                    preferentialRef.value.load(store.value)
                    goodsRef.value.load(store.value.id)
                    ordersRef.value.load(store.value)
                    sessionStorage.setItem('storeId',store.value.id)
                    request.post('/shipAddress/getInfo',{data:store.value.id}).then(sa=>{
                        shipAddressRef.value.load(sa.data.data)
                    })
                }else{
                    form.value.load(store.value)
                }
            })
            }
            
           
        })

        return {hasStore,form,preferentialRef,shipAddressRef,goodsRef,ordersRef,changeTab}
    }
})
</script>

<style scoped>
    .left_menu{
        height: 500px;
    }
</style>