package main

import (
	"flag"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
	"sync"
	"time"
)

func walkDir(dir string, wg *sync.WaitGroup, filesize chan<- int64) {
	defer wg.Done()
	for _, entry := range dirents(dir) {
		if entry.IsDir() {
			wg.Add(1)
			subdir := filepath.Join(dir, entry.Name())
			go walkDir(subdir, wg, filesize)
		} else {
			filesize <- entry.Size()
		}
	}
}
var sema = make(chan struct{}, 20) //最多打开20个目录

func dirents(dir string) []os.FileInfo {
	sema <- struct{}{}
	defer func() { <-sema }()
	entries, err := ioutil.ReadDir(dir)
	if err != nil {
		fmt.Fprintf(os.Stderr, "du1: %v\n", err)
		return nil
	}
	return entries
}

var verbose = flag.Bool("v", false, "show verbose progress messages")

func main() {
	flag.Parse()
	roots := flag.Args()
	if len(roots) == 0 {
		roots = []string{"."}
	}
	filesizes := make(chan int64)
	var wg sync.WaitGroup
	for _, dir := range roots {
		wg.Add(1)
		go walkDir(dir, &wg, filesizes)
	}

	go func() {
		wg.Wait()
		close(filesizes)
	}()
	var tick <-chan time.Time
	if *verbose {
		tick = time.Tick(500 * time.Millisecond)
	}
	var nfiles, nsize int64
loop:
	for {
		select {
		case size, ok := <-filesizes:
			if !ok {
				break loop
			}
			nfiles++
			nsize += size
		case <-tick:
			fmt.Printf("%d files %1.fG\n", nfiles, float64(nsize)/1e9)
		}
	}
}
