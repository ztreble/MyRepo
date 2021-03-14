#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>

static bool stop = false;
static void handle_term(int sig){
    stop = true;
}
int main(int argc,char* argv[]) {
    //处理信号
    signal(SIGTERM,handle_term);
    if(argc<=3){
        printf("usage:%s ip_address port_number backlog\n",basename(argv[0]));
        return 1;
    }
    const char* ip = argv[1];
    int port = atoi(argv[2]);
    int backlog = atoi(argv[3]);
    //第一个参数是底层协议族，PF_INET用于Ipv4
    //第二个参数指定服务类型，STREAM指的是流服务;还可以设置为非阻塞和创建子进程关闭
    int  sock = socket(PF_INET,SOCK_STREAM,0);
    assert(sock>=0);
    //通用socket地址
    struct sockaddr_in address;
    //清零
    bzero(&address,sizeof(address));
    //设置地址族为unix本地域协议族
    address.sin_family = AF_INET;
    //网络字节序转换
    inet_pton(AF_INET,ip,&address.sin_addr);
    //htons用于将port转换成网络字节序
    address.sin_port = htons(port);

    int ret = bind(sock,(struct sockaddr*)&address,sizeof(address));
    //学会使用perror打印错误原因
    if(ret==-1){
        //printf("%s",errno);
        perror("Error: ");
    }
    assert(ret!=-1);
    //指定监听队列，连接数不能超过内核监听队列长度，这个监听队列用于存放待处理的客户连接
    //执行过listen调用处于listen状态的socket称为监听socket
    ret = listen(sock,backlog);
    assert(ret!=-1);

    sleep(20);//
    //accept要接受的链接
    struct sockaddr_in client;
    socklen_t client_addrlength = sizeof(client);
    //accept从队列中取出链接
    //accept不关心网络状态变化，只是取出链接
    int connfd = accept(sock,(struct sockaddr*)&client,&client_addrlength);
    if(connfd<0){
        perror("Error:");
    }else{
        char remote[INET_ADDRSTRLEN];
        //最后一个参数意味着目标存储单元的大小
        printf("connected with ip: %s and port: %d\n",inet_ntop(AF_INET,
                                                                &client.sin_addr,remote,INET_ADDRSTRLEN),ntohs(client.sin_port));
        close(connfd);
    }
    close(sock);

    return 0;
}