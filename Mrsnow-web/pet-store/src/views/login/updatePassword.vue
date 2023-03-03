<template>
    <div id="container">
    <index-title style="float: left; margin-left: 120px; margin-top: 20px;"></index-title>
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
  <a-typography-title>修改密码</a-typography-title>
    <a-form-item
      label="新密码"
      name="password1"
      :rules="[{ required: true, message: '请输入新密码!' }]"
    >
      <a-input-password v-model:value="formState.password1" />
    </a-form-item>

    <a-form-item
      label="确认密码"
      name="password2"
      :rules="[{ required: true, message: '请再次输入密码!' }]"
    >
      <a-input-password v-model:value="formState.password2" />
    </a-form-item>

    <a-form-item style="margin-top: 30px; margin-left: 30px;" :wrapper-col="{ offset: 2, span: 18 }">
      <a-button type="primary" html-type="submit">提交</a-button>
    </a-form-item>

  </a-form>
    </div>
</template>

<script lang="ts">
import { message } from 'ant-design-vue';
import { defineComponent,onMounted,reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router';
import { updatePassword } from '../../api/login';
import IndexTitle from '../../components/login/IndexTitle.vue';
interface FormState {
  password1: string;
  password2: string;
}
export default defineComponent({
  components:{
    IndexTitle
  },
    setup () {
      const router = useRouter();
      const route = useRoute();
      const username = route.params.username
      const formState = reactive<FormState>({
        password1: '',
        password2: '',
      });
      onMounted(()=>{
        console.log("route!!!!",route.params.username)
        console.log(username)
      })
      //登录按钮
      const onFinish = (values: FormState) => {
        console.log(route.params)
        updatePassword(
          {
            data:{
              old: values.password1,
              new: values.password2,
              username: username
            }
          }
        ).then(res=>{
          if(res.data.code===200){
            message.success(res.data.message)
            router.push('/login')
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
    const goIndex=()=>{
        router.replace('/')
    }
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
    #login_form {
        padding-top: 30px;
        border-radius: 6px;
        width: 500px;
        height: 300px;
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
</style>