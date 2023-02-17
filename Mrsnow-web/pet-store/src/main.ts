import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import router from './router';
import  {setUsername}  from './api/login'
import 'ant-design-vue/dist/antd.css';

const app = createApp(App);

const userInfo:User = {}

app.config.globalProperties.$setUser = setUsername
app.config.globalProperties.$userId = ''

app.use(Antd).use(router).mount('#app');
