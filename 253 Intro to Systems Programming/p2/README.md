#Project 2

Author: Lucas Westmoreland	
Class: CS253 Section 2

##Overview

This program reads in an unsigned integer and swaps the bytes of the value to convert it form big endian to 
little endian representation (or vice-versa). In addition, this program can take in a binary value and 
convert it between big endian an little endian. This program also displays the hexadecimal value of each.

##Compiling and Using

Make sure all of the files are in the same directory...

	$ make
	$ homework -i|-b  <x> (x = positive integer or binary value representation < 4294967295)

##Discussion

 Replicating the expected outcome:

  Getting the exact same output as the grader is what took the most time in writing this project. I had to format
  the printf statements to get the exact layout that the grader was asking for. A good friend of mine told me about
  a command line argument called "vimdiff" that allowed me to compare the actual and expected side by side. I wish 
  I would have been informed of this in class because it was very helpful to be able to see exactly where the 
  inequalities were located. In comparison to p1, I attempted to format the printf statements to be easier to
  read. I wish that in the future, there would be a way to grade the projects by not having to have the same, exact, 
  format as the grader, because that causes a lot of frusteration, even if the project is technically completed.

 The C Book:

  This book helped me better understand the bitwise operators. The getbits function that is provided in this book
  was thoroughly explained which allowed me to better understand what the function was actually doing.

 This project related to in class work:

  Doing the binary packet in class with the groups helped a great deal when dealing with binary representation in the 
  first place. Being able to easily convert a decimal value to binary was essential to the testing of the output. 
 
##Sources used

Some of the code used in project was provided by "C The Programming Language" by Brian Kernighan and Dennis Ritchie


