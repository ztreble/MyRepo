//
// Created by treblez on 1/4/21.
//带外数据能够
//

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <assert.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>

int main( int argc, char* argv[] )
{
    if( argc <= 2 )
    {
        printf( "usage: %s ip_address port_number\n", basename( argv[0] ) );
        return 1;
    }
    const char* ip = argv[1];
    int port = atoi( argv[2] );

    struct sockaddr_in server_address;
    bzero( &server_address, sizeof( server_address ) );
    server_address.sin_family = AF_INET;
    inet_pton( AF_INET, ip, &server_address.sin_addr );
    server_address.sin_port = htons( port );
    //与服务器通信的socket
    int sockfd = socket( PF_INET, SOCK_STREAM, 0 );
    assert( sockfd >= 0 );
    //客户端主动与服务器进行连接，成功返回0
    //第一个参数是套接字，第二个参数是服务器地址，第三个参数是地址长度
    if ( connect( sockfd, ( struct sockaddr* )&server_address, sizeof( server_address ) ) < 0 )
    {
        perror("connection failed\n" );
    }
    else
    {
        printf( "send oob data out\n" );
        const char* oob_data = "abc";
        const char* normal_data = "123";
        //send发送数据
        //send( sockfd, normal_data, strlen( normal_data ), 0 );
        send( sockfd, oob_data, strlen( oob_data ), MSG_OOB );
        send( sockfd, normal_data, strlen( normal_data ), 0 );
        //send( sockfd, oob_data, strlen( oob_data ), MSG_OOB );
        //send( sockfd, normal_data, strlen( normal_data ), 0 );
    }

    close( sockfd );
    return 0;
}
