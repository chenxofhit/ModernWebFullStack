<template>
  <div class="dashboard">
    <el-container>
      <el-aside width="200px">
        <div class="logo">Mini Project</div>
        <el-menu
          :router="true"
          :default-active="activeMenu"
          class="menu"
          background-color="#304156"
          text-color="#bfcbd9"
          active-text-color="#409EFF"
        >
          <el-menu-item index="/dashboard/tasks">
            <el-icon><Document /></el-icon>
            <span>Tasks</span>
          </el-menu-item>
          <el-menu-item index="/dashboard/profile">
            <el-icon><User /></el-icon>
            <span>Profile</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <div class="header-right">
            <span class="username">{{ username }}</span>
            <el-dropdown @command="handleCommand">
              <span class="avatar-dropdown">
                <el-avatar :size="30" :src="avatarUrl">{{ username.substring(0, 1).toUpperCase() }}</el-avatar>
                <el-icon><ArrowDown /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item command="profile">Profile</el-dropdown-item>
                  <el-dropdown-item command="logout">Logout</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ArrowDown, Document, User } from '@element-plus/icons-vue'
import { useUserStore } from '@/store/user'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const username = computed(() => userStore.username)
const avatarUrl = computed(() => {
  if (userStore.avatar) {
    return `/api/uploads/${userStore.avatar}`
  }
  return ''
})

const activeMenu = computed(() => route.path)

const handleCommand = (command) => {
  if (command === 'logout') {
    userStore.logout()
    router.push('/login')
  } else if (command === 'profile') {
    router.push('/dashboard/profile')
  }
}
</script>

<style scoped>
.dashboard {
  height: 100vh;
}

.el-aside {
  background-color: #304156;
  color: #bfcbd9;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  border-bottom: 1px solid #1f2d3d;
}

.menu {
  border-right: none;
}

.el-header {
  background-color: #fff;
  color: #303133;
  line-height: 60px;
  border-bottom: 1px solid #e6e6e6;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.header-right {
  display: flex;
  align-items: center;
}

.username {
  margin-right: 10px;
}

.avatar-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.avatar-dropdown .el-icon {
  margin-left: 5px;
}

.el-main {
  background-color: #f0f2f5;
  padding: 20px;
}
</style> 