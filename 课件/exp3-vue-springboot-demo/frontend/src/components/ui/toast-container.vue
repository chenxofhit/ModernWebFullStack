<template>
  <div
    class="fixed inset-0 flex flex-col items-end space-y-4 p-4 sm:items-end sm:justify-start sm:p-6"
  >
    <TransitionGroup
      enter-active-class="transform ease-out duration-300 transition"
      enter-from-class="translate-y-2 opacity-0 sm:translate-y-0 sm:translate-x-2"
      enter-to-class="translate-y-0 opacity-100 sm:translate-x-0"
      leave-active-class="transition ease-in duration-100"
      leave-from-class="opacity-100"
      leave-to-class="opacity-0"
    >
      <Toast
        v-for="toast in toasts"
        :key="toast.id"
        v-bind="toast"
        @close="removeToast(toast.id)"
      />
    </TransitionGroup>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Toast from './toast.vue'

const toasts = ref([])

const addToast = (toast) => {
  const id = Date.now()
  toasts.value.push({ ...toast, id })
  if (toast.duration !== 0) {
    setTimeout(() => {
      removeToast(id)
    }, toast.duration || 3000)
  }
}

const removeToast = (id) => {
  toasts.value = toasts.value.filter((toast) => toast.id !== id)
}

defineExpose({
  addToast
})
</script> 