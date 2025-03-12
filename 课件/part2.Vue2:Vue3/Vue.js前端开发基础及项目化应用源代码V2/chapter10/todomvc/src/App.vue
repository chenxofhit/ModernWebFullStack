<template>
  <div class="container">
    <todo-header @add="addTodos"></todo-header>
    <todo-list :todos="todos" @checkedChange="checkedChange" @del="handleDelete"></todo-list>
    <todo-footer :todos="todos" @clear="clearAll"></todo-footer>
  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
import TodoHeader from './components/TodoHeader.vue';
import TodoList from './components/TodoList.vue';
import TodoFooter from './components/TodoFooter.vue';

export default {
  name: 'App',
  components: {
    TodoHeader,
    TodoList,
    TodoFooter
  },
  data(){
    return{
      todos:[]
    }
  },
  methods:{
    addTodos(value){
      this.todos.push({
        id: Date.now(),
        title: value,
        completed: false
      })
      
    },
    checkedChange(id){
      this.todos.forEach((todo)=>{
          if(todo.id==id){
            todo.completed =!todo.completed; //赋值取反操作
          }
      });
      //this.todos = t;
      
    },
    handleDelete(todo){  
      //利用indexOf获取todos元素todo的位置
      //利用splice删除从todo所在位置开始的1个元素    
      this.todos.splice(this.todos.indexOf(todo), 1)
    },
    clearAll(){
      console.log('sfssf')
      this.todos = this.todos.filter((todo)=>{
            return !todo.completed;
      });
      console.log(this.todos)
    }
  }
}
</script>

<style>
.container {
  width: 600px;
  margin: 0 auto;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}
</style>
