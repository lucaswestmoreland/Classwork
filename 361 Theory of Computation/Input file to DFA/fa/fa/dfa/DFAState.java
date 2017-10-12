package fa.dfa;

import java.util.HashMap;
import java.util.Map;

import fa.State;



/**
 * This is the class file for a DFAState object that we use in the 
 * DFA.java class
 * @author lucaswestmoreland
 *
 */
public class DFAState extends State {
	
	protected String name;
	protected HashMap<Character, DFAState> transitions;
	protected Map<Character, String> transitionString = new HashMap<Character, String>(); //toString purposes

	/**
	 * Constructor for DFAState
	 */
	public DFAState() {
		transitions = new HashMap<Character, DFAState>();
	}
	/**
	 * Conctructor for DFAState
	 * @param name
	 */
	public DFAState(String name) {
		this.name = name;
		transitions = new HashMap<Character, DFAState>();
		}
	
	/**
	 * Adds the transition to the DFA's delta data structure. This is a helper for the addTransition located in the DFA.java class
	 * @param onSymb is the symbol from the DFA's alphabet.
	 * @param toState is the label of the state where the transition ends
	 */
	public void addTransition(char onSymb, String toState) {
		DFAState state = new DFAState(toState);
		transitions.put(onSymb, state);
		transitionString.put(onSymb, toState);
	}
	
	/**
	 * Accessor method for the transitions
	 * @return
	 */
	public HashMap<Character, DFAState> getTransition() {
		return transitions;
	}
	
	/**
	 * Accessor method for the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Helper method for toString()
	 * @param c
	 * @return
	 */
	public String getNextState(char c)
	{
		return this.transitionString.get(c);
	}

	/**
	 * toString method for DFAState
	 */
	@Override
	public String toString() {
		return name;
	}

}
