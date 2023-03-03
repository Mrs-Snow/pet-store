<template>
    <div>
        <div>
            <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">发货信息</h2>
        <Form>
            <FormItem label="发货人" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input  v-model:value="formData.consigner"/>
            </FormItem>
            <FormItem label="联系电话" :label-col="{ span:8}" :wrapper-col="{ span:8 }">
                <Input v-model:value="formData.consignerPhone"/>
            </FormItem>
            <FormItem label="详细地址" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <a-textarea v-model:value="formData.consignerAddress"/>
            </FormItem>
            <FormItem :wrapper-col="{ offset: 3, span: 16 }">
                 <Button type="primary" html-type="submit" @click="handleSubmit">保存</Button>
            </FormItem>
        </Form>
            <h4 style="color: darkgray;">* 商店的信息平台不会透露给任何第三方,仅在需要介入客户之间的协商中使用</h4>
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
            consigner: '',
            consignerPhone: '',
            consignerAddress:'',
        })

        function handleSubmit(){
            request.post('/shipAddress/updateInfo',{data:{
                consigner:formData.consigner,
                consignerPhone:formData.consignerPhone,
                consignerAddress:formData.consignerAddress,
                storeId:sessionStorage.getItem('storeId')
            }}).then(res=>{
                message.success('已保存')
            })
        }

        function reload(){
            
            request.post('/shipAddress/getInfo',{data:userId}).then(res=>{
                load(res.data.data)
            })
        }

        function handleApply(){
            editable.value=true
            hasStore.value=false

        }

        function load(data){
            formData.consigner = data.consigner
            formData.consignerPhone = data.consignerPhone
            formData.consignerAddress = data.consignerAddress
        }

        return {load,formData,handleSubmit,hasStore,editable,handleApply,reload}
    }
})
</script>

<style scoped>

</style>