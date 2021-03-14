# MyRepo
个人库，存放做的玩具
## 项目目录
#### BuddhaServer
基于Reactor模式使用C++11搭建的TCP服务器，能够处理Get/Post/Head请求。使用Epoll边沿触发和Eventfd构建了高效的事件模式，利用Functor、Bind和std::Function实现事件回调并降低锁粒度。同时基于线程池实现One Loop One Thread，在IO线程中使用自动状态机解析HTTP请求，支持长连接。使用基于小根堆的定时器和事件循环关闭超时请求。使用WebBench和Valgrind等工具进行了压测和优化。
#### compiler
编译器前端和一个lisp-parser，已经施工完成词法、语法分析器
#### jotes
Java教学评价管理系统，QT前端SSM后端
### missile
QT无人机作战仿真模拟
### our-leetcode
SSM+nginx 仿力扣网站
### curriculum-design
来自世界各地的大学课设
#### EECS2030
练习递归的Java画图小程序
#### yangzhou-university-os-design
扬州大学os课设，质量值得称道
### build-cpu
logisim搭建单周期Mips-cpu
文件过大，链接： https://github.com/ztreble/build-a-cpu
### 游戏和工具
#### tools-whois-suspect
谁是嫌疑犯？C++做的Linux小游戏
#### tools-octathlon
奥林匹克比赛模拟，C++小游戏
#### tools-compare-tool
用于高精度时间对比的工具
#### go-tool
golang并发服务器和爬虫小demo