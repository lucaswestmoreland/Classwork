package fa.dfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fa.FAInterface;
import fa.State;


/**
 * This is the DFA.java class file.
 * @author lucaswestmoreland
 *
 */
public class DFA implements FAInterface, DFAInterface {

	private Set<State> states, finals;
	private Set<DFAState> Q;
	private Set<DFAState> F; //final states
	private DFAState q0; //start state
	private Set<DFAState> otherStates; //other states for toString purposes
	private ArrayList<DFAState> stateArray = new ArrayList<>(); //toString
	private Set<Character> sigma; //alphabet
	//	private boolean finalState;



	/**
	 * Constructor for a DFA
	 */
	public DFA() {
		Q = new HashSet<DFAState>();
		F = new HashSet<DFAState>();
		otherStates = new HashSet<DFAState>();
		sigma = new HashSet<Character>();

	}

	/**
	 * Adds the initial state to the DFA instance
	 * @param name is the label of the start state
	 */
	@Override
	public void addStartState(String name) {
		q0 = new DFAState(name);
		Q.add(q0);
		stateArray.add(q0);
		Iterator<DFAState> i = F.iterator();
		while(i.hasNext())
		{
		if(i.next().getName().equals(q0.getName()))
				{
					System.out.println("***Unfortunately, this DFA cannot handle when a final state is also the start state :(***");
					
				
		}
	}
	}

	/**
	 * Adds a non-final, not initial state to the DFA instance
	 * @param name is the label of the state 
	 */
	@Override
	public void addState(String name) {
		DFAState state = new DFAState(name);
		Q.add(state);
		otherStates.add(state);
		stateArray.add(state);



	}

	/**
	 * Adds a final state to the DFA
	 * @param name is the label of the state
	 */
	@Override
	public void addFinalState(String name) {

		DFAState finalState = new DFAState(name);
		F.add(finalState);
		Q.add(finalState);
		stateArray.add(finalState);
		
	
		}
	


	public boolean isFinal(DFAState state) {
		Iterator<DFAState> i = F.iterator();
		while(i.hasNext())
		{
			if(i.next().getName().equals(state.getName()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the transition to the DFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param onSymb is the symbol from the DFA's alphabet.
	 * @param toState is the label of the state where the transition ends
	 */
	@Override
	public void addTransition(String fromState, char onSymb, String toState) {

		if(!sigma.contains(onSymb))
		{
			sigma.add(onSymb);
		}


		//		DFAState state = new DFAState(fromState);
		Iterator<DFAState> i = Q.iterator();
		while(i.hasNext())
		{
			DFAState tState = i.next();
			if(tState.getName().equals(fromState))
			{

				tState.addTransition(onSymb, toState);
				return;
			}

		}
	}

	/**
	 * Getter for q0
	 * @return the start state of FA
	 */
	@Override
	public DFAState getStartState() {
		return q0;
	}

	/**
	 * Uses transition function delta of FA
	 * @param from the source state
	 * @param onSymb the label of the transition
	 * @return the sink state.
	 */
	@Override
	public DFAState getToState(DFAState fromState, char onSymb) {

		Iterator<DFAState> i = Q.iterator();
		DFAState tState = new DFAState();
		while(i.hasNext())
		{
			tState = i.next();
			if(tState.getName().equals(fromState.getName()))
			{
				tState = tState.getTransition().get(onSymb);
				return tState;
			}

		}
		
		System.out.println("Invalid sigma value detected");
		return null;


	}

	/**
	 * Construct the textual representation of the DFA, for example
	 * A simple two state DFA
	 * Q = { a b }
	 * Sigma = { 0 1 }
	 * delta =
	 *		0	1	
	 *	a	a	b	
	 *	b	a	b	
	 * q0 = a
	 * F = { b }
	 * 
	 * The order of the states and the alphabet is the order
	 * in which they were instantiated in the DFA.
	 * @return String representation of the DFA
	 */
	@SuppressWarnings("unused")
	public String toString() {

		String finalStates = "";
		String oStates = "";
		String qString = "";
//		String transitionString = "";
		String sigString = "";
		String finalString = "";
		String initialString = "";
		String deltaString = "";
		String qStringOnlyNumbers = "";
		String sigStringOnlyNumbers = "";

		Iterator<DFAState> fi = F.iterator();
		while(fi.hasNext())
		{
			finalStates += fi.next().getName();
		}
		
		finalStates = alphabetize(finalStates);
		

		


		qString += "Q = { " + finalStates;
		qString += q0.getName() + " ";

		Iterator<DFAState> oi = otherStates.iterator();
		while(oi.hasNext())
		{
			 oStates += oi.next().getName();
		}
		
		oStates = alphabetize(oStates);
		
		qString += oStates + "}";
		
		qStringOnlyNumbers += finalStates + q0.getName() + " " + oStates; 
		
		

		Iterator<Character> si = sigma.iterator();
		while(si.hasNext())
		{
			sigString += si.next().charValue() + " ";
		}
		
		
		sigStringOnlyNumbers += sigString;
		sigString = "Sigma = { " + sigStringOnlyNumbers + "}";
		
		sigStringOnlyNumbers = sigStringOnlyNumbers.replaceAll("\\s","");
		



		deltaString += "delta = " + "\n\t";
		
		for(int i = 0; i < sigStringOnlyNumbers.length(); i++)
		{
			deltaString +=  "\t" + sigStringOnlyNumbers.charAt(i);
		}
		
		deltaString += "\r\t";
		
		for(int i = 0; i < stateArray.size(); i++)
		{
			deltaString += stateArray.get(i).getName() + "\t";
			
			for(Character c : sigStringOnlyNumbers.toCharArray())
			{
				deltaString += stateArray.get(i).getNextState(c) + "\t";
			}
			
			if(i != stateArray.size()-1)
			{
			deltaString += "\n\t";
			}
		}
		
		deltaString += "\n";
		
		
		
		
		
		initialString += "q0 = " + q0.getName();
		finalStates = "F = { " + finalStates + "}";
		
		
		finalString += qString + "\n" + sigString + "\n" + deltaString + initialString + "\n" + finalStates + "\n";
		return finalString;
		
		
		
		

	}
	/**
	 *  checks to see if the DFAState has transitions assigned to it
	 * @param fromState
	 * @param onSymb
	 * @return
	 */
	public boolean checkTransition(DFAState fromState, char onSymb)
	{

		if(fromState.getTransition().get(onSymb) != null)
		{
			return true;
		}

		return false;
	}


	/**
	 * Simulates a DFA on input s to determine
	 * whether the DFA accepts s.
	 * @param s - the input string
	 * @return true if s in the language of the DFA and false otherwise
	 */
	@Override
	public boolean accepts(String s) {


		if(s.charAt(0) == 'e')
		{
			if(isFinal(q0))
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		DFAState state = new DFAState(q0.getName());

		//		if(checkTransition(q0, s.charAt(0)))
		//		{
		//			state = this.getToState(q0, s.charAt(0));
		//		}
		//		else
		//		{
		//			return false;
		//		}



		for (int i = 0; i < s.length();i++)
		{
			state = getToState(state, s.charAt(i));
			
			if(state == null)
			{
				System.out.println("Invalid sigma value detected. The program will now stop.");
				//System.exit(1);
			}
		}

		if(isFinal(state))
		{
			return true;
		}


		return false;
	}

	/**
	 * Getter for Q
	 * @return a set of states that FA has
	 */
	@Override
	public Set<State> getStates() {
		states.addAll(Q);
		return states;
	}

	/**
	 * Getter for F
	 * @return a set of final states that FA has
	 */
	@Override
	public Set<State> getFinalStates() {
		finals.addAll(F);
		return finals;
	}

	/**
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
	@Override
	public Set<Character> getABC() {
		return sigma;
	}

	/**
	 * Helper method that I implemented for the toString. 
	 * I got this from stack overflow but it's only to properly alphabetize.
	 * @param str
	 * @return alphabetized string
	 */
	public String alphabetize(String str)
	{
		Character[] chars = new Character[str.length()];
		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = str.charAt(i);
		}

		Arrays.sort(chars, new Comparator<Character>() {
			public int compare(Character c1, Character c2) 
			{
				int cmp = Character.compare(Character.toLowerCase(c1.charValue()), Character.toLowerCase(c2.charValue()));
				if (cmp != 0) return cmp;
				return Character.compare(c1.charValue(), c2.charValue());
			}
		});

		StringBuilder sb = new StringBuilder(chars.length);
		for (char c : chars) sb.append(c + " ");
		str = sb.toString();
		
		return str;
	}

}
