var http = require('http');

http.createServer(function (req, res) {
  res.writeHead(200,{
    "Content-Type": "text/xml;charset=utf-8",
    "Access-Control-Allow-Origin": "*"
  });

  let xml = "<p id='kk'>p标记<span>内套span标记</span></p><span>单独的span标记</span>";

  res.write(xml);
  res.end();
 
}).listen(1337,'localhost', function(){
  console.log('开始监听~')
});