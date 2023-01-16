<template>
    <span class="header">
        <a-tooltip class="user" title="个人中心">
            <a-button shape="circle" size="large">
            <template #icon><UserOutlined :style="{fontSize: '27px'}" />
            </template>
            </a-button>
        </a-tooltip>
    </span>
</template>

<script lang="ts">
import { UserOutlined } from '@ant-design/icons-vue';
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router';
export default defineComponent({
    name:'header-user',
    components: {
    UserOutlined,
  },
    setup () {
        return {}
    }
})
</script>

<style scoped>
    .user{
        margin-right: 30px;
    }
</style>