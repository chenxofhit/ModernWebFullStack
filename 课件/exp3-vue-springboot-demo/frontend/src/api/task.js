import request from './request'

/**
 * Get all tasks
 * @returns {Promise}
 */
export function getTasks() {
  return request({
    url: '/tasks',
    method: 'get'
  })
}

/**
 * Get task by ID
 * @param {Number} id - Task ID
 * @returns {Promise}
 */
export function getTaskById(id) {
  return request({
    url: `/tasks/${id}`,
    method: 'get'
  })
}

/**
 * Get tasks by user ID
 * @param {Number} userId - User ID
 * @returns {Promise}
 */
export function getTasksByUserId(userId) {
  return request({
    url: `/tasks/user/${userId}`,
    method: 'get'
  })
}

/**
 * Create a new task
 * @param {Object} data - Task data
 * @returns {Promise}
 */
export function createTask(data) {
  return request({
    url: '/tasks',
    method: 'post',
    data
  })
}

/**
 * Update a task
 * @param {Number} id - Task ID
 * @param {Object} data - Task data to update
 * @returns {Promise}
 */
export function updateTask(id, data) {
  return request({
    url: `/tasks/${id}`,
    method: 'put',
    data
  })
}

/**
 * Delete a task
 * @param {Number} id - Task ID
 * @returns {Promise}
 */
export function deleteTask(id) {
  return request({
    url: `/tasks/${id}`,
    method: 'delete'
  })
}

/**
 * Upload task attachment
 * @param {Number} id - Task ID
 * @param {FormData} formData - Form data with file
 * @returns {Promise}
 */
export function uploadAttachment(id, formData) {
  return request({
    url: `/tasks/${id}/attachment`,
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
} 