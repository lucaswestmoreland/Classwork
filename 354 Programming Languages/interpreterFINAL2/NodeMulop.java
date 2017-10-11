public class NodeMulop extends Node {

    private String mulop;

	/*
 	* Sets the position of the token and multiplication operator
 	*
 	* @param int position of token
 	* @param String operator
 	*/
    public NodeMulop(int pos, String mulop) {
	this.pos=pos;
	this.mulop=mulop;
    }

	/*
 	* Performs the multiplication operation on two integers
 	* Throws an exception if condition isn't met
 	*
 	* @param double first
 	* @param double second
 	* @return result
 	* @throws EvalException
 	*/ 
    public double op(double o1, double o2) throws EvalException {
	if (mulop.equals("*"))
	    return o1*o2;
	if (mulop.equals("/"))
	    return o1/o2;
	throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
