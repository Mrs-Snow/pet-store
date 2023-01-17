<template>
    <search style="margin-top: 15px"/>
    <guide style="margin-top: 15px"/>
    <div class="content">
        <img style="max-width: 50%;" src="../../assets/cat1.jpeg" />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import MyFooter from '../../components/layout/MyFooter.vue';
import MyHeader from '../../components/layout/MyHeader.vue';
import search from '../../components/store/content/search.vue';
import guide from '../../components/store/content/guide.vue';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search,
        guide
    },
    setup () {
        

        return {}
    }
})
</script>

<style scoped>
    .content {
        border: 1px solid #000;
        height: 80vh;
        width: 100%;
    }
</style>