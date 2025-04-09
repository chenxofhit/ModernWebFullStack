<template>
  <div class="profile-container">
    <div class="page-header">
      <h2>My Profile</h2>
    </div>
    
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="avatar-container">
          <el-avatar :size="150" :src="avatarUrl">{{ firstLetter }}</el-avatar>
          <el-upload
            class="avatar-uploader"
            :action="`/api/users/${userId}/avatar`"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-error="handleAvatarError"
          >
            <el-button type="primary" size="small">Change Avatar</el-button>
          </el-upload>
        </div>
      </el-col>
      
      <el-col :span="16">
        <el-tabs v-model="activeTab">
          <el-tab-pane label="Basic Info" name="basic">
            <el-form
              :model="profileForm"
              :rules="rules"
              ref="profileFormRef"
              label-width="120px"
              class="profile-form"
            >
              <el-form-item label="Username" prop="username">
                <el-input v-model="profileForm.username" />
              </el-form-item>
              <el-form-item label="Email" prop="email">
                <el-input v-model="profileForm.email" />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="updateProfile"
                  :loading="loading"
                >
                  Save Changes
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          
          <el-tab-pane label="Change Password" name="password">
            <el-form
              :model="passwordForm"
              :rules="passwordRules"
              ref="passwordFormRef"
              label-width="120px"
              class="password-form"
            >
              <el-form-item label="New Password" prop="newPassword">
                <el-input
                  v-model="passwordForm.newPassword"
                  type="password"
                  show-password
                />
              </el-form-item>
              <el-form-item label="Confirm Password" prop="confirmPassword">
                <el-input
                  v-model="passwordForm.confirmPassword"
                  type="password"
                  show-password
                />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="updatePassword"
                  :loading="passwordLoading"
                >
                  Update Password
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/store/user'
import { getUser, updatePassword as apiUpdatePassword } from '@/api/user'

const userStore = useUserStore()
const activeTab = ref('basic')
const loading = ref(false)
const passwordLoading = ref(false)
const profileFormRef = ref(null)
const passwordFormRef = ref(null)

const userId = computed(() => userStore.userId)
const firstLetter = computed(() => {
  return userStore.username ? userStore.username.substring(0, 1).toUpperCase() : '?'
})
const avatarUrl = computed(() => {
  if (userStore.avatar) {
    return `/api/uploads/${userStore.avatar}`
  }
  return ''
})

const profileForm = ref({
  username: '',
  email: ''
})

const passwordForm = ref({
  newPassword: '',
  confirmPassword: ''
})

const validateConfirmPassword = (rule, value, callback) => {
  if (value !== passwordForm.value.newPassword) {
    callback(new Error('Passwords do not match'))
  } else {
    callback()
  }
}

const rules = reactive({
  username: [
    { required: true, message: 'Please enter username', trigger: 'blur' },
    { min: 3, max: 20, message: 'Length should be 3 to 20 characters', trigger: 'blur' }
  ],
  email: [
    { required: true, message: 'Please enter email', trigger: 'blur' },
    { type: 'email', message: 'Please enter a valid email address', trigger: 'blur' }
  ]
})

const passwordRules = reactive({
  newPassword: [
    { required: true, message: 'Please enter new password', trigger: 'blur' },
    { min: 6, message: 'Password must be at least 6 characters', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: 'Please confirm password', trigger: 'blur' },
    { validator: validateConfirmPassword, trigger: 'blur' }
  ]
})

onMounted(async () => {
  try {
    const response = await getUser(userId.value)
    if (response.code === 200) {
      profileForm.value.username = response.data.username
      profileForm.value.email = response.data.email
    }
  } catch (error) {
    console.error('Error fetching user profile:', error)
    ElMessage.error('Failed to load profile')
  }
})

const updateProfile = async () => {
  if (!profileFormRef.value) return
  
  await profileFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        await userStore.updateProfile({
          username: profileForm.value.username,
          email: profileForm.value.email
        })
        ElMessage.success('Profile updated successfully')
      } catch (error) {
        console.error('Error updating profile:', error)
        ElMessage.error('Failed to update profile')
      } finally {
        loading.value = false
      }
    }
  })
}

const updatePassword = async () => {
  if (!passwordFormRef.value) return
  
  await passwordFormRef.value.validate(async (valid) => {
    if (valid) {
      passwordLoading.value = true
      try {
        const response = await apiUpdatePassword(userId.value, {
          newPassword: passwordForm.value.newPassword
        })
        if (response.code === 200) {
          ElMessage.success('Password updated successfully')
          passwordForm.value.newPassword = ''
          passwordForm.value.confirmPassword = ''
        }
      } catch (error) {
        console.error('Error updating password:', error)
        ElMessage.error('Failed to update password')
      } finally {
        passwordLoading.value = false
      }
    }
  })
}

const handleAvatarSuccess = (response) => {
  if (response.code === 200) {
    ElMessage.success('Avatar updated successfully')
    userStore.updateProfile({ avatar: response.data })
  }
}

const handleAvatarError = () => {
  ElMessage.error('Failed to upload avatar')
}
</script>

<style scoped>
.profile-container {
  padding: 20px;
}

.avatar-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
  border-radius: 8px;
  padding: 30px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.avatar-uploader {
  margin-top: 20px;
}

.profile-form,
.password-form {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-tabs {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style> 