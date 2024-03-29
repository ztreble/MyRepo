# TCP/IP 相关的问题

### 目录

---
<a href="#1">1. OSI 与 TCP/IP 各层的结构与功能,都有哪些协议,协议所占端口号。</a> <br>
<a href="#2">2. IP 地址的分类。</a> <br>
<a href="#3">3. 画出三次握手和四次挥手的图(状态转移图)。TCP 为什么三次握手,四次挥手?</a> <br>
<a href="#4">4. 为什么收到 Server 端的确认之后,Client 还需要进行第三次“握手”呢?</a> <br>
<a href="#5">5. 为什么要 4 次挥手</a> <br>
<a href="#6">6. 建立连接的第二个 syn 作用是啥? time_wait 状态产生的原因?</a> <br>
<a href="#7">7. 如果网络连接中出现大量 TIME_WAIT 状态所带来的危害?</a> <br>
<a href="#8">8. 如何消除大量 TCP 短连接引发的 TIME_WAIT?</a> <br>
<a href="#9">9. TIME_WAIT 的时间? 当关闭连接时最后一个 ACK 丢失怎么办?</a> <br>
<a href="#10">10. TCP如何保证可靠传输</a> <br>
<a href="#11">11. TCP 建立连接之后怎么保持连接(检测连接断没断)?</a> <br>
<a href="#12">12. TCP 三次握手有哪些漏洞?</a> <br>
<a href="#13">13. TCP 存在的缺陷有哪些?</a> <br>
<a href="#14">14. 三次握手与 accept()函数的关系?</a> <br>
<a href="#15">15. 在三次握手和四次挥手协议中,客户端和服务器端各用到什么函数。</a> <br>
<a href="#16">16. listen 的真正目的?</a> <br>
<a href="#17">17. 如果客户端发起握手请求,服务端无法立刻建立连接应该回应什么?</a> <br>
<a href="#18">18. TCP 与 UDP 的区别(或各自的优缺点),以及各自的用途和使用领域。</a> <br>
<a href="#19">19. 为什么 TCP 比 UDP 安全,但是还有很多用 UDP?</a> <br>
<a href="#20">20. UDP 为何快?</a> <br>
<a href="#21">21. TCP 如何实现流量控制和拥塞控制。tcp 是怎么做错误处理的?</a> <br>
<a href="#22">22. TCP 滑动窗口协议,窗口过大或过小有什么影响?</a> <br>
<a href="#23">23. 在流量控制的过程中,必须考虑传输效率。</a> <br>
<a href="#24">24. 说下 TCP 的黏包 ?</a> <br>
<a href="#25">25. TCP 头部有哪些字段?</a> <br>
<a href="#26">26. UDP 的首部多长,具体包含哪些字段?</a> <br>


### <a name="1">1. 优化查询的方法？</a>
#### 一.OSI 与 TCP/IP 各层的结构与功能,都有哪些协议,协议所占端口号。
1. 物理层----定义了为建立、维护和拆除物理链路所需的机械的、电气的、
功能的和规程的特性,其作用是使原始的数据比特流能在物理媒体上传输。具
体涉及接插件的规格、“0”、“1”信号的电平表示、收发双方的协调等内容。
2. 数据链路层----比特流被组织成数据链路协议数据单元(通常称为帧),并
以其为单位进行传输,帧中包含地址、控制、数据及校验码等信息。数据链路
层的主要作用是通过校验、确认和反馈重发等手段,*将不可靠的物理链路改造
成对网络层来说无差错的数据链路*。数据链路层还要协调收发双方的数据传输
速率,即进行*流量控制*,以防止接收方因来不及处理发送方来的高速数据而导
致缓冲器溢出及线路阻塞。
3. 网络层----数据以网络协议数据单元(分组)为单位进行传输。网络层关心
的是通信子网的运行控制,主要解决如何使数据分组跨越通信子网从源传送到
目的地的问题,这就需要在通信子网中进行*路由选择*。另外,为避免通信子网
中出现过多的分组而造成网络阻塞,需要对流入的分组数量进行控制。当分组
要跨越多个通信子网才能到达目的地时,还要解决网际互连的问题。
4. 传输层----是第一个端--端,也即主机--主机的层次。传输层提供的端到端
的透明数据运输服务,使高层用户不必关心通信子网的存在,由此用统一的运
输原语书写的高层软件便可运行于任何通信子网上。传输层还要处理端到端的
差错控制和流量控制问题。
5. 会话层----是进程--进程的层次,其主要功能是组织和同步不同的主机上
各种进程间的通信(也称为对话)。会话层负责在两个会话层实体之间进行对话连
接的建立和拆除。在半双工情况下,会话层提供一种数据权标来控制某一方何
时有权发送数据。会话层还提供在数据流中插入同步点的机制,使得数据传输
因网络故障而中断后,可以不必从头开始而仅重传最近一个同步点以后的数据。
6. 表示层----为上层用户提供共同的数据或信息的语法表示变换。为了让采
用不同编码方法的计算机在通信中能相互理解数据的内容,可以采用抽象的标
准方法来定义数据结构,并采用标准的编码表示形式。表示层管理这些抽象的
数据结构,并将计算机内部的表示形式转换成网络通信中采用的标准表示形式。
数据压缩和加密也是表示层可提供的表示变换功能。
7. 应用层是开放系统互连环境的最高层。不同的应用层为特定类型的网络
应用提供访问 OSI 环境的手段。网络环境下不同主机间的文件传送访问和管理
(FTAM)、传送标准电子邮件的文电处理系统(MHS)、使不同类型的终端和主机通
过网络交互访问的虚拟终端(VT)协议等都属于应用层的范畴。

##### 每层的协议：
- 物理层:RJ45、CLOCK、IEEE802.3 
- 数据链路:PPP、FR、HDLC、VLAN、MAC
- 网络层:IP、ICMP、ARP、RARP、OSPF、IPX、RIP、IGMP
- 传输层:TCP、UDP、SPX
- 会话层:RPC、SQL、NETBIOS、NFS
- 表示层:JPEG、MPEG、ASCII、MIDI
- 应用层:RIP、BGP、FTP、DNS、Telnet、SMTP、HTTP、WWW、NFS

面试问到的标准回答：
物理层 ： I trible E的一些协议
数据链路层：PPP（同等数据单元之间传递帧），MAC（节点什么时候允许发送分组）
网络层： IP，ICMP（传输错误信息），ARP（地址解析IP和物理地址），OSPF（路由选择协议）
传输层：TCP，UDP
会话层：
表示层：telnet（远程控制web服务器）
应用层：HTTP，FTP，DNS（域名转化IP），SMTP（邮件传输协议）


##### 协议所占端口号：
- http 80,
- ftp 20,21,
- telnet 23,
- SMTP 25。

### <a name="2">2. IP 地址的分类。</a>
私有地址有: <br>
A 类:10.0.0.0 到 10.255.255.255 <br>
B 类:172.16.0.0 到 172.31.255.255 <br>
C 类:192.168.0.0 到 192.168.255.255 <br>
### <a name="3">3. 画出三次握手和四次挥手的图(状态转移图)。TCP 为什么三次握手,四次挥手?</a>

#### 3 次握手过程状态:
- LISTEN: 表示服务器端的某个 SOCKET 处于监听状态,可以接受连接了。
- SYN_SENT: 当客户端 SOCKET 执行 CONNECT 连接时,它首先发送 SYN 报文,因
此也随即它会进入到了 SYN_SENT 状态,并等待服务端发送三次握手中的第 2 个报文。
SYN_SENT 状态表示客户端已发送 SYN 报文。
- SYN_RCVD: 这个状态表示接收到了 SYN 报文,在正常情况下,这个状态是服务器端
的 SOCKET 在建立 TCP 连接时的三次握手会话过程中的一个中间状态,很短暂,基本上
用 netstat 你是很难看到这种状态的,除非你特意写了一个客户端测试程序,故意将三次

TCP 握手过程中最后一个 ACK 报文不予发送。因此这种状态时,当收到客户端的 ACK 报
文后,它会进入到 ESTABLISHED 状态。(服务器端)
- ESTABLISHED:表示连接已经建立了。

#### 4 次挥手过程状态:
- FIN_WAIT_1: 这个状态要好好解释一下,其实 FIN_WAIT_1 和 FIN_WAIT_2 状态的真
正含义都是表示等待对方的 FIN 报文。而这两种状态的区别是:FIN_WAIT_1 状态实际上
是当 SOCKET 在 ESTABLISHED 状态时,它想主动关闭连接,向对方发送了 FIN 报文,
此时该 SOCKET 即进入到 FIN_WAIT_1 状态。而当对方回应 ACK 报文后,则进入到
- FIN_WAIT_2 状态,当然在实际的正常情况下,无论对方何种情况下,都应该马上回应 ACK
报文,所以 FIN_WAIT_1 状态一般是比较难见到的,而 FIN_WAIT_2 状态还有时常常可以
用 netstat 看到。(主动方持有的状态)
FIN_WAIT_2:
上面已经详细解释了这种状态,实际上 FIN_WAIT_2 状态下的 SOCKET,
表示半连接,也即有一方要求 close 连接,但另外还告诉对方,我暂时还有点数据需要传
送给你(ACK 信息),稍后再关闭连接。(主动方的状态)
- TIME_WAIT: 表示收到了对方的 FIN 报文,并发送出了 ACK 报文,就等 2MSL 后即可
回到 CLOSED 可用状态了。
如果 FIN_WAIT_1 状态下,收到了对方同时带 FIN 标志和 ACK
标志的报文时,可以直接进入到 TIME_WAIT 状态,而无须经过 FIN_WAIT_2 状态。(主
动方的状态)。
- CLOSING(比较少见): 表示双方同时关闭连接。如果双方几乎同时调用 close 函数,
那么会出现双方同时发送 FIN 报文的情况,就会出现 CLOSING 状态,表示双方都在关闭
连接。这种状态比较特殊,实际情况中应该是很少见,属于一种比较罕见的例外状态。正
常情况下,当你发送 FIN 报文后,按理来说是应该先收到(或同时收到)对方的 ACK 报
文,再收到对方的 FIN 报文。但是 CLOSING 状态表示你发送 FIN 报文后,并没有收到对
方的 ACK 报文,反而却收到了对方的 FIN 报文。什么情况下会出现此种情况呢?其实细
想一下,也不难得出结论:那就是如果双方几乎在同时 close 一个 SOCKET 的话,那么
就出现了双方同时发送 FIN 报文的情况,也即会出现 CLOSING 状态,表示双方都正在关
闭 SOCKET 连接。
- CLOSE_WAIT: 这种状态的含义其实是表示在等待关闭。当对方 close 一个 SOCKET
后发送 FIN 报文给自己,你系统毫无疑问地会回应一个 ACK 报文给对方,此时则进入到
CLOSE_WAIT 状态。接下来,实际上你真正需要考虑的事情是察看你是否还有数据发送
给对方,如果没有的话,那么你也就可以 close 这个 SOCKET,发送 FIN 报文给对方,
也即关闭连接。所以你在 CLOSE_WAIT 状态下,需要完成的事情是等待你去关闭连接。
(被动方的状态)

- LAST_ACK: 这个状态还是比较容易好理解的,它是被动关闭一方在发送 FIN 报文后,
最后等待对方的 ACK 报文。当收到 ACK 报文后,也即可以进入到 CLOSED 可用状态了。
(被动方的状态)。
- CLOSED: 表示连接中断。 <br>
&ensp;&ensp;&ensp;&ensp;
    首先 Client 端发送连接请求报文,Server 段接受连接后回复 ACK 报文,并为这次连接
分配资源。Client 端接收到 ACK 报文后也向 Server 段发生 ACK 报文,并分配资源,这样
TCP 连接就建立了。 <br>
&ensp;&ensp;&ensp;&ensp;
    ACK TCP 报头的控制位之一,对数据进行确认.确认由目的端发出,用它来告诉发送端
这个序列号之前的数据段都收到了.比如,确认号为 X,则表示前 X-1 个数据段都收到了,只有
当 ACK=1 时,确认号才有效,当 ACK=0 时,确认号无效,这时会要求重传数据,保证数据的完整
性. <br>
&ensp;&ensp;&ensp;&ensp;
    SYN 同步序列号,TCP 建立连接时将这个位置 1。 <br>
&ensp;&ensp;&ensp;&ensp;
    FIN 发送端完成发送任务位,当 TCP 完成数据传输需要断开时,提出断开连接的一方将
这位置 1。 <br>
*【注意】中断连接端可以是 Client 端,也可以是 Server 端。* <br>
&ensp;&ensp;&ensp;&ensp;
    假设 Client 端发起中断连接请求,也就是发送 FIN 报文。Server 端接到 FIN 报文后,
意思是说"我 Client 端没有数据要发给你了",但是如果你还有数据没有发送完成,则不必急
着关闭 Socket,可以继续发送数据。所以你先发送 ACK,"告诉 Client 端,你的请求我收
到了,但是我还没准备好,请继续你等我的消息"。这个时候 Client 端就进入 FIN_WAIT 状
态,继续等待 Server 端的 FIN 报文。当 Server 端确定数据已发送完成,则向 Client 端发
送 FIN 报文,"告诉 Client 端,好了,我这边数据发完了,准备好关闭连接了"。Client 端收
到 FIN 报文后,"就知道可以关闭连接了,但是他还是不相信网络,怕 Server 端不知道要
关闭,所以发送 ACK 后进入 TIME_WAIT 状态,如果 Server 端没有收到 ACK 则可以重传。
“,Server 端收到 ACK 后,"就知道可以断开连接了"。Client 端等待了 2MSL 后依然没有收
到回复,则证明 Server 端已正常关闭,那好,我 Client 端也可以关闭连接了。Ok,TCP
连接就这样关闭了! <br>

### 三次握手
客户端发送SYN，进入SYN_SENT状态
服务器此前一直在wait阶段
收到之后，发送SYN+ACK
进入syn_revd阶段
客户端收到之后再次发送ACK
进入establish状态
建立连接
### 四次挥手
发送fin
客户端收到fin，回复ack
客户端收到ack，已经释放
服务端继续发送fin+ack
客户端回复ack


### <a name="4">4. 为什么收到 Server 端的确认之后,Client 还需要进行第三次“握手”呢?</a>
&ensp;&ensp;&ensp;&ensp;
    采用三次握手是为了*防止失效的连接请求报文段突然又传送到主机 B*因而产生错误。
&ensp;&ensp;&ensp;&ensp;
    “已失效的连接请求报文段”的产生在这样一种情况下: client 发出的第一个连接请求报
文段并没有丢失,而是在某个网络结点长时间的滞留了(因为网络并发量很大在某结点被
阻塞了),以致延误到连接释放以后的某个时间才到达 server。*本来这是一个早已失效的
报文段。但 server 收到此失效的连接请求报文段后,就误认为是 client 再次发出的一个新
的连接请求。于是就向 client 发出确认报文段,同意建立连接。假设不采用“三次握手”,
那么只要 server 发出确认,新的连接就建立了。由于现在 client 并没有发出建立连接的请
求,因此不会理睬 server 的确认,也不会向 server 发送数据。但 server 却以为新的运输
连接已经建立,并一直等待 client 发来数据。这样,server 的很多资源就白白浪费掉了*。
采用“三次握手”的办法可以防止上述现象发生。例如刚才那种情况,client 不会向 server 的
确认发出确认。server 由于收不到确认,就知道 client 并没有要求建立连接。”。*主要目的
防止 server 端一直等待,浪费资源。* <br>

&ensp;&ensp;&ensp;&ensp;
    如果两次握手的话,客户端有可能因为网络阻塞等原因会发送多个请求报文,
这时服务器就会建立连接,浪费掉许多服务器的资源。


### <a name="5">5. 为什么要 4 次挥手</a>
&ensp;&ensp;&ensp;&ensp;
    *确保数据能够完成传输*。 <br>
&ensp;&ensp;&ensp;&ensp;
    但关闭连接时,当收到对方的 FIN 报文通知时,它仅仅表示对方没有数据发送给你了;
但未必你所有的数据都全部发送给对方了,所以你可以未必会马上会关闭 SOCKET,也即你可
能还需要发送一些数据给对方之后,再发送 FIN 报文给对方来表示你同意现在可以关闭连
接了,*所以它这里的 ACK 报文和 FIN 报文多数情况下都是分开发送的。* <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 协议是一种面向连接的、可靠的、基于字节流的运输层通信协议。TCP 是*全双工模
式*,这就意味着,当主机 1 发出 FIN 报文段时,只是表示主机 1 已经没有数据要发送了,
主机 1 告诉主机 2,它的数据已经全部发送完毕了;但是,这个时候主机 1 还是可以接受来
自主机 2 的数据;当主机 2 返回 ACK 报文段时,表示它已经知道主机 1 没有数据发送了,
但是主机 2 还是可以发送数据到主机 1 的;当主机 2 也发送了 FIN 报文段时,这个时候就
表示主机 2 也没有数据要发送了,就会告诉主机 1,我也没有数据要发送了,之后彼此就会
愉快的中断这次 TCP 连接。 <br>


### time_wait 状态产生的原因?

1. 可靠地实现 TCP 全双工连接的终止 
    我们必须要假想网络是不可靠的,你无法保证你最后发送的 ACK 报文会一定被对方收
到,因此对方处于 LAST_ACK 状态下的 SOCKET 可能会因为*超时*未收到 ACK 报文,而重发 FIN
报文,client *必须维护这条连接的状态(保持 time_wait,具体而言,就是这条 TCP 连接
对应的(local_ip, local_port)资源不能被立即释放或重新分配) *以便*可以重发丢失的
ACK* ,如果主动关闭端不维持 TIME_WAIT 状态,而是处于 CLOSED 状态,主动关闭端将会响
应一个 RST,结果 server 认为发生错误,导致服务器端不能正常关闭连接。所以这个
TIME_WAIT 状态的作用就是用来重发可能丢失的 ACK 报文。所以,当客户端等待 2MSL(2
倍报文最大生存时间)后,没有收到服务端的 FIN 报文后,他就知道服务端已收到了 ACK
报文,所以客户端此时才关闭自己的连接。
2. 允许老的重复分节在网络中消逝
    如果 TIME_WAIT 状态保持时间不足够长 ( 比如小于 2MSL) ,第一个连接就正常终止
了。 第二个拥有相同四元组(local_ip, local_port, remote_ip,remote_port)的连接出
现(建立起一个相同的 IP 地址和端口之间的 TCP 连接),而第一个连接的重复报文到达,
干扰了第二个连接。 TCP 实现必须防止某个连接的重复报文在连接终止后出现,所以让
TIME_WAIT 状态保持时间足够长 (2MSL) ,连接相应方向上的 TCP 报文要么完全响应完
毕,要么被丢弃。建立第二个连接的时候,不会混淆。 <br>

### <a name="7">7. 如果网络连接中出现大量 TIME_WAIT 状态所带来的危害?</a>
&ensp;&ensp;&ensp;&ensp;
    如果系统中有很多 socket 处于 TIME_WAIT 状态,当需要创建新的 socket 连接的时
候可能会受到影响,这也会影响到系统的扩展性。 <br>
&ensp;&ensp;&ensp;&ensp;
    之所以 TIME_WAIT 能够影响系统的扩展性是因为在一个 TCP 连接中,一个 Socket 如果
关闭的话,它将保持 TIME_WAIT 状态大约 1-4 分钟 。如果很多连接快速的打开和关闭的
话,系统中处于 TIME_WAIT 状态的 socket 将会积累很多,由于本地端口数量的限制,同一
时间只有有限数量的 socket 连接可以建立,如果太多的 socket 处于 TIME_WAIT 状态,你
会发现,由于用于新建连接的本地端口太缺乏,将会很难再建立新的对外连接。

### <a name="8">8. 如何消除大量 TCP 短连接引发的 TIME_WAIT?</a>
1. *可以改为长连接*,但代价较大,长连接太多会导致服务器性能问题,而且 PHP 等脚
本语言,需要通过 proxy 之类的软件才能实现长连接;
2. 修改 ipv4.ip_local_port_range,增大可用端口范围,但只能缓解问题,不能根本
解决问题;
3. 客户端程序中设置 socket 的 *SO_LINGER* 选项;
4. 客户端机器打开 tcp_tw_recycle 和 tcp_timestamps 选项 实现快速回收和重用


### <a name="9">9. TIME_WAIT 的时间? 当关闭连接时最后一个 ACK 丢失怎么办?</a>
#### TIME_WAIT 的时间?
&ensp;&ensp;&ensp;&ensp;
    就是 2 个报文最长生存时间(2MSL),1 个 MSL 在 RFC 上建议是 2 分钟,而实现传统上
使用 30 秒,因而,*TIME_WAIT 状态一般维持在 1-4 分钟。*

#### 当关闭连接时最后一个 ACK 丢失怎么办?
&ensp;&ensp;&ensp;&ensp;
    如果最后一个 ACK 丢失的话,TCP 就会认为它的 FIN 丢失,进行重发 FIN。
在客户端收到 FIN 后,就会设置一个 2MSL 计时器,2MSL 计时器可以使客户等
待足够长的时间,使得在 ACK 丢失的情况下,可以等到下一个 FIN 的到来。如
果在 TIME-WAIT 状态汇总有一个新的 FIN 到达了,客户就会发送一个新的 ACK,
并重新设置 2MSL 计时器。 <br>
&ensp;&ensp;&ensp;&ensp;
    如果重传 FIN 到达客户端时,客户端已经进入 CLOSED 状态时,那么客户就
永远收不到这个重传的 FIN 报文段,服务器收不到 ACK,服务器无法关闭连接。 <br>

### <a name="10">10. TCP如何保证可靠传输?</a>
http://www.cnblogs.com/deliver/p/5471231.html <br>
0、在传递数据之前,会有三次握手来建立连接。 <br>
1、应用数据被分割成 TCP 认为最适合发送的数据块(按字节编号,合理分片)。这和 UDP
完全不同,应用程序产生的数据报长度将保持不变。 (将数据截断为合理的长度) <br>
2、当 TCP 发出一个段后,它启动一个定时器,等待目的端确认收到这个报文段。如果不
能及时收到一个确认,将重发这个报文段。(超时重发) <br>

### <a name="11">11. TCP 建立连接之后怎么保持连接(检测连接断没断)?</a>
&ensp;&ensp;&ensp;&ensp;
    有两种技术可以运用。一种是由 TCP 协议层实现的 Keepalive 机制,另一种是由应用
层自己实现的 HeartBeat 心跳包。 <br>
&ensp;&ensp;&ensp;&ensp;
    1.在 TCP 中有一个 Keep-alive 的机制可以检测死连接,原理很简单,当连接闲置一定
的时间(参数值可以设置,默认是 2 小时)之后,TCP 协议会向对方发一个 keepalive 探
针包(包内没有数据),对方在收到包以后,如果连接一切正常,应该回复一个 ACK;如
果连接出现错误了(例如对方重启了,连接状态丢失),则应当回复一个 RST;如果对方
没有回复,那么,服务器每隔一定的时间(参数值可以设置)再发送 keepalive 探针包,
如果连续多个包(参数值可以设置)都被无视了,说明连接被断开了。 <br>
&ensp;&ensp;&ensp;&ensp;
    2.心跳包之所以叫心跳包是因为:它像心跳一样每隔固定时间发一次,以此来告诉服
务器,这个客户端还活着。事实上这是为了保持长连接,至于这个包的内容,是没有什么
特别规定的,不过一般都是很小的包,或者只包含包头的一个空包。由应用程序自己发送
心跳包来检测连接的健康性。客户端可以在一个 Timer 中或低级别的线程中定时向服务器
发送一个短小精悍的包,并等待服务器的回应。客户端程序在一定时间内没有收到服务器
回应即认为连接不可用,同样,服务器在一定时间内没有收到客户端的心跳包则认为客户
端已经掉线。 <br>

### <a name="12">12. TCP 三次握手有哪些漏洞?如何防止泛洪攻击？</a>
#### 1.SYN Flood 攻击
&ensp;&ensp;&ensp;&ensp;
    SYN Flood 是 DDoS 攻击的方式之一,这是一种利用 TCP 协议缺陷,发送大量伪造
的 TCP 连接请求,从而使得被攻击方资源耗尽(CPU 满负荷或内存不足)的攻击方式。
要明白这种攻击的基本原理,还是要从 TCP 连接建立的过程开始说起:
首先,请求端(客户端)发送一个包含 SYN 标志的 TCP 报文,SYN 即同步
(Synchronize),同步报文会指明客户端使用的端口以及 TCP 连接的初始序号;
第二步,服务器在收到客户端的 SYN 报文后,将返回一个 SYN+ACK 的报文,表示客
户端的请求被接受,同时 TCP 序号被加一,ACK 即确认(Acknowledgment)。
第三步,客户端也返回一个确认报文 ACK 给服务器端,同样 TCP 序列号被加一,到
此一个 TCP 连接完成。 <br>
&ensp;&ensp;&ensp;&ensp;
    以上的连接过程在 TCP 协议中被称为三次握手。
问题就出在 TCP 连接的三次握手中,假设一个用户向服务器发送了 SYN 报文后突然
死机或掉线,那么服务器在发出 SYN+ACK 应答报文后是无法收到客户端的 ACK 报文的
(第三次握手无法完成),这种情况下服务器端一般会不停地重试(再次发送 SYN+ACK 给客
户端)并等待一段时间后丢弃这个未完成的连接,这段时间的长度我们称为 SYN Timeout
(大约为 30 秒-2 分钟);一个用户出现异常导致服务器的一个线程等待 1 分钟并不是什
么很大的问题,但如果有一个恶意的攻击者发送大量伪造原 IP 地址的攻击报文,发送到服
务端,服务器端将为了维护一个非常大的半连接队列而消耗非常多的 CPU 时间和内存。服
务器端也将忙于处理攻击者伪造的 TCP 连接请求而无暇理睬客户的正常请求(毕竟客户端
的正常请求比率非常之小),此时从正常客户的角度看来,服务器失去响应,这种情况我
们称作:服务器端受到了 SYN Flood 攻击(SYN 洪水攻击)。 <br>
&ensp;&ensp;&ensp;&ensp;
    原理:攻击者首先伪造地址对 服务器发起 SYN 请求,服务器回应(SYN+ACK)包,而
真实的 IP 会认为,我没有发送请求,不作回应。服务 器没有收到回应,这样的话,服务
器不知 道(SYN+ACK)是否发送成功,默认情况下会重试 5 次(tcp_syn_retries)。这样的
话,对于服务器的内存,带宽都有很大的消耗。攻击者 如果处于公网,可以伪造 IP 的话,
对于服务器就很难根据 IP 来判断攻击者,给防护带来很大的困难。 <br>
&ensp;&ensp;&ensp;&ensp;
    2.解决方法: <br>
&ensp;&ensp;&ensp;&ensp;
    第一种是缩短 SYN Timeout 时间; <br>
&ensp;&ensp;&ensp;&ensp;
    由于 SYN Flood 攻击的效果取决于服务器上保持的 SYN 半连接数,这个值=SYN 攻击
的频度 x SYN Timeout,所以通过缩短从接收到 SYN 报文到确定这个报文无效并丢弃该连
接的时间,例如设置为 20 秒以下(过低的 SYN Timeout 设置可能会影响客户的正常访问),
可以成倍的降低服务器的负荷。 <br>
&ensp;&ensp;&ensp;&ensp;
    第二种方法是设置 SYN Cookie; <br>
&ensp;&ensp;&ensp;&ensp;
    就是给每一个请求连接的 IP 地址分配一个 Cookie,如果短时间内连续受到某个 IP 的
重复 SYN 报文,就认定是受到了攻击,以后从这个 IP 地址来的包会被丢弃。
可是上述的两种方法只能对付比较原始的 SYN Flood 攻击,缩短 SYN Timeout 时间仅
在对方攻击频度不高的情况下生效,SYN Cookie 更依赖于对方使用真实的 IP 地址,如果
攻击者以数万/秒的速度发送 SYN 报文,同时利用随机改写 IP 报文中的源地址,以上的方
法将毫无用武之地。例如 SOCK_RAW 返回的套接字通过适当的设置可以自己完全控制 IP
头的内容从而实现 IP 欺骗。 <br>
&ensp;&ensp;&ensp;&ensp;
    第三种方法是 Syn Cache 技术。 <br>
&ensp;&ensp;&ensp;&ensp;
    这种技术在收到 SYN 时不急着去分配系统资源,而是先回应一个 ACK 报文,并在一
个专用的 HASH 表中(Cache)中保存这种半开连接,直到收到正确的 ACK 报文再去分配
系统。 <br>
&ensp;&ensp;&ensp;&ensp;
    第四种方法是使用硬件防火墙。 <br>
SYN FLOOD 攻击很容易就能被防火墙拦截。 <br>
扩展:ddos 攻击的原理,如何防止 ddos 攻击? <br>
DDOS 是英文 Distributed Denial of Service 的缩写,意即“分布式拒绝服务”。 <br>
![01_13_1](/data/images/Java应届生面试突击/计算机网络/01_13_1.png) <br>

#### 当前主要有 2 种流行的 DDOS 攻击:
&ensp;&ensp;&ensp;&ensp;
    1、SYN Flood 攻击:这种攻击方法是经典最有效的 DDOS 方法。 <br>
&ensp;&ensp;&ensp;&ensp;
    2、TCP 全连接攻击 <br>
&ensp;&ensp;&ensp;&ensp;
    这种攻击是为了绕过常规防火墙的检查而设计的,一般情况下,常规防火墙大多具备
过滤 Land 等 DOS 攻击的能力,但对于正常的 TCP 连接是放过的,很多网络服务程序(如:
IIS、Apache 等 Web 服务器)能接受的 TCP 连接数是有限的,一旦有大量的 TCP 连接,
则会导致网站访问非常缓慢甚至无法访问。 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 全连接攻击就是通过许多僵尸主机不断地与受害服务器建立大量的 TCP 连接,
直到服务器的内存等资源被耗尽而被拖跨,从而造成拒绝服务。 <br>
&ensp;&ensp;&ensp;&ensp;
    这种攻击的特点是可绕过一般防火墙的防护而达到攻击目的。 <br>
&ensp;&ensp;&ensp;&ensp;
    缺点是需要找很多僵尸主机,并且由于僵尸主机的 IP 是暴露的,因此容易被追踪。 <br>
&ensp;&ensp;&ensp;&ensp;
    1.限制 SYN 流量 <br>
&ensp;&ensp;&ensp;&ensp;
    用户在路由器上配置 SYN 的最大流量来限制 SYN 封包所能占有的最高频宽,这样,
当出现大量的超过所限定的 SYN 流量时,说明不是正常的网络访问,而是有黑客入侵。 <br>
&ensp;&ensp;&ensp;&ensp;
    2.定期扫描 <br>
&ensp;&ensp;&ensp;&ensp;
    定期扫描现有的网络主节点,清查可能存在的安全漏洞,对新出现的漏洞及时进行清
理。 <br>
&ensp;&ensp;&ensp;&ensp;
    3.在骨干节点配置防火墙 <br>
&ensp;&ensp;&ensp;&ensp;
    防火墙本身能抵御 Ddos 攻击和其他一些攻击。在发现受到攻击的时候,可以将攻击
导向一些牺牲主机,这样可以保护真正的主机不被攻击。当然导向的这些牺牲主机可以选
择不重要的,或者是 linux 以及 unix 等漏洞少和天生防范攻击优秀的系统。 <br>
&ensp;&ensp;&ensp;&ensp;
    4.用足够的机器承受黑客攻击 <br>
&ensp;&ensp;&ensp;&ensp;
    这是一种较为理想的应对策略。如果用户拥有足够的容量和足够的资源给黑客攻击,
在它不断访问用户、夺取用户资源之时,自己的能量也在逐渐耗失,或许未等用户被攻死,
黑客已无力支招儿了。不过此方法需要投入的资金比较多,平时大多数设备处于空闲状态,
和目前中小企业网络实际运行情况不相符。 <br>
&ensp;&ensp;&ensp;&ensp;
    5.过滤不必要的服务和端口 <br>
&ensp;&ensp;&ensp;&ensp;
    可以使用 Inexpress、Express、Forwarding 等工具来过滤不必要的服务和端口,即在
路由器上过滤假 IP。 <br>

##### 2.Land 攻击
&ensp;&ensp;&ensp;&ensp;
    LAND 攻击利用了 TCP 连接建立的三次握手过程,通过向一个目标主机发送一个用于
建立请求连接的 TCP *SYN 报文*而实现对目标主机的攻击。与正常的 TCP SYN 报文不同的
是:*LAND 攻击报文的源 IP 地址和目的 IP 地址是相同的,都是目标主机的 IP 地址*。这样
目标主机接在收到这个 SYN 报文后,就会向该报文的源地址发送一个 ACK 报文,并建立
一个 TCP 连接控制结构,而该报文的源地址就是自己。由于目的 IP 地址和源 IP 地址是相
同的,都是目标主机的 IP 地址,因此这个 *ACK 报文就发给了目标主机本身*。这样如果攻
击者发送了足够多的 SYN 报文,则目标计算机的 TCB 可能会耗尽,最终不能正常服务。 <br>

### <a name="13">13. TCP 存在的缺陷有哪些?</a>
1. TCP 三次握手可能会出现 SYN Flood 攻击。
2. TCP 三次握手可能会出现 Land 攻击。
2. Connection Flood 攻击。 <br>
&ensp;&ensp;&ensp;&ensp;
    原理是利用真实的 IP 地址向服务器发起大量的连接,并且建立连接之后很长时间不
释放并定时发送垃圾数据包给服务器使连接得以长时间保持,占用服务器的资源,造成服
务器上残余连接(WAI-time 状态)过多,效率降低,甚至资源耗尽,无法响应其他客户所发
起的连接。 <br>

#### 防范该攻击主要有如下方法:
1. 限制每个源 IP 的连接数。
2. 对恶意连接的 IP 进行封禁。
3. 主动清除残余连接。

### <a name="14">14. 三次握手与 accept()函数的关系?</a>
1. 客户端发送 SYN 给服务器。
2. 服务器发送 SYN+ACK 给客户端。
3. 客户端发送 ACK 给服务器。
4. 连接建立,调用 accept()函数获取连接。

### <a name="15">15. 在三次握手和四次挥手协议中,客户端和服务器端各用到什么函数。</a>
https://www.cnblogs.com/xuan52rock/p/9454696.html <br>
*(这里涉及到底层的 socket 知识)* <br>
socket 的基本操作(加粗的是阻塞函数) <br>
socket()函数 <br>
bind()函数 <br>
listen()、connect()函数 <br>
accept()函数 <br>
read()/write()函数--------send()/recv()函数 <br>
close()函数 <br>

&ensp;&ensp;&ensp;&ensp;
    从图中可以看出,当客户端调用 connect()函数时,触发了连接请求,向服务器发送了
SYN J 包,这时 connect 进入阻塞状态(先调用 connect()函数,然后发送 SYN 包);
服务器监听到连接请求,即收到 SYN J 包,调用 accept()函数接收请求(先收到 SYN 包,
然后调用 accept()函数),向客户端发送 SYN K ,ACK J+1,这时 accept 进入阻塞状态;
客户端收到服务器的 SYN K ,ACK J+1 之后,这时 connect 返回,并对 SYN K 进行确认;
服务器收到 ACK K+1 时,accept 返回,至此三次握手完毕,连接建立。
总结:客户端的 connect()函数在三次握手的第二次之后返回,而服务器端的 accept
()在三次握手的第三次之后返回。
注意,read()返回 0 就表明收到了 FIN 段。

*函数:*
- socket()-- 创建套接字,它会创建一个结构体及收发缓冲区。此时并不指定该套接字在
哪个 IP 和 PORT 口上。

- bind()-- 用于将套接字绑定在特定的 IP 和 PORT 上。

- listen(SOCKET s, int backlog)-- 用于为侦听端口创建两个队列(见上图)用于接收客户
端的 SYN 请求,侦听客户端的 Socket 连接请求。backlog 指的就是已经完成握手了的队
列的大小。

- accept() --- 将侦听端口中的 ESTABLISHED 队列中取出那些连接。 accept 函数返回的是
已建立连接的套接字描述符,包括客户端的 ip 和 port 信息,服务器的 ip 和 port 信息。

- connect()-- 客户端连接请求。

- read() ---- 负责从 fd 中读取内容。当读成功时,read 返回实际所读的字节数,如果返回的
值是 0 表示已经读到文件的结束了,小于 0 表示出现了错误。

- write() -----将 buf 中的 nbytes 字节内容写入文件描述符 fd。成功时返回写的字节数。

*客户端过程*:socket() -> bind()(可选的)->connect()
*服务器过程*:socket() -> bind() -> listen() -> accept()

### <a name="16">16. listen 的真正目的?</a>
&ensp;&ensp;&ensp;&ensp;
    listen 的函数为侦听端口创建两个队列:未完成队列(SYN_RCV 状态)和已完成队列。
如果不调用 listen,则客户端过来的 SYN 请求无法入队接受进一步的处理。因此,listen
是服务器的必须过程。

### <a name="17">17. 如果客户端发起握手请求,服务端无法立刻建立连接应该回应什么?</a>
&ensp;&ensp;&ensp;&ensp;
    RST 报文,表示重置,重新建立连接。

### <a name="18">18. TCP 与 UDP 的区别(或各自的优缺点),以及各自的用途和使用领域。</a>
1. 基于连接 vs 无连接 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 是面向连接的协议,而 UDP 是无连接的协议。这意味着当一个客户端和一个服
务器通过 TCP 发送数据之前,必须先建立连接,建立连接的过程也被称为 TCP 三次握手。

2. 可靠性 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 提供交付保证,这意味着一个使用 TCP 协议发送的消息是保证交付给客户端的,
如果消息在传输过程中丢失,那么它将重发。UDP 是不可靠的,它不提供任何交付的保证,
一个数据报包在运输途中可能会丢失。

3. 有序性 <br>
&ensp;&ensp;&ensp;&ensp;
    消息到达网络的另一端时可能是无序的,TCP 协议将会为你排好序。UDP 不提供任何有序性的保证。

4. 速度 <br>
&ensp;&ensp;&ensp;&ensp;
     TCP 速度比较慢,而 UDP 速度比较快,因为 TCP 必须创建连接,以保证消息的可靠
交付和有序性,他需要做比 UDP 多的事。这就是为什么 UDP 更适用于对速度比较敏感的
应用。TCP 适合传输大量数据, UDP 适合传输少量数据。

5. 重量级 vs 轻量级 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 是重量级的协议,UDP 协议则是轻量级的协议。一个 TCP 数据报的报头大小最
少是 20 字节,UDP 数据报的报头固定是 8 个字节。TCP 报头中包含序列号,ACK 号,数
据偏移量,保留,控制位,窗口,紧急指针,可选项,填充项,校验位,源端口和目的端
口。而 UDP 报头只包含长度,源端口号,目的端口,和校验和。

6. 流量控制或拥塞控制 <br>

TCP 有流量控制和拥塞控制。UDP 没有流量控制和拥塞控制。
   
7. TCP 面向字节流,UDP 是面向报文的。
   TCP 是字节流的协议,无记录边界。
UDP 发送的每个数据报是记录型的数据报,所谓的记录型数据报就是接收进程可以识别接收到的数据报的记录边界。这也导致了TCP可能出现粘包

8. TCP 只能单播,不能发送广播和组播;UDP 可以广播和组播。 <br>

##### TCP 应用场景: 
&ensp;&ensp;&ensp;&ensp;
   效率要求相对低,但对准确性要求相对高的场景。因为传输中需要
对数据确认、重发、排序等操作,相比之下效率没有 UDP 高。举几个例子:文件传输、邮
件传输、远程登录。

##### UDP 应用场景: 
&ensp;&ensp;&ensp;&ensp;
    效率要求相对高,对准确性要求相对低的场景。举几个例子:QQ
聊天、QQ 视频、网络语音电话(即时通讯,速度要求高,但是出现偶尔断续不是太大问
题,并且此处完全不可以使用重发机制)、广播通信(广播、多播)。

### <a name="19">19. 为什么 TCP 比 UDP 安全,但是还有很多用 UDP?</a>
1. 无需建立连接(减少延迟)
2. 无需维护连接状态
3. 头部开销小,一个 TCP 数据报的报头大小最少是 20 字节,UDP 数据报的报头固定
是 8 个字节。
4. 应用层能更好地控制要发送的数据和发送时间。UDP 没有拥塞控制,因此网络中的
拥塞不会影响主机的发送频率。某些实时应用要求以稳定的速度发送数据,可以容忍一些
数据的丢失,但不允许有较大的延迟,而 UDP 正好满足这些应用的需求。

### <a name="20">20.  UDP 为何快?</a>
1. 不需要建立连接
2. 对于收到的数据,不用给出确认
3. 没有超时重发机制
4. 没有流量控制和拥塞控制

### <a name="21">21. TCP 如何实现流量控制和拥塞控制。tcp 是怎么做错误处理的?</a>
&ensp;&ensp;&ensp;&ensp;
    *流量控制*就是*让发送方的发送速率不要太快*,要让接收方来得及接收。利用*滑动窗口
机制*可以很方便地在 TCP 连接上实现对发送方的*流量控制*。原理这就是运用 TCP 报文段中
的*窗口大小字段*来控制,发送方的发送窗口不可以大于接收方发回的窗口大小。 <br>
&ensp;&ensp;&ensp;&ensp;
    *滑动窗口机制见《王道单科-数据链路层的部分》。* <br>
&ensp;&ensp;&ensp;&ensp;
    所谓滑动窗口协议,自己理解有两点:1. “窗口”对应的是一段可以被发送者发送
的字节序列,其连续的范围称之为“窗口”;2. “滑动”则是指这段“允许发送的范围”
是可以随着发送的过程而变化的,方式就是按顺序“滑动”。在引入一个例子来说这个协
议之前,我觉得很有必要先了解以下前提:
-    1. TCP 协议的两端分别为发送者 A 和接收者 B,由于是全双工协议,因此 A 和 B 应该
分别维护着一个独立的发送缓冲区和接收缓冲区,由于对等性(A 发 B 收和 B 发 A 收),我
们以 A 发送 B 接收的情况作为例子;
     2. 发送窗口是发送缓存中的一部分,是可以被 TCP 协议发送的那部分,其实应用层
需要发送的所有数据都被放进了发送者的发送缓冲区;
     3. 发送窗口中相关的有四个概念:已发送并收到确认的数据(不再发送窗口和发送
缓冲区之内)、已发送但未收到确认的数据(位于发送窗口之中)、允许发送但尚未发送
的数据以及发送窗口外发送缓冲区内暂时不允许发送的数据;
     4. 每次成功发送数据之后,发送窗口就会在发送缓冲区中按顺序移动,将新的数据
包含到窗口中准备发送;

#### 几种拥塞控制方法:
慢开始和拥塞避免、快重传和快恢复。   

### <a name="22">22. TCP 滑动窗口协议,窗口过大或过小有什么影响?</a>
&ensp;&ensp;&ensp;&ensp;
    滑动窗口的大小对网络性能有很大的影响。 <br>
&ensp;&ensp;&ensp;&ensp;
    *如果滑动窗口过小*,极端的情况就是停止等待协议,发一个报文等一个 ACK,会造成
通信效率下降。 <br>
&ensp;&ensp;&ensp;&ensp;
    *如果滑动窗口过大*,网络容易拥塞,容易造成接收端的缓存不够而溢出,容易产生丢
包现象,则需要多次发送重复的数据,耗费了网络带宽。 <br>

### <a name="23">23. 在流量控制的过程中,必须考虑传输效率。</a>
1. Nagle 算法 <br>
&ensp;&ensp;&ensp;&ensp;
    Nagle 算法是为了避免网络中存在太多的小包(协议头比例非常大)造成拥塞。
Nagle 算法就是为了尽可能发送大块数据,避免网络中充斥着许多小数据块。 <br>
&ensp;&ensp;&ensp;&ensp;
    Nagle 算法要求一个 TCP 连接上最多只能有一个未被确认的未完成的小分组,在该
分组的确认到达之前不能发送其他的小分组。因此它事实上就是一个扩展的停-等协议,只
不过它是基于包停-等的,而不是基于字节停-等的。 <br>
![01_23_1](/data/images/Java应届生面试突击/计算机网络/01_23_1.png) <br>

    他的主要职责是数据的累积,实际上有三个门槛:
- 1) 缓冲区中的字节数达到了一定量(超过阀值 MSS);
  2) 等待了一定的时间(一般的 Nagle 算法都是等待 200ms);
  3) 紧急数据发送。

### <a name="24">24. 说下 TCP 的黏包 ?</a>
&ensp;&ensp;&ensp;&ensp;
    TCP 报文粘连就是,本来发送的是多个 TCP 报文,但是在接收端收到的却是一个报文,
把多个报文合成了一个报文。 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 报文粘连的原因: "粘包"可发生在发送端,也可发生在接收端。在流传输中出现, UDP
不会出现粘包,因为它有消息边界(两段数据间是有界限的)。 <br>
&ensp;&ensp;&ensp;&ensp;
    1. 由 Nagle 算法造成的发送端的粘包 <br>
Nagle 算法产生的背景是,为了解决发送多个非常小的数据包时(比如 1 字节),由
于包头的存在而造成巨大的网络开销。简单的讲,Nagle 算法就是当有数据要发送时,先
不立即发送,而是稍微等一小会,看看在这一小段时间内,还有没有其他需要发送的消息。
当等过这一小会以后,再把要发送的数据一次性都发出去。这样就可以有效的减少包头的
发送次数。 <br>
&ensp;&ensp;&ensp;&ensp;
    2. 接收端接收不及时造成的接收端粘包 <br>
&ensp;&ensp;&ensp;&ensp;
    TCP 会把接收到的数据存在自己的缓冲区中,然后通知应用层取数据.当应用层由于某
些原因不能及时的把 TCP 的数据取出来,就会造成 TCP 缓冲区中存放了几段数据,产生报
文粘连的现象。

#### TCP 报文粘连的解决方法:
1. 关闭 Nagle 算法。在 scoket 选项中,TCP_NODELAY 表示是否使用 Nagle 算法。
2. 接收端尽可能快速的从缓冲区读数据。
3. 可以在发送的数据中,添加一个表示数据的开头和结尾的字符,在收到消息后,通过这
些字符来处理报文粘连。

#### 如何用 udp 实现 tcp(udp 的可靠性怎么提高)?
&ensp;&ensp;&ensp;&ensp;
    如果要通过 UDP 传输数据,但却要保证可靠性的话,要通过第七层(应用层)来实现
的。

### <a name="25">25. TCP 头部有哪些字段?</a>
*(这里又是关键点,在讲这个问题的时候,
不能光讲头部哪些字段,还要结合字段讲讲作用,然后就顺带把整个
TCP 的可靠传输原理,以及相关的拥塞控制等全讲了.这就是主动出
击的技巧)* <br>
![01_25_1](/data/images/Java应届生面试突击/计算机网络/01_25_1.png) <br>
![01_25_2](/data/images/Java应届生面试突击/计算机网络/01_25_2.png) <br>
![01_25_3](/data/images/Java应届生面试突击/计算机网络/01_25_3.png) <br>


源端口 目的端口
序号
确认号 为上一个包的确认号+1 保证了TCP的可靠传输
窗口 用于拥塞控制，保证了网络传输的可靠性
数据偏移
保留位
TCP flags 包括ACK FIN SYN等等，用于保证连接可靠性
检验和 用于保证数据正确
紧急指针 用于指示带外数据




### <a name="26">26. UDP 的首部多长,具体包含哪些字段?</a>
&ensp;&ensp;&ensp;&ensp;
    UDP 的首部是固定 8B。包含的字段如下。 <br>
![01_26_1](/data/images/Java应届生面试突击/计算机网络/01_26_1.png) <br>



---
### 搬运工信息
Author:Jason Lou <br>
Email:vip.iotworld@gmail.com <br>
Blog:https://blog.csdn.net/qq_21508727 <br>
Github:https://github.com/JGPY/JavaGuideBooster <br>
---
