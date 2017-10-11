public class NodeAddop extends Node {

    private String addop;

	/*
 	* Constructor for the add operation node. sets the postion and the add operation token.
 	*
 	* @param int position of token. 
 	* @param String add operation.
 	*/ 
    public NodeAddop(int pos, String addop) {
	this.pos=pos;
	this.addop=addop;
    }

	/*
 	* Adds the two operands together if a valid condition
 	* Otherwise, throws an exception. 
 	*
 	* @param double first double
 	* @param double second double
 	* @return double result
 	* @throws EvalException
 	*/ 
    public double op(double o1, double o2) throws EvalException {
	if (addop.equals("+"))
	    return o1+o2;
	if (addop.equals("-"))
	    return o1-o2;
	throw new EvalException(pos,"bogus addop: "+addop);
    }

}
