/* vue-default */
import { createApp } from 'vue'
//import { createMetaManager } from 'vue-meta'
/* qusar framework */
import { Quasar, Notify, Dialog, Meta, Dark } from 'quasar'
import '@quasar/extras/material-icons/material-icons.css'   // Import icon libraries
import 'quasar/src/css/index.sass'  // Import Quasar css
/* mobile-detect */
import VueMobileDetection from 'vue-mobile-detection'
/* user-defined  */
import './assets/main.scss'
import router from "@/route"
/* global component */
import ConfirmModal from "@/components/_modal/ConfirmModal.vue"

import App from './App.vue'

const app = createApp(App)

app.component('ConfirmModal', ConfirmModal)
app.use(router)
//app.use(createMetaManager())
app.use(Quasar, {
    plugins: {
        Notify, Dialog, Meta
    },
    config: {
        notify: {
            position: "top"
        }
    }
})
app.use(VueMobileDetection)
/*app.use(Meta, {

})*/
Dark.set(false)

app.mount('#app')
