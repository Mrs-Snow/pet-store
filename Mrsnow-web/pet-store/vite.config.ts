import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'
// https://vitejs.dev/config/


export default defineConfig(({ command, mode }) => {
    const env = loadEnv(mode, process.cwd(),'')
    console.log(env)
   return{
      plugins: [vue()],

      resolve:{
        alias:{
          "@" : resolve(__dirname,"./src")
        }
      },

      server:{
        https:false,
        host:true,
        port:env.VITE_PORT,
        proxy: {
          "api": {
            target: env.VITE_APP_API_BASEURL,
            changeOrigin: true,
          }
        }
      }
    }
})
