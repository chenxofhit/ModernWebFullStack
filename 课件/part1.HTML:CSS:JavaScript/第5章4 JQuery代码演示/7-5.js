const http = require('http');
const url = require('url')
const querystring = require('querystring')

var path = require('path'); //系统路径模块
const fs = require('fs');

http.createServer((req, res) => {
  let pathname1, getData, postData
  if (req.method == 'GET') {
    let {pathname, query} = url.parse(req.url, true) 
    pathname1 = pathname,
    getData = query
    complete()
  } else if(req.method == 'POST') {
    let arr = []
    req.on('data', buffer => {
      arr.push(buffer)
    })
    req.on('end', ()=> {
      postData = querystring.parse(Buffer.concat(arr).toString())
    })
    complete()
  }

  function complete() {
    console.log(pathname1)
    console.log(req.url)
    if (req.url == '/test') {
      res.writeHead(200,{
        "Content-Type": "text/plain;charset=utf-8",
      });
      let {username, birthday} = getData
      res.end(username + ', 你的生日是' + birthday)
    } else if (req.url == '/test-post') {
      res.writeHead(200,{
        "Content-Type": "text/plain;charset=utf-8",
      });
      console.log(postData)
      let {username, birthday} = postData
      res.end(username + ', 你的生日是' + birthday)
    } else {
      // fs.readFile(path.join(__dirname, pathname), (err, data) => {
      fs.readFile(`./${pathname1}`, (err, data) => {
        console.log(err)
        if (err)
          res.end('文件读取失败')
        else 
          res.end(data)
      })
    }
  }

  // if (req.url == '/test')
  //   if (req.method == 'GET')
  //     res.write('GET:' + req.url)
  //   if (req.method == 'POST') 
  //     res.write('POST:' + req.url)
  // else 
  //   // var file = path.join(__dirname, 'demo.json'); //文件路径，__dirname为当前运行js文件的目录
  //   fs.readFile('./demo.json', (err, data) => {
  //     if (err)
  //       res.end('文件读取失败')
  //     else 
  //       res.end(data)

  //   })
  // res.end();
 
}).listen(8081,'localhost', function(){
  console.log('开始监听~')
});