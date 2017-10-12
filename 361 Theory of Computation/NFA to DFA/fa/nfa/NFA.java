package fa.nfa;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import fa.FAInterface;
import fa.State;
import fa.dfa.DFA;

/*
 * This is my implementation of the NFA class that properly inherits everything from the FAInterface and NFAInterface. This class is capable
 * of constructing an NFA and also capable of converting a given DFA into its respective NFA.
 *    
 *  @author lucaswestmoreland
 *     
 */
public class NFA implements FAInterface, NFAInterface {


	private Set<NFAState> Q, F;	//all states Q, and final states F
	private NFAState q0; //start state
	private Set<Character> sigma; //alphabet


	/**
	 * Constructor for an NFA
	 */
	public NFA() {
		Q = new HashSet<NFAState>();
		F = new HashSet<NFAState>();
		sigma = new HashSet<Character>();
	}

	/**
	 * Adds the initial state to the DFA instance
	 * @param name is the label of the start state
	 */
	@Override
	public void addStartState(String name) {
		NFAState s = getState(name);
		if (s == null) {
			s = new NFAState(name);
			Q.add(s);
		}
		q0 = s;
	}

	/**
	 * Adds a non-final, not initial state to the DFA instance
	 * @param name is the label of the state 
	 */
	@Override
	public void addState(String name) {
		NFAState s = new NFAState(name);
		Q.add(s);
	}

	/**
	 * Adds a final state to the NFA
	 * @param name is the label of the state
	 */
	@Override
	public void addFinalState(String name) {
		NFAState s = new NFAState(name, true);
		F.add(s);
		Q.add(s);
	}

	/**
	 * Checks a given state to see if it is final
	 * @param s
	 * @return
	 */
	public boolean isFinal(State s) {
		for (NFAState nfa : Q) {
			if (nfa.getName().equals(s.getName())) {
				return nfa.isFinal();
			}
		}
		return false;
	}

	/**
	 * Adds the transition to the NFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param onSymb is the symbol from the DFA's alphabet.
	 * @param toState is the label of the state where the transition ends
	 */
	@Override
	public void addTransition(String fromState, char onSymb, String toState) {

		(getState(fromState)).addTransition(onSymb, getState(toState));
		if (!sigma.contains(onSymb) && onSymb != 'e') {
			sigma.add(onSymb);
		}
	}

	/**
	 * Getter for q0
	 * @return the start state of FA
	 */
	@Override
	public NFAState getStartState() {
		return q0;
	}

	/**
	 * Checks and returns a state with the given label has already been created. If not created,
	 * method returns null.
	 * @param name
	 * @return
	 */
	private NFAState getState(String name) {
		NFAState ret = null;
		for (NFAState s : Q) {
			if (s.getName().equals(name)) {
				ret = s;
				break;
			}
		}
		return ret;
	}

	/**
	 * This method adds the relevant states and transitions from an NFA and converts it to the respective DFA.
	 */
	@Override
	public DFA getDFA() {
		DFA dfa = new DFA();
		LinkedList<HashSet<NFAState>> queue = new LinkedList<HashSet<NFAState>>();
		HashSet<Set<NFAState>> confirmedSets = new HashSet<Set<NFAState>>();
		HashSet<NFAState> startSet = new HashSet<NFAState>();

		startSet.add(q0);
		startSet.addAll(eClose(q0, new HashSet<NFAState>())); //handles the epsilon transitions associated with the given start state

		boolean isFinalState = false;
		for (NFAState nfa : startSet) {
			if (nfa.isFinal()) {
				isFinalState = true;
			}
		}

		if (isFinalState == true) {
			dfa.addFinalState(startSet.toString());
		}

		dfa.addStartState(startSet.toString());
		queue.addFirst(startSet);
		while (!queue.isEmpty()) {
			HashSet<NFAState> current = queue.removeLast();
			confirmedSets.add(current);

			for (Character c : sigma) { //for each character in the alphabet
				isFinalState = false;
				HashSet<NFAState> updatedState = new HashSet<NFAState>();

				for (NFAState nfa : current) { //for each nfa state in the current set
					HashSet<NFAState> transitions = nfa.getToStates(c);

					if (transitions != null) { //if there is at least one transition associated with this value in the NFA

						for (NFAState transition : transitions) { //for each transition in the set of transitions
							eClose(transition, updatedState); //Handle each epsilon transition with this spot in the NFA

							if (transition.isFinal()) {
								isFinalState = true;
							}
							updatedState.add(transition);
						}
					}
				}

				String currentSet = current.toString();
				String newState = updatedState.toString();

				if (isFinalState == true) {
					if (!queue.contains(updatedState) && !confirmedSets.contains(updatedState)) { //This means the state is the final state
						dfa.addFinalState(newState);
					}
					dfa.addTransition(currentSet, c, newState);
				} 
				else {
					if (!queue.contains(updatedState) && !confirmedSets.contains(updatedState)) { //This means the state is not the final state
						dfa.addState(newState);
					}
					dfa.addTransition(currentSet, c, newState);
				}
				if (!queue.contains(updatedState) && !confirmedSets.contains(updatedState)) { //Always becomes the first state until a new one is added
					queue.addFirst(updatedState);

				}
			}
		}
		return dfa;
	}

	/**
	 * Traverses all epsilon transitions and determines
	 * what states can be reached from s through e
	 * @param state
	 * @param path
	 * @return set of states that can be reached from s on epsilon trans.
	 */
	private HashSet<NFAState> eClose(NFAState state, HashSet<NFAState> path) {
		HashSet<NFAState> temp = state.getToStates('e'); //finds the route to the epsilon transition
		if (temp != null) {

			for (NFAState nfa : temp) { //for each nfa state inside the temporary set
				NFAState current = nfa;
				path.add(current); //add the epsilon transitions to the path set
				eClose(current, path); //do it again until no more transitions in the given state
			}
		}
		return path;
	}

	@Override
	/**
	 * Return delta entries
	 * @param from - the source state
	 * @param onSymb - the label of the transition
	 * @return a set of sink states
	 */
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getToStates(onSymb);
	}

	/**
	 * I was not able to implement the eClosure method so I did the logic in the eClose method. I
	 * needed an extra parameter in order to make it functional.
	 */
	@Override
	public Set<NFAState> eClosure(NFAState s) {

		return null;
	}

	/**
	 * Getter for Q
	 * @return a set of states that FA has
	 */
	@Override
	public Set<State> getStates() {
		Set<State> returnStates = new HashSet<State>();
		returnStates.addAll(Q); //Add all states to the returnStates set
		return returnStates;
	}

	/**
	 * Getter for F
	 * @return a set of final states that FA has
	 */
	@Override
	public Set<State> getFinalStates() {
		Set<State> finalStates = new HashSet<State>();
		for (NFAState state : Q) { //For each in states, choose the final states
			if (state.isFinal()) {
				finalStates.add(state);
			}
		}
		return finalStates;
	}

	/**
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
	@Override
	public Set<Character> getABC() {
		return sigma;
	}


}
