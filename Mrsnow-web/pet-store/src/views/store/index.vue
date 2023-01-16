<template>
    <search/>
    <div class="content">内容</div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import MyFooter from '../../components/layout/MyFooter.vue';
import MyHeader from '../../components/layout/MyHeader.vue';
import search from '../../components/store/content/search.vue';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search
    },
    setup () {
        

        return {}
    }
})
</script>

<style scoped>
    .content {
        height: 80vh;
        width: 100%;
    }
</style>