// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.

public abstract class Node {

    protected int pos=0;
	
	/*
 	* Evaluates node in the tree.
 	*
 	* @param Environment
 	* @throws EvalException 
 	*/ 
    public double eval(Environment env) throws EvalException {
	throw new EvalException(pos,"cannot eval() node!");
    }

}
