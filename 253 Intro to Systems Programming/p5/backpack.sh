#!/bin/bash

if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi

if [ "$1" = "results" ]; then
  echo "Please choose another name for your output"
  exit 0;
fi

dest="$1"
make
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib
./wf --self-organized-list sample-input.txt > results

if [ $? -eq 0 ];then
	echo "HW5: PASS" >> $dest
else
        echo "HW5: FAIL" >> $dest
	exit 1
fi

make clean
rm -f results
