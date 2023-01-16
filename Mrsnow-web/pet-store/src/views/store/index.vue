<template>
    <div>内容</div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import MyFooter from '../../components/layout/MyFooter.vue';
import MyHeader from '../../components/layout/MyHeader.vue';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
    },
    setup () {
        

        return {}
    }
})
</script>

<style scoped>

</style>