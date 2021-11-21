#!/bin/bash
if [ $# -lt 1 ] || [ $# -gt 3 ];then
        echo Error:
        echo parameters problem
        exit 0
fi
if [ ! -d $1 ];then
        echo Error:
	echo DB not exist	
else
	cd $1
	touch $2
	echo $3 > $2
fi

