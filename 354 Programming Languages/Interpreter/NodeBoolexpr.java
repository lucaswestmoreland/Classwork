
public class NodeBoolexpr {

    private final NodeExpr expr1;
    private final String relop;
    private final NodeExpr expr2;
    
    public NodeBoolexpr(NodeExpr expr1, String relop, NodeExpr expr2)
    {

        this.expr1 = expr1;
        this.relop = relop;
        this.expr2 = expr2;
    }
    
    /**
     * Evaluates the boolean expression for the given environment
     * @param env
     * @return - boolean
     * @throws EvalException 
     */
    public boolean eval(Environment env) throws EvalException
    {
        double lhv = expr1.eval(env);
        double rhv = expr2.eval(env);

        switch (relop)
        {
        	case "<":
        		return lhv <  rhv;
        	case ">":
        		return lhv >  rhv;           
            case "<=":
                return lhv <= rhv;
            case ">=":
                return lhv >= rhv;
            case "<>":
                return lhv != rhv;
            case "==":
                return lhv == rhv;

            default:
                throw new EvalException(0,"Not a relational operation." );
        }
    }
}
