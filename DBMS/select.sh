#!/bin/bash
if [ $# -ne 3 ] && [ $# -ne 2 ];then
        echo Error:
        echo parameters problem
        exit 0
fi
if [ ! -d $1 ];then
        echo Error:
        echo DB not exist
        exit 0
else
	cd $1
	if [ ! -e $2 ];then
                echo Error:
                echo table not exist
        else
		if [ $# -eq 2 ];then
			while read line
			do
    				echo $line
			done < $2
		fi
        fi
fi
