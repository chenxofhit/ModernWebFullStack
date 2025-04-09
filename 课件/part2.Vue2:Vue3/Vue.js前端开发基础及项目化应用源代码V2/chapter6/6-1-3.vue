<!--ComponentAPI应用（二）-->
<template>
    <div>
        <h3>显示用户信息</h3>
        <button @click="showUser">显示</button>
        <p>{{userName}}</p>
    </div>
    <br/>
    <div>
        <h3>搜索手机产品的价格</h3>
        <input type="text" name="keyword" v-model="productName"/><button @click="getPrice">搜索</button>
        <p v-if="productName!=''">{{productName}}: {{productPrice}}</p>
    </div>
</template> 
<script>
    import {ref} from 'vue'

    function useUser(){//功能1
        const userName = ref('')
        const showUser = function(){
            userName.value = '张三' 
        }
        return {
            userName,
            showUser 
        }    
    }
    
    function useProduct(){//功能2
        let productName = ref('')
        let productPrice = ref(0)
        const getProductPrice = function(){ 
            if(productName.value != '') {
                productPrice.value = 2000
            }                
        }
        return {
            productName,
            productPrice,
            getProductPrice
        }
    }
    export default{
        name:'CompositionsDemo-2',
        setup(){
            /**功能1**/
            const {userName, showUser} = useUser()            

            /**功能2**/
            const {productName, productPrice, getProductPrice } = useProduct()

            return {
                userName,
                showUser,
                productName,
                productPrice,
                getProductPrice
            }            
        }
    }
</script>