<template>
    <div>
        <el-input v-model="input" placeholder="请输入任务名称，按回车键确认" @keyup.enter="addTodos" />
    </div>
</template>

<script >
export default {
    name:'TodoHeader',
    props:{

    },
    emits:['add'],
    data(){
        return{
            input:''
        }
    },
    methods:{
        addTodos(){
            if(this.input.trim()==""){
                return  alert("输入内容不能为空"); 
            }
            this.$emit('add',this.input)
            this.input = ''
        }
    }
}
</script>

<style scoped>

</style>