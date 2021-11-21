#!/bin/bash
if [ $# -ne 3 ];then
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
		count1=`head -n 1 $2 | tr -cd ","|wc -c`
		count2=`echo $3 | tr -cd ","|wc -c`
		echo $count1
		echo $count2
		if [ $count1 -ne $count2 ];then
		       echo Error:
	       	       echo number of columns in tuple does not match schema num	
	        else
			echo $3 >> $2
		fi
	fi
fi
