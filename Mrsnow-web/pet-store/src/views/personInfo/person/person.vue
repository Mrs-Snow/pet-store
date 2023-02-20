<template>
    <div class="left_menu">
        <Tabs tab-position="left">
            <TabPane key="1" tab="基本信息">
                <BaseForm  ref="form"/>
            </TabPane>
            <TabPane key="2" tab="密码修改">
                <Password/>
            </TabPane>
            <TabPane key="3" tab="收获地址">
                <Password/>
            </TabPane>
            <TabPane key="4" tab="我的订单">
                <Password/>
            </TabPane>
        </Tabs>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { message,TabPane,Tabs } from 'ant-design-vue';
import BaseForm from './BaseForm.vue';
import Password from './Password.vue';
import request from '../../../utils/request';
import { useRoute,useRouter } from 'vue-router';
export default defineComponent({
    name: 'Person',

    components:{
        TabPane,
        Tabs,
        BaseForm,
        Password
    },
    setup () {
        const form = ref();
        const router = useRouter()
        onMounted(()=>{
            const userId = sessionStorage.getItem('userId')
            if (userId){
                request.post('/user/myself',{data: userId}).then(res=>{
                    form.value.load(res.data.data)
            })
                
            } else{
                message.info('请登录!')
                router.push({
                    path: '/login'
                })
            }
        })

        return {form}
    }
})
</script>

<style scoped>
    .left_menu{
        height: 500px;
    }
</style>