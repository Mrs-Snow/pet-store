import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import router from './router';
import 'ant-design-vue/dist/antd.css';

const app = createApp(App);

app.use(Antd).use(router).mount('#app');