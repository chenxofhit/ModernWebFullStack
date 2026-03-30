// 简单的Node.js服务器，用于演示GET和POST的区别
// 运行方法: node server.js
// 然后访问: http://localhost:3000/get-post-demo.html

const http = require('http');
const fs = require('fs');
const path = require('path');
const url = require('url');
const querystring = require('querystring');

const PORT = 3000;

const server = http.createServer((req, res) => {
    const parsedUrl = url.parse(req.url, true);
    const pathname = parsedUrl.pathname;

    // 设置CORS头，允许跨域
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Content-Type', 'text/html; charset=utf-8');

    // 处理API请求
    if (pathname === '/api/submit') {
        if (req.method === 'GET') {
            // GET请求：数据在URL查询参数中
            const queryData = parsedUrl.query;
            
            res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
            res.end(`
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <title>GET请求结果</title>
                    <style>
                        body { font-family: Arial, sans-serif; padding: 20px; background: #f5f5f5; }
                        .container { background: white; padding: 30px; border-radius: 10px; max-width: 600px; margin: 50px auto; }
                        h1 { color: #4CAF50; }
                        .info { background: #e8f5e9; padding: 15px; border-radius: 5px; margin: 15px 0; }
                        .data { background: #f9f9f9; padding: 10px; border-radius: 5px; margin: 10px 0; }
                        a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #2196F3; color: white; text-decoration: none; border-radius: 5px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>✅ GET请求处理成功</h1>
                        <div class="info">
                            <strong>📌 观察地址栏：</strong> 可以看到所有数据都在URL中！
                        </div>
                        <div class="data">
                            <h3>接收到的数据：</h3>
                            <p><strong>用户名：</strong> ${queryData.username || '未提供'}</p>
                            <p><strong>密码：</strong> ${queryData.password || '未提供'}</p>
                        </div>
                        <div class="info">
                            <strong>⚠️ 安全问题：</strong> 密码等敏感信息暴露在URL中，可能被记录在浏览器历史、服务器日志中！
                        </div>
                        <a href="/get-post-demo.html">返回演示页面</a>
                    </div>
                </body>
                </html>
            `);
        } else if (req.method === 'POST') {
            // POST请求：数据在请求体中
            let body = '';
            
            req.on('data', chunk => {
                body += chunk.toString();
            });
            
            req.on('end', () => {
                const postData = querystring.parse(body);
                
                res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
                res.end(`
                    <!DOCTYPE html>
                    <html lang="zh-CN">
                    <head>
                        <meta charset="UTF-8">
                        <title>POST请求结果</title>
                        <style>
                            body { font-family: Arial, sans-serif; padding: 20px; background: #f5f5f5; }
                            .container { background: white; padding: 30px; border-radius: 10px; max-width: 600px; margin: 50px auto; }
                            h1 { color: #2196F3; }
                            .info { background: #e3f2fd; padding: 15px; border-radius: 5px; margin: 15px 0; }
                            .data { background: #f9f9f9; padding: 10px; border-radius: 5px; margin: 10px 0; }
                            a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #4CAF50; color: white; text-decoration: none; border-radius: 5px; }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h1>✅ POST请求处理成功</h1>
                            <div class="info">
                                <strong>📌 观察地址栏：</strong> URL中没有数据，数据在请求体中！
                            </div>
                            <div class="data">
                                <h3>接收到的数据：</h3>
                                <p><strong>用户名：</strong> ${postData.username || '未提供'}</p>
                                <p><strong>密码：</strong> ${postData.password || '未提供'}</p>
                            </div>
                            <div class="info">
                                <strong>✅ 优点：</strong> 敏感信息不会暴露在URL中，更安全！
                            </div>
                            <a href="/get-post-demo.html">返回演示页面</a>
                        </div>
                    </body>
                    </html>
                `);
            });
        }
    } else {
        // 处理静态文件请求
        let filePath = '.' + pathname;
        if (pathname === '/') {
            filePath = './get-post-demo.html';
        }

        // 确保文件路径安全
        filePath = path.normalize(filePath);
        
        fs.readFile(filePath, (err, data) => {
            if (err) {
                res.writeHead(404, { 'Content-Type': 'text/html; charset=utf-8' });
                res.end('<h1>404 - 文件未找到</h1>');
            } else {
                const ext = path.extname(filePath);
                let contentType = 'text/html';
                
                if (ext === '.js') contentType = 'text/javascript';
                else if (ext === '.css') contentType = 'text/css';
                
                res.writeHead(200, { 'Content-Type': contentType + '; charset=utf-8' });
                res.end(data);
            }
        });
    }
});

server.listen(PORT, () => {
    console.log(`🚀 服务器运行在 http://localhost:${PORT}`);
    console.log(`📝 访问演示页面: http://localhost:${PORT}/get-post-demo.html`);
    console.log(`\n💡 提示：`);
    console.log(`   - GET请求：数据会显示在URL中`);
    console.log(`   - POST请求：数据在请求体中，URL中看不到`);
});

