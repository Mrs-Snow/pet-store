<template>
    <div id="container">
    <index-title class="title"></index-title>
    <a-form
    id="login_form"
    :model="formState"
    name="basic"
    :label-col="{ span: 5 }"
    :wrapper-col="{ span: 16 }"
    autocomplete="off"
    :hideRequiredMark = true
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  > 
  <a-typography-title>登录</a-typography-title>
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

    <a-form-item  style="margin-top: -20px;" name="remember" :wrapper-col="{ offset: 0, span: 20 }">
      <a-checkbox class="login-form-remember" v-model:checked="formState.remember">记住我</a-checkbox>
      <a class="login-form-forgot" @click="findPassword">忘记密码？</a>
    </a-form-item>

    <a-form-item style="margin-top: -10px; margin-left: 30px;" :wrapper-col="{ offset: 2, span: 18 }">
      <a-button type="primary" html-type="submit">登录</a-button>
      <a-button style="margin-left: 30px" type="primary" @click="handleRegister">注册</a-button>
    </a-form-item>

  </a-form>
    </div>
</template>

<script lang="ts">
import { message } from 'ant-design-vue';
import doLogin from '@/api/login'
import { defineComponent,reactive } from 'vue'
import { useRouter } from 'vue-router';
import IndexTitle from '../../components/login/IndexTitle.vue';
interface FormState {
  username: string;
  password: string;
  remember: boolean;
}
export default defineComponent({
  components:{
    IndexTitle
  },
    setup () {
      const router = useRouter();
      const formState = reactive<FormState>({
        username: '',
        password: '',
        remember: true,
      });
      //登录按钮
      const onFinish = (values: FormState) => {
        doLogin(
          {
            userName: values.username,
            password: values.password
          }
        ).then(res=>{
          if(res.data.code===200){
            message.success("登录成功！")
            sessionStorage.setItem("token",res.data.token)
            router.push({
              path: '/',
              params: {
                user: res.data.data
              }
            })
          }
          if(res.data.code===-1){
            message.error(res.data.message)
          }
        }).catch(err=>{
           message.error(err)
        })
      };
      //注册按钮
    const handleRegister=()=>{
      console.log("注册")
      router.push('/register')
    };
    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    //找回密码
    const findPassword=()=>{
      router.push('/findPassword')
    }

        return {
            formState,
            onFinish,
            onFinishFailed,
            handleRegister,
            findPassword,
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
    #login_form {
        padding-top: 30px;
        border-radius: 6px;
        width: 500px;
        height: 300px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(51%,-50%);
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
    .login-form-forgot {
     float: right;
    }
    .login-form-remember{
      margin-left: -100px;
    }
</style>