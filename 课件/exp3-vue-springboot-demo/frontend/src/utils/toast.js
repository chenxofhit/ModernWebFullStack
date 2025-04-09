import { ref } from 'vue'

const toastContainerRef = ref(null)

export const setToastContainer = (ref) => {
  toastContainerRef.value = ref
}

export const toast = {
  success(message, title = '成功', duration = 3000) {
    this.show({ type: 'success', message, title, duration })
  },
  error(message, title = '错误', duration = 3000) {
    this.show({ type: 'error', message, title, duration })
  },
  info(message, title = '提示', duration = 3000) {
    this.show({ type: 'info', message, title, duration })
  },
  warning(message, title = '警告', duration = 3000) {
    this.show({ type: 'warning', message, title, duration })
  },
  show({ type, message, title, duration }) {
    if (toastContainerRef.value) {
      toastContainerRef.value.addToast({ type, message, title, duration })
    }
  }
} 