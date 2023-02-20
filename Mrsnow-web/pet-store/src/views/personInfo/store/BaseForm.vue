<template>
    <div>
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
                 <Button type="primary" html-type="submit">保存</Button>
            </FormItem>
        </Form>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive } from 'vue'
import { Form,FormItem,Input,Button } from 'ant-design-vue';
import { string } from 'vue-types';
export default defineComponent({
    
    components:{
        Form,
        FormItem,
        Input,
        Button
    },
    setup () {
        const formData=reactive({
            name: '',
            isOpen: false,
            area:'',
            managerName:''
        })

        
        function load(data){
            formData.name = data.storeName
            if(data.isOpening==="1"){
                formData.isOpen=true
            }else{
                formData.isOpen = false
            }
            formData.area = data.storePosition
            formData.managerName = data.managerName
        }

        return {load,formData}
    }
})
</script>

<style scoped>

</style>