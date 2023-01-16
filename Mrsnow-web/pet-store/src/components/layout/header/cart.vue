<template>
    <span class="header">
        <a-tooltip class="cart" title="购物车">
            <a-button shape="circle" size="large">
            <template #icon><ShoppingCartOutlined :style="{fontSize: '27px'}" />
                <a-badge
                 :count="cartNum"
                 :offset=[8,-49]
                ></a-badge>
            </template>
            </a-button>
        </a-tooltip>
    </span>
</template>

<script lang="ts">
import { ShoppingCartOutlined } from '@ant-design/icons-vue';
import { defineComponent, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router';
import { number } from 'vue-types';
export default defineComponent({
    name:'header-cart',
    props:{
        cartNum:{
            type:Number,
            default: 0
        }
    },
    components: {
    ShoppingCartOutlined
},
    setup (cartNum) {
        
        return {}
    }
})
</script>

<style scoped>
    .cart{
        margin-right: 30px;
    }
</style>