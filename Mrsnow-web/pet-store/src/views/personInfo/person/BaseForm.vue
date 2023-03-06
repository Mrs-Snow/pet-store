<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">基本信息</h2>
        <Form>
            <FormItem label="用户名" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input disabled v-model:value="formData.userName"/>
            </FormItem>
            <FormItem label="手机号" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.phone"/>
            </FormItem>
            <FormItem label="邮箱" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.email"/>
            </FormItem>
            <FormItem label="我的昵称" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input v-model:value="formData.nickName"/>
            </FormItem>
            <FormItem :wrapper-col="{ offset: 3, span: 16 }">
                 <Button type="primary" html-type="submit" @click="save">保存</Button>
            </FormItem>
        </Form>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,inject } from 'vue'
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
        const updateHeader =  inject('reload')
        const formData=reactive({
            userName: '',
            phone: '',
            email:'',
            nickName:''
        })
        const userId = sessionStorage.getItem('userId')

        function save(){
            request.post('/user/update',{data:{
                id: userId,
                userName: formData.userName,
                phone: formData.phone,
                email: formData.email,
                nickName: formData.nickName,
            }
            }).then(res=>{
                message.info(res.data.message)
                reload()
            })
            message.success('部分更改下次登录生效')
        }

        function reload(){
            request.post('/user/myself',{data: userId}).then(res=>{
                const data = res.data.data
                formData.userName = data.userName
                formData.phone = data.mobile
                formData.email = data.email
                formData.nickName = data.nickName
            })
            
        }
        
        function load(data){
            formData.userName = data.userName
            formData.phone = data.mobile
            formData.email = data.email
            formData.nickName = data.nickName
        }

        return {load,formData,save}
    }
})
</script>

<style scoped>

</style>