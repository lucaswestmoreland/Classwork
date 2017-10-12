#Project 3

Author: Lucas Westmoreland
Class: CS253 Section 2

##Overview

This program utilizes my knowledge of pointers, structs, and memory management to write, test, and implement a
Doubly Linked List.

##Compiling and Using

Make sure you are in the home directory and type into the console:

 $make (with given MakeFile)

then navigate to the libsrc directory and run:

 $UnitTestList

##Testing

The testing of this code was done through the given test files, as well as 
the UnitTestList file that I had to add to. I added as many tests as I could 
think of with the theme of testing a list with 0, 1, and 2 elements. There were 
also tests done to NULL lists that were implemented as well. 

Before I wrote any additional tests, I made sure that the SimpleTestList and RandomTestList, 
checked out. The errors and warning that I received when compiling the program were also effective
in finding out where all of my initial flaws were. Only after fixing these errors, was I able to use 
compile the program and thus further my testing. The final testing I did was the leak checks through 
valgrind. This was to make sure that I was properly freeing my memory.


##Discussion
The initial stage of writing this program was very difficult for me. The main reason 
is that I was not confident enough in the realm of pointers in C. This proved difficult
 when actually writing the functions themselves and getting the program to compile. 
The logic itself was a breeze for me. I fully understand how a Doubly Linked List should work 
and so when writing the functions like add/remove etc., I didn’t have a hard time with the 
mental model and of what was expected in the functions themselves. Mainly, it was just getting 
the program to compile and making sure I was using the right operators in declaring variables 
and relating the nodes to the list. 

Having a Java reference to the same program helped me add in the conditional statements needed 
in each function to ensure the minimum amount of struggle when writing the functions for the 
list itself. Since the segmentation fault error is pretty obscure. I utilized the accuracy of 
valgrind to determine where the potential leaks were in the program. The lack of exceptions in 
C was very unfortunate because I feel as though that luxury would have proven helpful in the 
development of this program. 

I didn’t get to add as many new test cases as I would have liked to, but I needed to get the project 
submitted and I did a little procrastination on the test writing. But I’m just happy that the list 
compiles and passes the grader. The project started clicking for me when I was able to get the syntax 
down with the correct operators for pointers and when I was able to properly free the memory and 
stop getting memory leaks. The in class examples that were done that showed us how to free an 
array of elements was somewhat translatable to this project as well and so that bit of 
information proved to be helpful.

##Sources used
I referenced stack overflow when trying to figure out how to free the memory in my while statement 
correctly for my free() function, however nothing was copied and it was just used for reference. 
Other than that, it was just in class examples and the textbook.

