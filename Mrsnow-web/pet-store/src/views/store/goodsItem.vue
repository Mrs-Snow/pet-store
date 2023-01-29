<template>
    <div class="box">
        这是一条商品信息
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'goodsItem',
    setup () {
        

        return {}
    }
})
</script>

<style scoped>
    .box {
        margin-top: 17px;
        width: 100%px;
        height: 100px;
        border: 1px solid #000;
    }
</style>