
@SuppressWarnings("serial")
public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

	/*
 	* Initializes pos, expected, and found
 	*
 	* @param int position of token.
 	* @param Token expected token.
 	* @param Token found 
 	*/ 
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

	/*
 	* toString that returns exception 
 	*
 	* @return String exception
 	*/ 
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
