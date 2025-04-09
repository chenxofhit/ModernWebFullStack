<template>
    <div class="todo-footer" v-show="total">
        <span>
            <span>已完成{{doneTotal}}</span> / 全部{{total}}
        </span>
        <el-button  type="info" round style="margin-left:8px;" @click="clearCompleted">清除已完成任务</el-button>
    </div>
</template>

<script >
export default {
    name:'TodoFooter',
    props:['todos'], 
    emits:['clear'],
    computed: {
        doneTotal(){
            let total=0;
            this.todos.forEach((todo)=>{
                if(todo.completed){
                  total++;
                }
            } );
            return total; 
        },
        total(){
            return this.todos.length;
        }       
    },
    methods:{
        clearCompleted(){
            this.$emit('clear')              
        }
    }
}
</script>

<style scoped>
.todo-footer {
  height: 40px;
  line-height: 40px;
  padding-left: 6px;
  margin-top: 5px;
}


</style>