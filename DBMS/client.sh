#!/bin/bash
if [ $# -ne 1 ];then
        echo Error:
        echo parameters problem
        exit 0
fi
pipe=/tmp/compipe
if [[ ! -p $pipe ]]; then
    echo "Server not running"
    exit 1
fi
trap 'onCtrlC' INT
function onCtrlC(){
	rm -f $pipe
	exit 0
}
while true;do
	read input_order
	echo $input_order > $pipe
done
