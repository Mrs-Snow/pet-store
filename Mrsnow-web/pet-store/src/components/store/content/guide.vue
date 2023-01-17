<template>
    <div class="box">
        <span class="guide">商品分类</span>
        <Mtab :index="'1'" :tab="'罐头'" style="margin-left: 5%"/>
        <Mtab :index="'2'" :tab="'猫条'" style="margin-left: 5%"/>
        <Mtab :index="'3'" :tab="'猫粮'" style="margin-left: 5%"/>
        <Mtab :index="'4'" :tab="'冻干'" style="margin-left: 5%"/>
        <Mtab :index="'5'" :tab="'猫薄荷'" style="margin-left: 5%"/>
        <Mtab :index="'6'" :tab="'饭盆'" style="margin-left: 5%"/>
        <Mtab :index="'7'" :tab="'饮料'" style="margin-left: 5%"/>
        <Mtab :index="'8'" :tab="'磨牙食品'" style="margin-left: 5%"/>
    </div>
</template>

<script lang="ts">
import { defineComponent,ref } from 'vue'
import Mtab from '../../tabs/Mtab.vue';
export default defineComponent({
    name: 'my-guide',
    components:{
        Mtab
    },
    setup () {
        
        
        return {
            
            
        }
    }
})
</script>

<style scoped>

    .tabs {
        margin-left: 20px;
    }
    .box {
        height: 42px;
        display: flex;
        width: 70%;
        margin-left: 10%;
        background-color: rgb(244, 247, 249);
    }

    .guide {
        background-color: deeppink; 
        width: 10%; 
        height: 100%; 
        cursor: pointer;
        color: aliceblue;
        font-size: larger;
        font-weight: 700;
        padding-top: 8px;
        transition: all 0.3s;
    }

    .guide:hover{
        transform: matrix(1.2,0,0,1.2,0,3);
    }

 

</style>