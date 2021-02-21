package main

import (
	"flag"
	"fmt"
	"io"
	"log"
	"net"
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

func handleCon(c net.Conn) {
	defer c.Close()
	// for {
	_, err := io.WriteString(c, time.Now().Format("15:04:05\n"))
	if err != nil {
		return
	}
	time.Sleep(1 * time.Second)
	// }
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
