<template>
    <div> 
        <ul v-if="todos.length">
            <li v-for="(item,index) in todos" :key="item.id">            
                <label>
                    <el-checkbox :checked="item.completed" @change="handleCheck(item.id)" />                
                    <span>{{item.title}}</span>
                </label>                
                <el-button type="primary" @click="deleteTodo(item)">删除</el-button>
            </li>            
        </ul>
    </div>
</template>

<script >
export default {
    name:'TodoList',
    props:['todos'],   
    emits:['checkedChange','del'],
    data(){
        return{
            
        }
    },
    methods:{
        handleCheck(id){       
          this.$emit('checkedChange',id)
        },
        deleteTodo(item){
            this.$emit('del',item)
        }
    
    }
}
</script>

<style scoped>
ul {
    margin-left: 0px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0px;
}
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li span{
    margin-left: 5px;
}
/* li label {
  float: left;
  cursor: pointer;
} */

/* li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
} */

li button {
  float: right;
  /* display: none;   */
  margin-top: 3px;
}

</style>