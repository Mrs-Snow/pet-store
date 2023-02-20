<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">修改密码</h2>
        <Form>
            <FormItem label="旧密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input :rules="[{ required: true, message: '请输入旧密码!' }]"
                 placeholder="请输入旧密码" />
            </FormItem>
            <FormItem label="新密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input :rules="[{ required: true, message: '请输入新密码!' }]"
                 placeholder="请输入新密码"/>
            </FormItem>
            <FormItem label="确认密码" :label-col="{ span:8}" :wrapper-col="{span:8}">
                <Input :rules="[{ required: true, message: '请再次输入新密码!' }]"
                 placeholder="请再次输入新密码"/>
            </FormItem>
            <FormItem :wrapper-col="{ offset: 3, span: 16 }">
                 <Button type="primary" html-type="submit">确认提交</Button>
            </FormItem>
        </Form>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { Form,FormItem,Input,Button } from 'ant-design-vue';
export default defineComponent({
    components:{
        Form,
        FormItem,
        Input,
        Button
    },
    setup () {
        

        return {}
    }
})
</script>

<style scoped>

</style>