
public class NodeFactNeg extends NodeFact {

	private NodeFact negFact;
	
/**
 * Initializes the Negative Fact
 * @param fact
 */
	public NodeFactNeg(NodeFact fact)
	{
		this.negFact = fact;
	}
	
	public double eval(Environment env) throws EvalException
	{
		return - negFact.eval(env);
	}
	
}
