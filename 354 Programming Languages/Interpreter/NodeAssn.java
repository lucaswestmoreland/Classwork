public class NodeAssn extends Node {

    private String id;
    private NodeExpr expr;

	/*
 	* initializes an id and expression
 	*
 	* @param String id
 	* @param NodeExpr expression
 	*/ 
    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }



    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
