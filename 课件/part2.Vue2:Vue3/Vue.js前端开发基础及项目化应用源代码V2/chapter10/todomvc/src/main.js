import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
//引入element-plus样式
import 'element-plus/dist/index.css'

const app = createApp(App)
//项目中加载element-plus
app.use(ElementPlus)
//app.use(ElButton)
app.mount('#app')

