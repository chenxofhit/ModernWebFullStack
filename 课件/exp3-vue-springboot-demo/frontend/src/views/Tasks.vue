<template>
  <div class="tasks-container">
    <div class="page-header">
      <h2>Task Management</h2>
      <el-button type="primary" @click="openAddTaskDialog">Add Task</el-button>
    </div>
    
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="task-column">
          <h3 class="task-column-title">To Do</h3>
          <div v-if="todoTasks.length === 0" class="empty-tasks">No tasks</div>
          <div v-for="task in todoTasks" :key="task.id" class="task-card">
            <h4>{{ task.title }}</h4>
            <p>{{ task.description }}</p>
            <div class="task-actions">
              <el-button size="small" @click="startTask(task)">Start</el-button>
              <el-button size="small" type="danger" @click="confirmDelete(task)">
                Delete
              </el-button>
              <el-button size="small" @click="openEditTaskDialog(task)">Edit</el-button>
            </div>
            <div v-if="task.attachment" class="task-attachment">
              <a :href="`/api/uploads/${task.attachment}`" target="_blank">
                Attachment
              </a>
            </div>
          </div>
        </div>
      </el-col>
      
      <el-col :span="8">
        <div class="task-column">
          <h3 class="task-column-title">In Progress</h3>
          <div v-if="inProgressTasks.length === 0" class="empty-tasks">No tasks</div>
          <div v-for="task in inProgressTasks" :key="task.id" class="task-card">
            <h4>{{ task.title }}</h4>
            <p>{{ task.description }}</p>
            <div class="task-actions">
              <el-button size="small" type="success" @click="completeTask(task)">
                Complete
              </el-button>
              <el-button size="small" @click="openEditTaskDialog(task)">Edit</el-button>
            </div>
            <div v-if="task.attachment" class="task-attachment">
              <a :href="`/api/uploads/${task.attachment}`" target="_blank">
                Attachment
              </a>
            </div>
          </div>
        </div>
      </el-col>
      
      <el-col :span="8">
        <div class="task-column">
          <h3 class="task-column-title">Done</h3>
          <div v-if="doneTasks.length === 0" class="empty-tasks">No tasks</div>
          <div v-for="task in doneTasks" :key="task.id" class="task-card">
            <h4>{{ task.title }}</h4>
            <p>{{ task.description }}</p>
            <div class="task-actions">
              <el-button size="small" type="danger" @click="confirmDelete(task)">
                Delete
              </el-button>
            </div>
            <div v-if="task.attachment" class="task-attachment">
              <a :href="`/api/uploads/${task.attachment}`" target="_blank">
                Attachment
              </a>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    
    <!-- Add/Edit Task Dialog -->
    <el-dialog
      v-model="taskDialogVisible"
      :title="isEditing ? 'Edit Task' : 'Add Task'"
      width="500px"
    >
      <el-form :model="taskForm" :rules="rules" ref="taskFormRef" label-width="100px">
        <el-form-item label="Title" prop="title">
          <el-input v-model="taskForm.title" />
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="taskForm.description" type="textarea" rows="4" />
        </el-form-item>
        <el-form-item label="Status" prop="status">
          <el-select v-model="taskForm.status" placeholder="Select status">
            <el-option label="To Do" value="TODO" />
            <el-option label="In Progress" value="IN_PROGRESS" />
            <el-option label="Done" value="DONE" />
          </el-select>
        </el-form-item>
        <el-form-item label="Attachment">
          <el-upload
            :action="`/api/tasks/${taskForm.id}/attachment`"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            :file-list="fileList"
            :auto-upload="false"
            ref="uploadRef"
          >
            <el-button type="primary">Select File</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="taskDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="saveTask">Save</el-button>
        </span>
      </template>
    </el-dialog>
    
    <!-- Delete Confirmation Dialog -->
    <el-dialog
      v-model="deleteDialogVisible"
      title="Confirm Delete"
      width="400px"
    >
      <p>Are you sure you want to delete this task?</p>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="deleteDialogVisible = false">Cancel</el-button>
          <el-button type="danger" @click="deleteTask">Delete</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useTaskStore } from '@/store/task'
import { useUserStore } from '@/store/user'
import { uploadAttachment } from '@/api/task'

const taskStore = useTaskStore()
const userStore = useUserStore()

const todoTasks = computed(() => taskStore.todoTasks)
const inProgressTasks = computed(() => taskStore.inProgressTasks)
const doneTasks = computed(() => taskStore.doneTasks)

const taskDialogVisible = ref(false)
const deleteDialogVisible = ref(false)
const isEditing = ref(false)
const taskFormRef = ref(null)
const uploadRef = ref(null)
const fileList = ref([])
const taskToDelete = ref(null)

const taskForm = ref({
  id: null,
  title: '',
  description: '',
  status: 'TODO',
  userId: userStore.userId,
  attachment: null
})

const rules = reactive({
  title: [{ required: true, message: 'Please enter title', trigger: 'blur' }],
  status: [{ required: true, message: 'Please select status', trigger: 'change' }]
})

onMounted(async () => {
  await taskStore.fetchUserTasks(userStore.userId)
})

const openAddTaskDialog = () => {
  isEditing.value = false
  taskForm.value = {
    id: null,
    title: '',
    description: '',
    status: 'TODO',
    userId: userStore.userId,
    attachment: null
  }
  fileList.value = []
  taskDialogVisible.value = true
}

const openEditTaskDialog = (task) => {
  isEditing.value = true
  taskForm.value = { ...task }
  fileList.value = task.attachment ? [{ name: task.attachment, url: `/api/uploads/${task.attachment}` }] : []
  taskDialogVisible.value = true
}

const saveTask = async () => {
  if (!taskFormRef.value) return
  
  await taskFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (isEditing.value) {
          await taskStore.updateTask(taskForm.value.id, taskForm.value)
          ElMessage.success('Task updated successfully')
        } else {
          await taskStore.addTask(taskForm.value)
          ElMessage.success('Task added successfully')
        }
        
        // Handle file upload if there's a file selected
        if (uploadRef.value && uploadRef.value.uploadFiles.length > 0) {
          uploadRef.value.submit()
        }
        
        taskDialogVisible.value = false
      } catch (error) {
        console.error('Error saving task:', error)
        ElMessage.error('Failed to save task')
      }
    }
  })
}

const startTask = async (task) => {
  try {
    await taskStore.updateTask(task.id, { ...task, status: 'IN_PROGRESS' })
    ElMessage.success('Task started')
  } catch (error) {
    console.error('Error starting task:', error)
    ElMessage.error('Failed to start task')
  }
}

const completeTask = async (task) => {
  try {
    await taskStore.updateTask(task.id, { ...task, status: 'DONE' })
    ElMessage.success('Task completed')
  } catch (error) {
    console.error('Error completing task:', error)
    ElMessage.error('Failed to complete task')
  }
}

const confirmDelete = (task) => {
  taskToDelete.value = task
  deleteDialogVisible.value = true
}

const deleteTask = async () => {
  try {
    await taskStore.removeTask(taskToDelete.value.id)
    ElMessage.success('Task deleted')
    deleteDialogVisible.value = false
  } catch (error) {
    console.error('Error deleting task:', error)
    ElMessage.error('Failed to delete task')
  }
}

const handleUploadSuccess = (response) => {
  ElMessage.success('File uploaded successfully')
  taskForm.value.attachment = response.data
}

const handleUploadError = () => {
  ElMessage.error('File upload failed')
}
</script>

<style scoped>
.tasks-container {
  padding: 20px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.task-column {
  background-color: #f9f9f9;
  border-radius: 4px;
  padding: 15px;
  height: calc(100vh - 180px);
  overflow-y: auto;
}

.task-column-title {
  margin-top: 0;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ddd;
  color: #606266;
}

.task-card {
  background-color: #fff;
  border-radius: 4px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.task-card h4 {
  margin-top: 0;
  margin-bottom: 10px;
}

.task-card p {
  margin-bottom: 15px;
  color: #606266;
}

.task-actions {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}

.empty-tasks {
  text-align: center;
  padding: 30px 0;
  color: #909399;
  font-style: italic;
}

.task-attachment {
  margin-top: 10px;
  font-size: 0.9em;
}

.task-attachment a {
  color: var(--primary-color);
  text-decoration: none;
}
</style> 