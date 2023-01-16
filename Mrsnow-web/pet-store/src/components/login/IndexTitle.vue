<template>
    <img @click="goIndex" src="../store/images/main_logo.png">
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';
    export default defineComponent({
        name: 'index-title',
        setup (){
        const router = useRouter();
        const goIndex= () =>{
        router.replace('/')
    }
            return {
                goIndex 
            }
        }
    })
</script>