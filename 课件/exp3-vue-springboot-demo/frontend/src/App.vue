<template>
  <router-view />
  <ToastContainer ref="toastContainerRef" />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import ToastContainer from '@/components/ui/toast-container.vue'
import { setToastContainer } from '@/utils/toast'

const toastContainerRef = ref(null)

onMounted(() => {
  setToastContainer(toastContainerRef.value)
})
</script>

<style>
body {
  margin: 0;
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style> 