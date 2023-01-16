<template>
    <my-header></my-header>
    <router-view class="container"></router-view>
    <my-footer></my-footer>
</template>
<script setup lang="ts">
import MyFooter from './components/layout/MyFooter.vue';
import MyHeader from './components/layout/MyHeader.vue';
</script>

<style scoped>
   .container {
     height: 90vh;
   }
</style>
