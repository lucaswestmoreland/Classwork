/**
 * This is the implementation of the relational operators
 * 
 *
 */

public class NodeRelop extends Node {
	private String relop;
    private boolean bool;

    public NodeRelop(int pos, String relop) {
	this.pos=pos;
	this.relop=relop;
    }

    public boolean op(double o1, double o2) throws EvalException {

	
	if (relop.equals("<")){
		bool = o1 < o2;
		return bool;
	}
	
	if (relop.equals("<=")){
		bool = o1 <= o2;
		return bool;
	}
	if (relop.equals(">")){
		bool = o1 > o2;
		return bool;
	}
	   
	if (relop.equals(">=")){
		bool = o1 >= o2;
		return bool;
	}
	
	if (relop.equals("<>")){
		bool = o1 != o2;
		return bool;
	}
	
	if (relop.equals("==")){
		bool = o1 == o2;
		return bool;
	}
			
	throw new EvalException(pos,"not a relational operator: "+relop);
    }
}
