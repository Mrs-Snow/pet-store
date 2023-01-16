<template>
    <div id="container">
      <index-title></index-title>
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
  <a-typography-title>注册</a-typography-title>
    <a-form-item
      label="用户名"
      name="username"
      :rules="[{ required: true, message: '请输入有效的用户名!' }]"
    >
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item
      label="密码"
      name="password"
      :rules="[{ required: true, message: '请输入密码!' }]"
    >
      <a-input-password v-model:value="formState.password" />
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

    <a-form-item  style="margin-top: -20px;" name="remember" :wrapper-col="{ offset: 0, span: 20 }">
        <a-checkbox class="login-form-remember" v-model:checked="formState.remember">我已阅读不存在的协议</a-checkbox>
      <a class="login-form-goLogin" @click="backLogin">已经有帐号了？去登录...</a>
    </a-form-item>

    <a-form-item style="margin-top: -10px;" :wrapper-col="{ offset: 2, span: 18 }">
      <a-button size="large" type="primary" html-type="submit">提交注册</a-button>
    </a-form-item>
  </a-form>
    </div>
</template>

<script lang="ts">
import { message } from 'ant-design-vue';
import request from "@/utils/request";
import { defineComponent,reactive } from 'vue'
import { useRouter } from 'vue-router';
import IndexTitle from '../../components/login/IndexTitle.vue';
import { doLogin } from '../../api/login';
import router from '../../router';
interface FormState {
  username: string;
  password: string;
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
        password: '',
        mobile: '',
        email: '',
        remember: false
      });
      const router = useRouter();
      const onFinish = (values: FormState) => {
        request.post(
          'http://127.0.0.1:17777/user/register',
          {
            userName: values.username,
            password: values.password,
            mobile: values.mobile,
            email: values.email
          }
        ).then(res=>{
          console.log(res)
          if(res.data.code === -1){
              message.error(res.data.message);
          }
          if(res.data.code===200){
            message.success("注册成功！")
            router.push('/login')
          }
        }).catch(err=>{
           message.error(err)
        })
      };
      const goIndex=()=>{
        router.replace('/')
    }
    const handleRegister=()=>{
      console.log("注册")
    };
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
            handleRegister,
            backLogin,
            goIndex
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