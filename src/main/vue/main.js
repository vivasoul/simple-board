/* vue-default */
import { createApp } from 'vue'
//import Meta from 'vue-meta'
/* qusar framework */
import { Quasar, Notify } from 'quasar'
import '@quasar/extras/material-icons/material-icons.css'   // Import icon libraries
import 'quasar/src/css/index.sass'  // Import Quasar css
/* user-defined  */
import './assets/main.scss'
import router from "@/route";
import App from './App.vue'

const app = createApp(App)
app.use(router)
app.use(Quasar, {
    plugins: {
        Notify
    },
    config: {
        notify: {
            position: "top"
        }
    }
})
/*app.use(Meta, {

})*/
app.mount('#app')
