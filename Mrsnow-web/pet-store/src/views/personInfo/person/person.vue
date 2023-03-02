<template>
    <div class="left_menu">
        <Tabs tab-position="left" :activeKey="activeKey" @change="changeTab">
            <TabPane key="1" tab="基本信息">
                <BaseForm  ref="form"/>
            </TabPane>
            <TabPane :forceRender="true" key="2" tab="密码修改">
                <Password ref="passwordRef"/>
            </TabPane>
            <TabPane :forceRender="true" key="3" tab="收获地址">
                <Address ref="addressRef"></Address>
            </TabPane>
            <TabPane :forceRender="true" key="4" tab="我的订单">
                <Orders ref="ordersRef"/>
            </TabPane>
        </Tabs>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { message,TabPane,Tabs } from 'ant-design-vue';
import BaseForm from './BaseForm.vue';
import Password from './Password.vue';
import Address from './Address.vue';
import Orders from './Orders.vue';
import request from '../../../utils/request';
import { useRoute,useRouter } from 'vue-router';
export default defineComponent({
    name: 'Person',

    components:{
        TabPane,
        Tabs,
        BaseForm,
        Password,
        Address,
        Orders
    },
    setup () {
        const ordersRef=ref()
        const passwordRef=ref()
        const addressRef=ref()
        const form = ref();
        const router = useRouter()
        const route =useRoute()
        const activeKey =ref("1")

        const changeTab=(e)=>{
            activeKey.value=e
        }
        onMounted(()=>{
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/user/myself',{data: userId}).then(res=>{
                    form.value.load(res.data.data)
                    addressRef.value.load(res.data.data)
                    ordersRef.value.load(res.data.data)
            })
                
            } else{
                message.info('请登录!')
                router.push({
                    path: '/login'
                })
            }
        })

        function setTabKey(key){
            console.log(key)
            activeKey.value=key;
        }

        return {form,addressRef,activeKey,setTabKey,changeTab,passwordRef,ordersRef}
    }
})
</script>

<style scoped>
    .left_menu{
        height: 500px;
    }
</style>