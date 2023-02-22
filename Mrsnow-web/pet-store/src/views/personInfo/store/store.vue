<template>
    <div class="left_menu">
        <Tabs tab-position="left">
            <TabPane key="1" tab="商店管理">
                <BaseForm ref="form"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="2" tab="优惠活动">
                <Preferential ref="preferentialRef"/>
            </TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="3" tab="商品管理"></TabPane>
            <TabPane :forceRender="true" :disabled="hasStore" key="4" tab="订单中心"></TabPane>
        </Tabs>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { message,TabPane,Tabs } from 'ant-design-vue';
import request from '../../../utils/request';
import BaseForm from './BaseForm.vue';
import Preferential from './Preferential.vue';
export default defineComponent({
    name: 'Store',

    components:{
        TabPane,
        Tabs,
        BaseForm,
        Preferential
    },
    setup () {
        const hasStore = ref(true)
        const store = ref()
        const form = ref()
        const preferentialRef = ref()
        onMounted(()=>{
            const userId = sessionStorage.getItem('userId')
            request.post('/store/storeInfo',{data:userId}).then(res=>{
                store.value = res.data.data
                if(store.value.id){
                    hasStore.value=false
                    form.value.load(store.value)
                    preferentialRef.value.load(store.value)
                    sessionStorage.setItem('storeId',store.value.id)
                }else{
                    form.value.load(store.value)
                }
            })
           
        })

        return {hasStore,form,preferentialRef}
    }
})
</script>

<style scoped>
    .left_menu{
        height: 500px;
    }
</style>