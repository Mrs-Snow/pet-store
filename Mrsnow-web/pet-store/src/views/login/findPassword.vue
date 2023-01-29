<template>
    <div id="container">
    <index-title class="title"></index-title>
    <a-form
    id="register_form"
    :model="formState"
    name="basic"
    :label-col="{ span: 5 }"
    :wrapper-col="{ span: 16 }"
    autocomplete="off"
    :hideRequiredMark = true
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  > 
  <a-typography-title>验证</a-typography-title>
    <a-form-item
      label="用户名"
      name="username"
      :rules="[{ required: true, message: '请输入有效的用户名!' }]"
    >
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item
      label="手机号"
      name="mobile"
      :rules="[{ required: true, message: '请输入有效的手机号!' }]"
    >
      <a-input v-model:value="formState.mobile" />
    </a-form-item>
    <a-form-item
      label="邮箱"
      name="email"
      :rules="[{ required: true, message: '请输入有效的邮箱地址!' }]"
    >
      <a-input v-model:value="formState.email" />
    </a-form-item>

    <a-form-item  :wrapper-col="{ offset: 2, span: 18 }">
      <a-button size="large" type="primary" html-type="submit">验证</a-button>
    </a-form-item>
  </a-form>
    </div>
</template>

<script lang="ts">
import { message } from 'ant-design-vue';
import request from "@/utils/request";
import { defineComponent,reactive } from 'vue'
import { useRouter } from 'vue-router';
import { findPassword } from '../../api/login';
import router from '../../router';
import IndexTitle from '../../components/login/IndexTitle.vue';
interface FormState {
  username: string;
  mobile: string;
  email: string;
  remember: boolean;
}
export default defineComponent({
  components:{
    IndexTitle
  },
    setup () {
      const formState = reactive<FormState>({
        username: '',
        mobile: '',
        email: '',
        remember: false
      });
      const router = useRouter();
      const onFinish = (values: FormState) => {
        findPassword(
          {
            userName: values.username,
            mobile: values.mobile,
            email: values.email
          }
        ).then(res=>{
          if(res.data.code===200){
            message.success("验证成功");
            router.push({
              name:'updatePassword',
              params: {
                username: values.username
              }
            })
          }
        }).catch(err=>{
          console.log(err)
        })
       
      };
    const goIndex=()=>{
        router.replace('/')
    }
    const backLogin=()=>{
        router.push('/login')
    }
    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    

        return {
            formState,
            onFinish,
            onFinishFailed,
            goIndex,
            backLogin
        } 
  }
})
</script>

<style scoped>
    #container {
        position: relative;
        width: 100vw;
        height: 100vh;
        background-image: url(../../assets/bg1.webp);
        background-size: cover;
    }
    #register_form {
        padding-top: 30px;
        border-radius: 6px;
        width: 500px;
        height: 400px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(51%,-50%);
        background-color: aliceblue;
        opacity: 0.9;
        background-image: url(../../assets/loginbg.webp);
        background-size: cover;
    }
    .title {
      cursor: pointer;
      float: left;
      margin-top: 30px;
      margin-left: 30px;
    }
    .login-form-goLogin {
        float: right;
    }
    .login-form-remember{
      margin-left: 0px;
    }
</style>