<template>
    <div style="margin-top: 10px;">
        <span :id=tab+index class="box" @click="click" >{{ tab }}</span>
    </div>
    
</template>

<script lang="ts">

import { defineComponent,onMounted,ref } from 'vue'

export default defineComponent({
    name: 'Mytab',
    props:{
        index:{
            type:String,
            default: 1
        },
        tab:{
            type:String,
            default: 'tab 1'
        },
        selectColor:{
            type:String,
            default: 'deeppink'
        }
    },
    
    setup (props,{emit}) {
        const tabs = ['罐头','猫条','猫粮','冻干','猫薄荷','饭盆','饮料','磨牙食品'];
        const tabName = ref(props.tab)
        onMounted(()=>{
           
        })

        function click(e:Event) {
            emit('tabName',tabName)
            //刷新一下整体,再单独变色
            for(var i=0;i<8;i++){
                const id = tabs[i]+(i+1)
                let e =document.getElementById(id)
                if(e){
                    e.style.fontSize='16px'
                    e.style.fontWeight='400'
                    e.style.color='black'
                }
            }
            const id = e.target.id
            if(id){
              const box =  document.getElementById(id)
              if(box){
                if(props.selectColor){
                    // console.log("传入有颜色",props.selectColor)
                    box.style.color=props.selectColor
                }else{
                    box.style.color='deeppink'
                }
                box.style.fontWeight='800'
                box.style.fontSize = '20px'
              }
            }
            
    
        }

        return { 
            click,
            tabName
        }
    }
})
</script>

<style scoped>
    .box {
        font-size: 16px;
        font-weight: 400;
        margin-top: 10px;
        transition: all 0.1s;
        cursor: pointer;
    }
    .box:hover{
        color: deeppink;
        border-bottom:2px solid deeppink;
    }
</style>