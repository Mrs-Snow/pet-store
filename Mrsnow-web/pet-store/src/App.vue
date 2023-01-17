<template>
    <my-header></my-header>
    <router-view></router-view>
    <my-footer></my-footer>
</template>
<script setup lang="ts">
import MyFooter from './components/layout/MyFooter.vue';
import MyHeader from './components/layout/MyHeader.vue';
</script>

<style scoped>
   
</style>
