#!/bin/bash
if [ $# -lt 1 ];then
	echo Error:
	echo No parameter was provided
	exit 0
fi
if [ ! -d $1 ];then
	mkdir $1
	echo Everything went well
else
	echo Error:
	echo The database already existed
fi
