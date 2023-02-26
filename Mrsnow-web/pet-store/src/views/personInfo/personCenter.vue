<template>
    <div class="mycart">
        <div style="width: 1200px;">
            <div style="text-decoration:underline; cursor: pointer; font-size: x-large; text-align: justify; margin-left: 50px; color:hotpink;" @click="handleBack">返回🔙</div>
            <h1 style="text-align: justify; margin-left: 130px; margin-top: 10px; color:hotpink;">🐈 用户中心</h1>
        </div>
        
        <div class="tabs">
            <Tabs
            :activeKey="activeKey" 
            :tabBarGutter="50"
            size="large"
            centered 
            @change="changeTab"
            style="width: 1000px; margin-left: 120px;" >
                <TabPane forceRender key="1" tab="个人中心">
                    <Person ref="person"/>
                </TabPane>
                <TabPane forceRender key="2" tab="店家中心">
                    <Store/>
                </TabPane>
            </Tabs>
        </div>
    </div>
    <div class="right">
            广告位招租
        </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue'
import { useRoute,useRouter } from 'vue-router';
import { Divider,message,TabPane,Tabs } from 'ant-design-vue';
import Person from './person/person.vue';
import Store from './store/store.vue';
import request from '../../utils/request';
export default defineComponent({
    components:{
        Divider,
        Tabs,
        TabPane,
        Person,
        Store
    },
    setup () {
        const person=ref()
        const route = useRoute()
        const router = useRouter()
        const userData =ref()
        const activeKey = ref("1");
        onMounted(()=>{
            console.log(route.query)
            if(route.query.tabKey){
                person.value.activeKey=route.query.tabKey
            }
        })

        const handleBack =()=>{
            router.go(-1)
        }

        const changeTab=(e)=>{
            activeKey.value=e
        }
       
        return {handleBack,userData,activeKey,person,changeTab}
    }
})
</script>

<style scoped>
    .right {
        position: fixed;
        width: 200px;
        background-color: rgb(242, 149, 189);
        height: 700px;
        float: right;
        font-weight: 600;
        font-size: large;
        color: blueviolet;
        left: 1320px;
        top: 100px;
    }
    .tabs{
        border: 3px solid rgba(243, 27, 157, 0.3);
        border-radius: 20px;
        width: 1200px;
        margin-left: 30px;
    }
    .mycart{
        height: 800px;
    }
</style>