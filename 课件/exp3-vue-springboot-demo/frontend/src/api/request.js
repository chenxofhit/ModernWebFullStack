import axios from 'axios'
import { ElMessage } from 'element-plus'

// Create an axios instance
const request = axios.create({
  baseURL: '/api', // Using Vite proxy
  timeout: 10000
})

// Request interceptor
request.interceptors.request.use(
  config => {
    // Add custom headers if needed
    return config
  },
  error => {
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// Response interceptor
request.interceptors.response.use(
  response => {
    const res = response.data
    
    if (res.code !== 200) {
      ElMessage.error(res.message || 'Error')
      
      // Handle unauthorized (401)
      if (res.code === 401) {
        // Redirect to login
        localStorage.removeItem('user')
        window.location.href = '/login'
      }
      
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.error('Response error:', error)
    ElMessage.error(error.message || 'Request failed')
    return Promise.reject(error)
  }
)

export default request 