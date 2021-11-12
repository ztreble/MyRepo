## 同步连接
boost::asio::io_constext io_context表示程序与操作系统IO服务的连接
通过io对象初始化socket
```c++
boost::asio::io_context io_context;
boost::asio::ip::tcp::socket socket(io_context);
//启动连接,iocontext转发
socket.connect(server_endpoint);

```