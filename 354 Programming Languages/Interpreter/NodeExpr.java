public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

	/*
 	* Constructor that initializes 
 	* term, addopp, and expr
 	*
 	* @param NodeTerm term
 	* @param NodeAddop addop
 	* @param NodeExpr expr 
 	*/ 
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }

	/*
 	* append the parameter expression
 	*
 	* @param NodeExpr expr
 	*/ 
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }

 
    public double eval(Environment env) throws EvalException {
	return expr==null
	    ? term.eval(env)
	    : addop.op(expr.eval(env),term.eval(env));
    }

}