<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">修改密码</h2>
        <Form>
            <FormItem label="旧密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <a-input-password :rules="[{ required: true, message: '请输入旧密码!' }]"
                 placeholder="请输入旧密码" v-model:value="formData.old" />
            </FormItem>
            <FormItem label="新密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <a-input-password :rules="[{ required: true, message: '请输入新密码!' }]"
                 placeholder="请输入新密码" v-model:value="formData.new1"/>
            </FormItem>
            <FormItem label="确认密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <a-input-password :rules="[{ required: true, message: '请再次输入新密码!' }]"
                 placeholder="请再次输入新密码" v-model:value="formData.new2"/>
            </FormItem>
            <FormItem :wrapper-col="{ offset: 3, span: 16 }">
                 <Button type="primary" html-type="submit" @click="handleSubmit">确认提交</Button>
            </FormItem>
        </Form>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from 'vue'
import { Form,FormItem,Input,Button, message } from 'ant-design-vue';
import request from '../../../utils/request';
export default defineComponent({
    components:{
        Form,
        FormItem,
        Input,
        Button
    },
    setup () {
        const formData=reactive({
            old:'',
            new1:'',
            new2:''
        })
        function handleSubmit(){
            if(formData.new1!==formData.new2){
                message.error('两次输入的密码不一致');
                return;
            }
            request.post('/user/updatePassword',
            {
                data:{
                    old:formData.old,
                    new:formData.new1,
                    userId:sessionStorage.getItem('userId')
                }
            }).then(res=>{
                message.info(res.data.message)
            })
        }

        return {handleSubmit,formData}
    }
})
</script>

<style scoped>

</style>