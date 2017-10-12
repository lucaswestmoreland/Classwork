# P1P3
* Lucas Westmoreland  		
* Elena Sherman       		
* CS 361              		
* 23 April, 2017     		
* P1P3: Regular Expressions 

##Project Overview:
 
 This program implements an interpretation of regular expressions. Mainly, it takes in a given 
 Regular Expression, and returns the resulting NFA.
 
##Compilation and Running:

 Ensure that you are in the outermost directory and input:
 
  $ javac -cp ".:./CS361FA.jar" re/REDriver.java
  
  And then run:
  
  $ java -cp ".:./CS361FA.jar" re.REDriver ./tests/p3tc1.txt
 
##Discussion and Testing

 Biggest hurdle of this program was figuring out how to do everything all in the RE.java file.
 I wanted to make new classes and define different manipulatable objects that would make integration
 of the various methods easier. 
  
 Testing of this program was done with the given tests, as well as the tests posted on piazza.
 
##Sources:

http://matt.might.net/articles/parsing-regex-with-recursive-descent/
