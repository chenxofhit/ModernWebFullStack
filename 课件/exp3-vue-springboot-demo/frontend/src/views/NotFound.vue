<template>
  <div class="not-found">
    <div class="content">
      <h1>404</h1>
      <h2>Page Not Found</h2>
      <p>The page you are looking for does not exist.</p>
      <el-button type="primary" @click="goHome">Go Home</el-button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push('/')
}
</script>

<style scoped>
.not-found {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: var(--background-color);
}

.content {
  text-align: center;
  padding: 40px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 72px;
  margin: 0;
  color: var(--primary-color);
}

h2 {
  margin-top: 10px;
  margin-bottom: 20px;
  color: #333;
}

p {
  margin-bottom: 30px;
  color: #666;
}
</style> 