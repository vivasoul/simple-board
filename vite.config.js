import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import { quasar, transformAssetUrls } from '@quasar/vite-plugin'

// https://vitejs.dev/config/
export default defineConfig({
    base: "/static/vue/",
    root: "src/main/vue",
    plugins: [
        vue({
            template: { transformAssetUrls }
        }),
        quasar({
            sassVariables: 'src/main/vue/quasar-variables.sass'
        })
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
