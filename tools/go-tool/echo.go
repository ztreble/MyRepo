package main

import (
	"bufio"
	"flag"
	"fmt"
	"log"
	"net"
	"strings"
	"time"
)

var (
	host string
	port int
)

func init() {
	flag.StringVar(&host, "host", "localhost", "clock server host")
	flag.IntVar(&port, "port", 8000, "clock server port")
	flag.Parse()
}
func echo(c net.Conn, shout string, delay time.Duration) {
	fmt.Fprintln(c, "\t", strings.ToUpper(shout))
	time.Sleep(delay)
	fmt.Fprintln(c, "\t", shout)
	time.Sleep(delay)
	fmt.Fprintln(c, "\t", strings.ToLower(shout))
}
func handleCon(c net.Conn) {
	input := bufio.NewScanner(c)
	for input.Scan() { //不断接收输入的内容
		echo(c, input.Text(), 1*time.Second)
	}
	c.Close()
}

func main() {
	server := fmt.Sprintf("%s:%d", host, port)
	listener, err := net.Listen("tcp", server)
	if err != nil {
		log.Fatal(err)
	}
	for {
		conn, err := listener.Accept() // 会阻塞
		if err != nil {
			log.Print(err)
			continue
		}
		go handleCon(conn)
	}
}
