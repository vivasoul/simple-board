import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import { quasar, transformAssetUrls } from '@quasar/vite-plugin'
import transportOutput from "./src/main/vue/plugins/transport-output-plugin.js"

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
        }),
        transportOutput({
            srcPath: fileURLToPath(new URL('src/main/resources/vue', import.meta.url)),
            destPath: fileURLToPath(new URL('build/resources/main/vue', import.meta.url)),
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
