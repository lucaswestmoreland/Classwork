
public class NodeFactNegative extends NodeFact {


	private NodeFact fact;

	/*
 	* Initializes the negative fact
 	*
 	* @param NodeFact fact
 	*/
	public NodeFactNegative(NodeFact fact){
		this.fact = fact;
	}

	public double eval(Environment env) throws EvalException{
	return -(double)fact.eval(env);
	}
}
