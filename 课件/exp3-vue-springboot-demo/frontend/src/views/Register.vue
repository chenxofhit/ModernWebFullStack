<template>
  <div class="register-container">
    <div class="register-box">
      <h2 class="title">注册</h2>
      <el-form @submit.prevent="handleRegister">
        <el-form-item>
          <el-input
            v-model="registerForm.username"
            placeholder="用户名"
            prefix-icon="User"
          />
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="registerForm.password"
            type="password"
            placeholder="密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="registerForm.email"
            type="email"
            placeholder="电子邮箱"
            prefix-icon="Message"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            class="register-button"
            @click="handleRegister"
          >
            {{ loading ? '注册中...' : '注册' }}
          </el-button>
        </el-form-item>
        <div class="login-link">
          <router-link to="/login">已有账号？立即登录</router-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()
const registerForm = ref({
  username: '',
  password: '',
  email: ''
})

const loading = ref(false)

const handleRegister = async () => {
  try {
    loading.value = true
    const response = await userStore.register(registerForm.value)
    if (response.code === 200) {
      ElMessage.success('注册成功')
      router.push('/login')
    }
  } catch (error) {
    console.error('注册错误:', error)
    ElMessage.error(error.message || '注册失败')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.register-box {
  width: 400px;
  padding: 40px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  margin-bottom: 30px;
  color: #303133;
  font-size: 24px;
}

.register-button {
  width: 100%;
}

.login-link {
  text-align: center;
  margin-top: 20px;
}

.login-link a {
  color: #409eff;
  text-decoration: none;
}

.login-link a:hover {
  color: #66b1ff;
}
</style>