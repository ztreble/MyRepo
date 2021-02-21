package main

import (
	"flag"
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"
	"time"
)
func walkDir(dir string, filesize chan<- int64) {
	for _, entry := range dirents(dir) {
		if entry.IsDir() {
			subdir := filepath.Join(dir, entry.Name())
			walkDir(subdir, filesize)
		} else {
			filesize <- entry.Size()
		}
	}
}

func dirents(dir string) []os.FileInfo {
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
	go func() {
		for _, dir := range roots {
			walkDir(dir, filesizes)
		}
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
			fmt.Printf("%d files %1.fM\n", nfiles, float64(nsize)/1e6)
		}
	}

}
