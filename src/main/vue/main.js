import { createApp } from 'vue'
import { Quasar } from 'quasar'
// Import icon libraries
import '@quasar/extras/material-icons/material-icons.css'
// Import Quasar css
import 'quasar/src/css/index.sass'

import './assets/main.css'
import router from "@/route";
import App from './App.vue'

const app = createApp(App)
app.use(router)
app.use(Quasar, {
    plugins: {}
})
app.mount('#app')
