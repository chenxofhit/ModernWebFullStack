import { defineStore } from 'pinia'
import { login, register, updateUser } from '@/api/user'

export const useUserStore = defineStore('user', {
  state: () => ({
    currentUser: JSON.parse(localStorage.getItem('user')) || null,
    loading: false,
    error: null
  }),
  
  getters: {
    isLoggedIn: (state) => !!state.currentUser,
    username: (state) => state.currentUser?.username || '',
    userId: (state) => state.currentUser?.id || null,
    avatar: (state) => state.currentUser?.avatar || ''
  },
  
  actions: {
    async login(credentials) {
      this.loading = true
      this.error = null
      try {
        const response = await login(credentials)
        this.currentUser = response.data
        localStorage.setItem('user', JSON.stringify(response.data))
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Login failed'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async register(userData) {
      this.loading = true
      this.error = null
      try {
        const response = await register(userData)
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Registration failed'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async updateProfile(userData) {
      this.loading = true
      this.error = null
      try {
        const response = await updateUser(this.userId, userData)
        if (response.code === 200) {
          this.currentUser = { ...this.currentUser, ...userData }
          localStorage.setItem('user', JSON.stringify(this.currentUser))
        }
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Update failed'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    logout() {
      this.currentUser = null
      localStorage.removeItem('user')
    }
  }
}) 