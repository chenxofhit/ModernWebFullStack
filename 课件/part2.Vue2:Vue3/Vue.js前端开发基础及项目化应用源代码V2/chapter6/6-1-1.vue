<template>
    <div>
        <h3>显示用户名信息</h3>
        <button @click="showUser">显示</button>
        <p>{{userName}}</p>
    </div>
    <br/>
    <div>
        <h3>根据手机产品名搜索其价格</h3>
        <input type="text" name="keyword" v-model="productName"/><button @click="getProductPrice">搜索</button>
        <p v-if="productName!=''">{{productName}}: {{productPrice}}</p>
    </div>
</template>           
<script>
    export default{
        name:'OptionsDemo',
        data() {
            return {
                userName:'',//功能1
                productName:'',//功能2
                productPrice: 0//功能2
            }
        },
        methods:{
            showUser(){//功能1
               this.userName = '张三' 
            },
            getProductPrice(){//功能2 
                if(this.productName != '') {
                    this.productPrice = 2000
                }                
            }
        }
        
    }    
</script>
