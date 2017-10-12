#Project 7

Author: Lucas Westmoreland
Class: CS253 Section 2

##Overview

In this project, I implement a ring buffer that focuses on a fast form of data storage. In addition to this 
implementation, I added numerous mutexes as a safety precaution when reading and writing data. Finally, I implemented
an asynchronous form of the ring buffer utilizing the pthread library.     

##Compiling and Using

Compile this program in the main directory with the Makefile given

	$ make

Then run

	$ ./run.sh


##Testing

In testing the functionality of the mutexes, I placed them in different places within the functions to see where they
would work and where they would fail. I believe that I placed the mutexes in the proper locations however I may have been
able to position them closer to the for loops where the data was actually being written. I left some of them spaced
a little farther out just because I would rather be safe than sorry.

In testing the implementation of the pthread library, I had to check what my output was in the ring.log. This will be
touched on more in the discussion section, however I could not get the threads that I created to print my desired
log messages.

##Discussion

P7, even though still being a little vague in my opinion, allowed me to understand what we were doing in P6 a little
better. With regards to the pthread library, I believe that I implemented the pthread_create and the pthread_join functionscorrectly. With that being said, I could not get the threads to output the proper log messages. I believe that this
was because I only called the pointer to the function init_buffer. This meant that I initialized the buffer correctly
in each thread, it just didn't have any messages to log. This was seen in the output of ./run.sh when it listed
every log_msg that was in the main function in test.c. After that, it printed "Initializing buffer" (or something
like that), three consecutive times, without printing any log messages. The makefile, mutexes, and the asynchronous
aspect of the pthread library I believe were all implemented correctly.

##Citations

I referenced the example in /threads of Marissa's examples that showed me how to properly create and join the threads.
