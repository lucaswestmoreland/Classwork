*************************************
*Name: Lucas Westmoreland           *
*Instructor: Elena Sherman          *
*Class: Computer Science 361        *
*Date: 14 February 2017             *
*************************************

## Project 1: Deterministic Finite Automaton

# Compilation and program execution
 
 Navigate into each folder and be sure all .java files are compiled
 		
 		$javac *.java
 		
 Navigate into the dfa folder where the driver is located and execute with a test
 in the command line arguments:
 
 		$java DFADriver "sampleFileName.txt"
 		
 		
#Program description

 This program builds a deterministic finite automaton off of a given input file. The input file
 provides the details regarding how the machine should operate. The DFA.java class imports all of
 the methods associated with the FAinterface and DFAinterface and adds a few more.
 
#Testing and development

 In the completion of this program, I look back and observe that most of the time spent in design, was spent on
 insuring that the toString() method was exactly how it needed to be. It became a little bit messy at times but it
 was because I tried going about the implementation of it in many different ways. I drafted a couple extra test cases 
 to handle certain scenarios, however one specific scenario, I could not fix.
 
 Specifically, the instance where the final state is also the start state. Having this situation caused many odd reactions 
 that I really did not know how to fix. I wound up just handling the exact instance of when this happens and just quit out of
 the program. I figure this would be a better alternative than just printing out a funky toString that doesn't make any logical
 sense.   		