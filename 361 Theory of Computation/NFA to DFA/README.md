*****************************
* Lucas Westmoreland        *
* Elena Sherman             *
* CS 361                    *
* 23 March 2017             *
* Project 2: NFA            *
*****************************

#Project Overview

In this program, I model an instance of a nondeterministic finite automaton (NFA) and I 
include an instance method that produces an equivalent deterministic finite automaton (DFA).

#Compilation and Execution

To compile fa.nfa.NFADriver from the top directory of these files:
	
	$ javac fa/nfa/NFADriver.java
	
To run fa.nfa.NFADriver:

	$ java fa.nfa.NFADriver ./tests/p2tc0.txt	
	
	
#Complications and Testing

 In order to figure out how to handle the epsilon transitions, I wasn't able to implement the eClosure method. 
 I tried to take in one parameter and then apply a global variable for the list of epsilon transitions, 
 but for some reason it wasn't working correctly. When I tried taking in the set that I was adding to, 
 it worked much better. When I initially had it set up as a global variable, 
 I was passing way more tests than I was supposed to be passing.
 
 I really liked the fact that we were able to transfer much of the DFA material over to the NFA class. 
 Also, the conceptual review we did in class regarding NFA's was not only helpful,
 but completely necessary in the completion of this program. Though I am submitting late, I genuinely 
 feel as though I have a solid grasp of the relationship between the removal of
 the epsilon transitions, and the corresponding DFA that results from it.
 
 I didn't add any additional testing because I ran out of time and I also feel confident enough to submit 
 just passing the 4 given tests.