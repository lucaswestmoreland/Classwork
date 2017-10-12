package fa.nfa;
import java.util.HashMap;
import java.util.HashSet;
import fa.State;

/*
 *Implementation of NFAState
 *
 *@author lucaswestmoreland
 *
 */
public class NFAState extends State {

	private HashMap<Character, HashSet<NFAState>> transitions;
	private boolean isFinal;


	/**
	 * Constructor for NFAState
	 * @param name
	 */
	public NFAState(String name) {
		this.name = name;
		isFinal = false;
		transitions = new HashMap<>();
	}

	/**
	 * Constructor specifically for a final state
	 * @param name
	 * @param isFinal
	 */
	public NFAState(String name, boolean isFinal) {
		this.name = name;
		this.isFinal = isFinal;
		transitions = new HashMap<>();
	}

	/**
	 * Decides if the state is a final state
	 * @return
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * Gets the transitions and returns them
	 * @return
	 */
	public HashMap<Character, HashSet<NFAState>> getTransitions()
	{
		return transitions;
	}

	/**
	 * Adds the transitions to a given state. Will make a new set of transitions and add to it if there are none
	 * initially associated with the given state
	 * @param onSymb
	 * @param toState
	 */
	public void addTransition(char onSymb, NFAState toState) {
		HashSet<NFAState> states = transitions.get(onSymb);
		if (states != null) {
			states.add(toState);
			transitions.put(onSymb, states);
		} 
		else {
			states = new HashSet<NFAState>();
			states.add(toState);
			transitions.put(onSymb, states);

		}
	}

	/**
	 * Get the location that the transition leads to from a given state based on the input
	 * @param symb
	 * @return
	 */
	public HashSet<NFAState> getToStates(char symb) {
		HashSet<NFAState> transition = transitions.get(symb);
		if (transition == null)
			return new HashSet<NFAState>();

		return transition;
	}

}
