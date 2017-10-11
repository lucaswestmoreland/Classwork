public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

	/*
 	* Initialize the expression
 	*
 	* @param NodeExpr expr
 	*/
    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
