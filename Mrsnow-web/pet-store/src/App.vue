<template>
    <my-header v-if="state.ifshow"></my-header>
    <router-view></router-view>
    <my-footer></my-footer>
</template>
<script  lang="ts">

import { provide, reactive, nextTick } from 'vue';
import MyFooter from './components/layout/MyFooter.vue';
import MyHeader from './components/layout/MyHeader.vue';
export default{
    components:{
        MyFooter,
        MyHeader
    },
    setup(){
        const state = reactive({
            ifshow: true
        })
        function reload(){
            state.ifshow=false
            nextTick(()=>{
                state.ifshow=true
            })
        }
        provide('reload',reload)
        return{
            state
        }
    }
}

</script>
    
<style scoped>
   
</style>
