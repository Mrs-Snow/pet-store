<template>
    <div class="box">
        <IndexTitle class="logo"/>
        <span class="search">
            <input class="input" type="text" placeholder="请输入关键字" v-model="searchKey">
            <span style="background-color: deeppink; width: 37px; height: 37px; margin-top: 5px;" @click="goSearch"> 
                <search-outlined style="color: white; margin-top: 10px; font-size: large;"/>
            </span>
        </span>
        
        
    </div>
</template>

<script lang="ts">
    import IndexTitle from '../../login/IndexTitle.vue';
    import { defineComponent,ref } from 'vue'
    import { SearchOutlined } from '@ant-design/icons-vue';
import { string } from 'vue-types';
    export default defineComponent({

        name:'search-line',
        components:{
            IndexTitle,
            SearchOutlined
        },
        setup(props){
            const searchKey = ref<string>('');

            function goSearch(){
                console.log("搜索关键字为:"+searchKey.value)
            }
            return{
                goSearch,
                searchKey
            }
        }
    })
</script>

<style scoped>
    .box {
        display: flex;
        width: 100%;
        height: 50px;
        border: 1px solid #000;
    }
    .search {
        display: flex;
        margin-left: 30%;
    }
    .input {
        background-color: rgb(255, 255, 255);
        border: 2px solid rgb(203, 29, 151);
        line-height: 27px;
        padding: 7px;
        height: 37px;
        margin-top: 5px;
        width: 270px;
    }

    .logo {
        margin-left: 10px;
    }
</style>