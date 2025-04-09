import request from './request'

/**
 * User login
 * @param {Object} data - Login credentials
 * @returns {Promise}
 */
export function login(data) {
  return request({
    url: '/users/login',
    method: 'post',
    data
  })
}

/**
 * User registration
 * @param {Object} data - User data
 * @returns {Promise}
 */
export function register(data) {
  return request({
    url: '/users/register',
    method: 'post',
    data
  })
}

/**
 * Get user by ID
 * @param {Number} id - User ID
 * @returns {Promise}
 */
export function getUser(id) {
  return request({
    url: `/users/${id}`,
    method: 'get'
  })
}

/**
 * Update user information
 * @param {Number} id - User ID
 * @param {Object} data - User data to update
 * @returns {Promise}
 */
export function updateUser(id, data) {
  return request({
    url: `/users/${id}`,
    method: 'put',
    data
  })
}

/**
 * Update user password
 * @param {Number} id - User ID
 * @param {Object} data - Password data
 * @returns {Promise}
 */
export function updatePassword(id, data) {
  return request({
    url: `/users/${id}/password`,
    method: 'put',
    data
  })
}

/**
 * Upload user avatar
 * @param {Number} id - User ID
 * @param {FormData} formData - Form data with file
 * @returns {Promise}
 */
export function uploadAvatar(id, formData) {
  return request({
    url: `/users/${id}/avatar`,
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
} 