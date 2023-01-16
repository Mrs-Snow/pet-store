<template>
    <div class="header">
        <div class="base">
            <span class="welcome">您好, {{ username }}!</span>
            <cart :cartNum="5"></cart>
            <user></user>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import user from './header/user.vue';
import cart from './header/cart.vue';
import { useRouter } from 'vue-router';
import { number, string } from 'vue-types';

export default defineComponent({
    props:{
        username:{
            type: String,
            default:'游客'
        },
    },
    name:'my-header',
    components: {
        user,
        cart,
  },
    setup (props) {
        return {}
    }
})
</script>

<style scoped>
    .welcome{
        color: rgb(248, 248, 248);
        font-weight: 700;
        margin-right: 2%;
    }
    .header {
        flex-direction: row-reverse;
        width: 100vw;
        background-color:rgb(189, 36, 176);
    }
    .base{
        width: 100%;
        margin-left: 43%;
    }
</style>