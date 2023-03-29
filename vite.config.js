import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  base: "/static/vue/",
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    outDir: "D:\\DEV\\intelli_space\\simple-board-api\\src\\main\\resources\\vue",
    assetsDir: "assets",
    emptyOutDir: true
  }
})
