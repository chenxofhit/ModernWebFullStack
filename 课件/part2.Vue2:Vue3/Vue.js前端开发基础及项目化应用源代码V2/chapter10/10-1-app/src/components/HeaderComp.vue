<template>
    <div id="container">
        <div class="top">
            <div class="wrap">
                <div>
                    <img class="magintop_20 floatLeft logo" src="../assets/img/logo.png" />
                </div>
                <div class="login floatRight magintop_20">
                    <div class="floatLeft">
                        <ul>
                            <li><a href="#">注册</a><span>/</span>
                                <a href="#">登录</a>							
                            <!-- <el-button type="danger" link>登录</el-button> -->
                            </li>
                        </ul>							
                    </div>						
                </div>                                 
            </div>
        </div>                
    </div>    
</template>

<script>
export default {
  name: 'HeaderComp'
}
</script>

<style scoped>
.top {
	width: 100%;
	height: 80px;	
	background-color: rgb(218,210,199);
	overflow: hidden;
}
.wrap {
	width: 1000px;
	margin: 0 auto;
}
.logo {
	width: 100px;
	height: 48px;
}
.magintop_20 {
	margin-top: 20px;
}
.floatRight {
	float: right;
}

.floatLeft {
	float: left;
}
.login ul{
	list-style: none;
}
.login a,.login a:link,.login a:visited,.login span{
	line-height: 35px;
	font-size: 0.8em;
	color: #A58241;
	text-decoration: none;
}
ul{
	list-style: none;		
}
ul li{				
	margin:1px 0;
}
</style>