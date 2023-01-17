<template>
    <search style="margin-top: 15px"/>
    <guide style="margin-top: 15px"/>
    <div class="content">
        <Carousel
        class="carousel"
        effect="fade"
        autoplay
        :dots=false
        >
            <img class="img" src="../../assets/cat1.jpeg" />
            <img class="img" src="../../assets/cat2.jpeg" />
            <img class="img" src="../../assets/cat3.jpg" />
        </Carousel>
        
    </div>
    
    
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import MyFooter from '../../components/layout/MyFooter.vue';
import MyHeader from '../../components/layout/MyHeader.vue';
import search from '../../components/store/content/search.vue';
import guide from '../../components/store/content/guide.vue';
import { Carousel } from 'ant-design-vue';
export default defineComponent({
    components: {
        MyHeader,
        MyFooter,
        search,
        guide,
        Carousel
    },
    setup () {
        

        return {}
    }
})
</script>

<style scoped>
    .carousel{
        cursor: pointer;
        margin-left: 30%;
        width: 50%;
    }

    .img{
        width: 100%;
        height: 400px;
    }

    .content {
        border: 1px solid #000;
        height: 80vh;
        width: 100%;
    }
</style>