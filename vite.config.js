import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    base: "/static/vue/",
    root: "src/main/vue",
    plugins: [
        vue()
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('src/main/vue', import.meta.url))
        }
    },
    build: {
        outDir: fileURLToPath(new URL('src/main/resources/vue', import.meta.url)),
        assetsDir: "assets",
        emptyOutDir: true
    }
})
