import { defineStore } from 'pinia'
import { getTasks, getTasksByUserId, createTask, updateTask, deleteTask } from '@/api/task'

export const useTaskStore = defineStore('task', {
  state: () => ({
    tasks: [],
    currentTask: null,
    loading: false,
    error: null
  }),
  
  getters: {
    todoTasks: (state) => state.tasks.filter(task => task.status === 'TODO'),
    inProgressTasks: (state) => state.tasks.filter(task => task.status === 'IN_PROGRESS'),
    doneTasks: (state) => state.tasks.filter(task => task.status === 'DONE')
  },
  
  actions: {
    async fetchAllTasks() {
      this.loading = true
      this.error = null
      try {
        const response = await getTasks()
        this.tasks = response.data || []
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Failed to fetch tasks'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async fetchUserTasks(userId) {
      this.loading = true
      this.error = null
      try {
        const response = await getTasksByUserId(userId)
        this.tasks = response.data || []
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Failed to fetch tasks'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async addTask(taskData) {
      this.loading = true
      this.error = null
      try {
        const response = await createTask(taskData)
        if (response.code === 200) {
          await this.fetchUserTasks(taskData.userId)
        }
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Failed to add task'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async updateTask(id, taskData) {
      this.loading = true
      this.error = null
      try {
        const response = await updateTask(id, taskData)
        if (response.code === 200) {
          const index = this.tasks.findIndex(task => task.id === id)
          if (index !== -1) {
            this.tasks[index] = { ...this.tasks[index], ...taskData }
          }
        }
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Failed to update task'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    },
    
    async removeTask(id) {
      this.loading = true
      this.error = null
      try {
        const response = await deleteTask(id)
        if (response.code === 200) {
          this.tasks = this.tasks.filter(task => task.id !== id)
        }
        return Promise.resolve(response)
      } catch (error) {
        this.error = error.message || 'Failed to delete task'
        return Promise.reject(error)
      } finally {
        this.loading = false
      }
    }
  }
}) 