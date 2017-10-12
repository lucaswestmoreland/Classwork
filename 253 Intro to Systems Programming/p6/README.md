#Project 6

Author: Lucas Westmoreland
Class: CS253 Section 2

##Overview

In this project, I implement a ring buffer that focuses on a fast form of data storage.    

##Compiling and Using

Compile this program in the main directory with the Makefile given

	$ make

Then run

	$ ./run.sh


##Testing

Testing this program involved different checks on the log_msg() function. These test descriptions were detailed out in the project description. In addition, the warnings and errors that were printed to the console were good indications of where I needed to examine my code in order to fix the problem. I wasn't able to implement some of them however and I'm still a little confused on how to handle those certain tests.
 
##Discussion

The biggest concern for me was some of the tests that were asked to be implemented, and the output that goes into the
logfile that I am creating. First of all, I don't understand what necessary additions to the code I need to add in order
to not have those error messages popping up on my logfile. There were a few that I got to not show up, but the majority
stayed on. Also, specifically the test case that involved checking to see if the index value overflowed was very confusing to me because I looked at the code given to us regarding the index value and it is constantly being "modulo'd" by the MAX_LOG_ENTRY value meaning that the index value can't get very high. Also, the second to last test listed in the desciption
talking about checking to see if I am trying to log at the exast same time was something that I just did not understand. 
In all honesty, I mainly just made sure I was passing the base grader and cleaning up the code as best I could because
I felt like the instructions were a little vague and hard to understand.

If you used any sources outside of the lecture notes, class lab files, or text book you need to list them here. If you looked something up on stackoverflow.com and fail to cite it in this section it will be considered plagiarism and be dealt with accordingly. So be safe CITE! Here is some help creating links
