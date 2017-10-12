#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi
dest="$1"
#Generate the students assignment
#for this assignment we should have a make file
#which will put the library in a folder called lib
make

#Run the grader. 0 exit status is a pass
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib
./grader
if [ $? -eq 0 ];then
	echo "Passed the base grader, running more tests"
else
        echo "HW6: FAIL - base grader" >> $dest
	exit 1
fi

#Check to make sure we created the log file
if [ -e "ring.log" ]; then
    echo "Created a log file"
else
    echo "HW6: FAIL - Did not create the log file" >> $dest
    exit 1
fi

#If we got here we passed
echo "HW6: PASS" >> $dest
