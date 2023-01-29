<template>
    <a-layout>
      <a-layout-footer class="footer" style="text-align: center">
        河南中医药大学 ©2022 Created by Ren
      </a-layout-footer>
    </a-layout>
  </template>
  <script lang="ts">
  import { defineComponent, ref,onMounted } from 'vue';
import { useRouter } from 'vue-router';
  export default defineComponent({
    name: 'my-footer',
    setup() {
    
      return {
        
      };
    },
  });
  </script>
  <style>
    .footer {
      width: 100%;
      height: 10%;
    }
  </style>