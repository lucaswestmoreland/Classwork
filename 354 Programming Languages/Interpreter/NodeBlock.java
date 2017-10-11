
public class NodeBlock extends Node {

	private NodeStmt stmt;
	private NodeBlock block;

	/**
	 * This class represents a "block" that is separated by ;
	 * @param stmt
	 * @param block
	 */
	public NodeBlock(NodeStmt stmt, NodeBlock block){
		this.stmt = stmt;
		this.block = block;
	}

	/**
	 * Evaluates the block with a given environment
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException{
		if(block == null) {
			return stmt.eval(env);
		} 
		else {
			stmt.eval(env);
			return block.eval(env);
		}
	}
}
