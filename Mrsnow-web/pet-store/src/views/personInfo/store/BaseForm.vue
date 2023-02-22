<template>
    <div>
       <div v-if="hasStore" style="margin-top: 50px;">
            <a-empty
            image="https://gw.alipayobjects.com/mdn/miniapp_social/afts/img/A*pevERLJC9v0AAAAAAAAAAABjAQAAAQ/original"
            :image-style="{
            height: '60px',
            }"
            >
            <template #description>
                <span>
                    您还未拥有任何店铺
                </span>
            </template>
                <a-button type="primary" @click="handleApply">现在申请</a-button>
            </a-empty>
       </div>
       
        <div v-if="editable">
            <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">基本信息</h2>
        <Form>
            <FormItem label="店铺名" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input  v-model:value="formData.name"/>
            </FormItem>
            <FormItem label="营业状态" :label-col="{ span:8}" :wrapper-col="{ span:2 }">
                <a-switch v-model:checked="formData.isOpen" checked-children="营业中" un-checked-children="打烊"/>
            </FormItem>
            <FormItem label="所在地区" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.area"/>
            </FormItem>
            <FormItem label="管理员姓名" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input disabled v-model:value="formData.managerName"/>
            </FormItem>
            <FormItem :wrapper-col="{ offset: 3, span: 16 }">
                 <Button type="primary" html-type="submit" @click="handleSubmit">保存</Button>
            </FormItem>
        </Form>
        </div>
       
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive } from 'vue'
import { Form,FormItem,Input,Button, message } from 'ant-design-vue';
import { string } from 'vue-types';
import request from '../../../utils/request';
export default defineComponent({
    
    components:{
        Form,
        FormItem,
        Input,
        Button
    },
    setup () {
        const hasStore = ref(true)
        const editable = ref(false)
        const userId = sessionStorage.getItem('userId')
        const formData=reactive({
            name: '',
            isOpen: false,
            area:'',
            managerName:''
        })

        function handleSubmit(){
            let isOpen='0'
            if(formData.isOpen){
                isOpen='1'
            }
            request.post('/store/edit',
            {
                data:{
                    name:formData.name,
                    isOpening: isOpen,
                    storePosition: formData.area,
                    managerName: formData.managerName
                },
                extra: userId
            }).then(res=>{
                reload()
                message.success(res.data.message)
            })
        }

        function reload(){
            
            request.post('/store/storeInfo',{data:userId}).then(res=>{
                load(res.data.data)
            })
        }

        function handleApply(){
            editable.value=true
            hasStore.value=false

        }

        function load(data){
            if(data.id){
                hasStore.value=false
                editable.value=true
            }
            formData.name = data.storeName
            if(data.isOpening==="1"){
                formData.isOpen=true
            }else{
                formData.isOpen = false
            }
            formData.area = data.storePosition
            formData.managerName = data.managerName
        }

        return {load,formData,handleSubmit,hasStore,editable,handleApply}
    }
})
</script>

<style scoped>

</style>