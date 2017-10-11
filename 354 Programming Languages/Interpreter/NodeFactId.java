public class NodeFactId extends NodeFact {

    private String id;

	/*
 	* Initializes the position and id
 	*
 	* @param int pos
 	* @param String id
 	*/
    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }

    public double eval(Environment env) throws EvalException {
	return env.get(pos,id);
    }

}
