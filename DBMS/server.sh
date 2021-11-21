#!/bin/bash
pipe=/tmp/compipe
trap "rm -f $pipe" EXIT
if [[ ! -p $pipe ]];then
	mkfifo $pipe
fi
while true
do
	if read input_order<$pipe;then	
	array=(${input_order// / })
	case  ${array[0]} in
		create_database)
		./database.sh ${array[1]}
		;;
		create_table)
		./create_table.sh ${array[1]} ${array[2]} ${array[3]}
		;;
		insert)
		./insert.sh ${array[1]} ${array[2]} ${array[3]}
		;;
		select)
		./select.sh ${array[1]} ${array[2]}
		;;
		shutdown)
		echo "shutdown"
		exit 0
		;;
		*)
		echo "error"
		exit 0
	esac
	fi
done
