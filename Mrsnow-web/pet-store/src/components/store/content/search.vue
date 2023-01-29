<template>
    <div class="box">
        <IndexTitle class="logo"/>
        <SafetyCertificateOutlined style="color:violet; margin-top: 8px; font-size:xx-large; margin-left: 20px;"/>
        <span style="color: violet; font-weight: 900; margin-top: 10px; margin-left: 10px; font-size: large;">品质保证</span>
        <GlobalOutlined style="color:violet; margin-top: 8px; font-size:xx-large; margin-left: 20px;"/>
        <span style="color: violet; font-weight: 900; margin-top: 10px; margin-left: 10px; font-size: large;">全国配送</span>
        <AccountBookOutlined style="color:violet; margin-top: 8px; font-size:xx-large; margin-left: 20px;"/>
        <span style="color: violet; font-weight: 900; margin-top: 10px; margin-left: 10px; font-size: large;">退换无忧</span>
        <span class="search">
            <input class="input" type="text" placeholder="请输入关键字" v-model="searchKey">
            <span style="background-color: deeppink; width: 37px; height: 37px; margin-top: 5px; cursor: pointer;" @click="goSearch"> 
                <search-outlined style="color: white; margin-top: 10px; font-size: large;"/>
            </span>
        </span>
        <PhoneFilled style="color:crimson; margin-top: 8px; font-size:xx-large; margin-left: 50px;"/>
        <span style="color: crimson; font-weight: 900; font-style: italic; font-size: x-large; margin-left: 1%; margin-top: 7px;">加盟热线
            <span>400-800-7777</span>
        </span>
        
        
    </div>
</template>

<script lang="ts">
    import IndexTitle from '../../login/IndexTitle.vue';
    import { defineComponent,ref } from 'vue'
    import { SearchOutlined,SafetyCertificateOutlined,GlobalOutlined,AccountBookOutlined,PhoneFilled } from '@ant-design/icons-vue';
    import searchByKey from '../../../api/goods';
    export default defineComponent({

        name:'search-line',
        components:{
            IndexTitle,
            SearchOutlined,
            SafetyCertificateOutlined,
            GlobalOutlined,
            AccountBookOutlined,
            PhoneFilled
        },
        setup(props){
            const searchKey = ref<string>('');

            function goSearch(){
                console.log("搜索关键字为:"+searchKey.value)
                searchByKey({
                    data:{
                        searchKey: searchKey.value
                    }
                })
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
    }
    .search {
        display: flex;
        margin-left: 5%;
    }
    .input {
        background-color: rgb(255, 255, 255);
        border: 2px solid rgb(203, 29, 151);
        line-height: 27px;
        padding: 7px;
        height: 37px;
        margin-top: 5px;
        width: 360px;
    }

    .logo {
        margin-left: 10px;
    }
</style>