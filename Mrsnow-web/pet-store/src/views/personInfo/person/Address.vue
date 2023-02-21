<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">我的收获地址</h2>
        <Form>
            <FormItem label="用户名" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input disabled v-model:value="formData.consignee"/>
            </FormItem>
            <FormItem label="手机号" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.consigneeTel"/>
            </FormItem>
            <FormItem label="邮箱" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.deliveryAddress"/>
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
            consignee: '',
            consigneeTel: '',
            deliveryAddress:'',
        })

        
        function load(data){
            
        }

        return {load,formData}
    }
})
</script>

<style scoped>

</style>