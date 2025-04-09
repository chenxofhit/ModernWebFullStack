var http = require('http');

http.createServer(function (req, res) {
  res.writeHead(200,{
    "Content-Type": "text/plain;charset=utf-8",
    "Access-Control-Allow-Origin": "*"
  });
  
  res.write('异步测试成功，很高兴！！！！');
  res.end();
 
}).listen(1337,'localhost', function(){
  console.log('开始监听~')
});