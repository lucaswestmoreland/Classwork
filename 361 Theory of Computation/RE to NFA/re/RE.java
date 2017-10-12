package re;

import java.util.HashSet;
import java.util.Set;

import fa.State;
import fa.nfa.NFA;
import fa.nfa.NFAState;

/**
 * This class is an interpretation of regular expressions (a sequence of characters that define a search pattern). Most notably, this
 * class will take in a Regular Expression, and print the resulting NFA associated with it.
 * 
 * @author Lucas Westmoreland | with much of the parsing logic deriving from: http://matt.might.net/articles/parsing-regex-with-recursive-descent/
 *
 */
public class RE implements REInterface {

	private String regEx;
	private static int KEYCODE = 1;

	/**
	 * Constructor Method for Regular Expression
	 * @param regEx
	 */
	public RE(String regEx) {
		this.regEx = regEx;
	}

	/**
	 * Returns an NFA from a regular expression
	 */
	public NFA getNFA() {
		NFA nfa = regex();
		return nfa;
	}

	/**
	 * Calls term() and checks to see if OR operation exists
	 * @return
	 */
	private NFA regex() {
		NFA term = term(); 
		if(more() && peek() == '|') 
		{
			eat('|');
			NFA regex = regex(); 
			return choice(term, regex);
		}
		else
		{
			return term;
		}
	}

	/**
	 * concatenates the factors while there are still factors left
	 * @return
	 */
	private NFA term() {
		NFA factor = new NFA();
		while (more() && peek() != ')' && peek() != '|') 
		{
			NFA nextFactor = factor();

			if(!factor.getStates().isEmpty())
			{
				factor = concat(factor, nextFactor);	
			}
			else
			{
				factor = nextFactor;		
			}
		}
		return factor;
	}

	/**
	 * Calls base() and checks for Kleene Star operation
	 * @return
	 */
	private NFA factor() {

		NFA base = base();
		while (more() && peek() == '*') 
		{
			eat('*') ;
			base = star(base);
		}
		return base ;
	}

	/**
	 * Checks for open paren and will otherwise return the next symbol
	 * @return
	 */
	private NFA base() {

		switch (peek()) {
		case '(':
			eat('(') ;
			NFA r = regex() ;  
			eat(')') ;
			return r ;
		default:
			return symbol(next());
		}
	}

	/**
	 * Concatenates one NFA to another and returns the resulting NFA
	 * @param thisNFA
	 * @param thatNFA
	 * @return
	 */
	private NFA concat(NFA thisNFA, NFA thatNFA)
	{
		thisNFA.addAbc(thatNFA.getABC()); //merge the alphabets
		String thatNFAStartStates = thatNFA.getStartState().getName(); //get start states of that NFA
		Set<State> thisNFAFinalStates = thisNFA.getFinalStates(); //get final states of this NFA
		thisNFA.addNFAStates(thatNFA.getStates()); //add the states from that NFA to this NFA


		for(State finalState : thisNFAFinalStates) //for each final state in the list of this NFA's final states, remove its final status and draw an
		{										   //epsilon transition that goes from the old final state to the start states of that NFA.
			NFAState oldFinalState = (NFAState) finalState;
			oldFinalState.setNonFinal();
			thisNFA.addTransition(oldFinalState.getName(), 'e', thatNFAStartStates);
		}

		return thisNFA;
	}

	/**
	 * This method performs the Kleene Star operation for a Regular Expression
	 * @param nfa
	 * @return
	 */
	private NFA star(NFA nfa)
	{
		NFA newNFA = new NFA();

		newNFA.addAbc(nfa.getABC()); //merge alphabets
		String newStart = "" + KEYCODE++;
		String newFinal = "" + KEYCODE++;
		newNFA.addStartState(newStart);
		newNFA.addFinalState(newFinal);
		newNFA.addNFAStates(nfa.getStates());

		for(State finalState : nfa.getFinalStates()) //for each state in the list of final states, remove it's final status and draw an
		{											 //epsilon transition that goes from the old final state to the new final state as well
			NFAState oldFinalState = (NFAState) finalState;//as a transition from the old final state to the start state
			oldFinalState.setNonFinal();
			newNFA.addTransition(oldFinalState.getName(), 'e', newFinal);
			newNFA.addTransition(oldFinalState.getName(), 'e', nfa.getStartState().getName());
		}

		newNFA.addTransition(newStart, 'e', newFinal); //add an epsilon transition from the new start state to the new final state
		newNFA.addTransition(newStart, 'e', nfa.getStartState().getName()); //add an epsilon transition from the new start state to the old start state

		return newNFA;
	}

	/**
	 * Returns the resulting NFA when the OR symbol is found
	 * @param thisNFA
	 * @param thatNFA
	 * @return
	 */
	private NFA choice(NFA thisNFA, NFA thatNFA)
	{
		NFA newNFA = new NFA();

		String newStart = "" + KEYCODE++;
		String newFinal = "" + KEYCODE++;
		newNFA.addStartState(newStart);
		newNFA.addFinalState(newFinal);


		newNFA.addNFAStates(thisNFA.getStates());
		newNFA.addNFAStates(thatNFA.getStates());


		for(State finalState : thisNFA.getFinalStates()) 
		{
			NFAState ourFinal = (NFAState) finalState;
			ourFinal.setNonFinal();
			newNFA.addTransition(ourFinal.getName(), 'e', newFinal);
		}

		for(State finalState : thatNFA.getFinalStates()) 
		{
			NFAState ourFinal = (NFAState) finalState;
			ourFinal.setNonFinal();
			newNFA.addTransition(ourFinal.getName(), 'e', newFinal);
		}


		String oldStartThis = thisNFA.getStartState().getName();
		String oldStartThat = thatNFA.getStartState().getName();

		newNFA.addTransition(newStart, 'e', oldStartThis);
		newNFA.addTransition(newStart, 'e', oldStartThat);

		newNFA.addAbc(thisNFA.getABC());
		newNFA.addAbc(thatNFA.getABC());		

		return newNFA;
	}

	/**
	 * Returns an NFA from a given symbol
	 * @param c
	 * @return
	 */
	private NFA symbol(char c)
	{
		NFA newNFA = new NFA();
		//create an alphabet for the new NFA and add it
		Set<Character> alphabet = new HashSet<Character>();
		alphabet.add(c);
		newNFA.addAbc(alphabet);

		String newStart = "" + KEYCODE++;
		String newFinal = "" + KEYCODE++;
		newNFA.addStartState(newStart);
		newNFA.addFinalState(newFinal);
		newNFA.addTransition(newStart, c, newFinal); //create a transition between the start state and the final state

		return newNFA;
	}

	/**
	 * returns the next item of input without consuming it
	 * @return
	 */
	private char peek()
	{
		return regEx.charAt(0);
	}

	/**
	 * returns the next item of input and consumes it
	 * @return
	 */
	private char next()
	{
		char c = peek();
		eat(c);
		return c;		
	}

	/**
	 * Consumes the next item of input, failing if not equal to item.
	 * @param c
	 */
	private void eat(char c)
	{
		if(peek() == c)
		{
			this.regEx = this.regEx.substring(1);
		}
		else
		{
			throw new RuntimeException("Expected: " + c + "; got: " + peek());
		}
	}

	/**
	 * Checks to see if there are any more characters available in the string
	 * @return
	 */
	private boolean more()
	{
		return regEx.length() > 0;
	}	
}
