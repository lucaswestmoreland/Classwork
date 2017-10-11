////// This class is a recursive-descent parser,
////// modeled after the programming language's grammar.
////// It constructs and has-a Scanner for the program
////// being parsed.


//import java.util.Scanner;

/**
 * This program is a parser, that interprets the input based on the given grammar. 
 * @author lwestmor
 *
 */
public class Parser {

	private Scanner scanner;

	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	private int pos() {
		return scanner.pos();
	}



	private NodeAddop parseAddop() throws SyntaxException { //addop
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}
	
	private NodeMulop parseMulop() throws SyntaxException { //mulop
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}
	
	private String parseRelop() throws SyntaxException { //relop - I changed this to return a string so my switch case can work.
	if (curr().equals(new Token("<"))) {
		match("<");
		return new String("<");
	}
	
	if (curr().equals(new Token(">"))) {
		match(">");
		return new String(">");
	}
	
	if (curr().equals(new Token("<="))) {
		match("<=");
		return new String("<=");
	}

	if (curr().equals(new Token(">="))) {
		match(">=");
		return new String(">=");
	}
	
	if (curr().equals(new Token("<>"))) {
		match("<>");
		return new String("<>");
	}
	
	if (curr().equals(new Token("=="))) {
		match("==");
		return new String("==");
	}
	
	return null;
}

	private NodeFact parseFact() throws SyntaxException { //fact

		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactNeg(fact);
		}
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	private NodeTerm parseTerm() throws SyntaxException { //term
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	private NodeExpr parseExpr() throws SyntaxException { //expr
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	private NodeAssn parseAssn() throws SyntaxException { //assn
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	private NodeBoolexpr parseBoolexpr() throws SyntaxException { //boolexpr
		NodeExpr expr1 = parseExpr();
		String relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		NodeBoolexpr boolexpr = new NodeBoolexpr(expr1, relop, expr2);
		return boolexpr;
		
	}

	private NodeStmt parseStmt() throws SyntaxException { //stmt
		if (curr().equals(new Token("rd"))) {
			match("rd");
			Token id = curr();
			match("id");
			System.out.println(id.lex() + ": ");
			
			java.util.Scanner input = new java.util.Scanner(System.in);
			double value = input.nextDouble();
			NodeFactNum num = new NodeFactNum(Double.toString(value));
			
			NodeTerm term = new NodeTerm(num, null, null);
			NodeExpr expr = new NodeExpr(term, null, null);
			NodeAssn assn = new NodeAssn(id.lex(), expr);
			
			input.close();
			return new NodeStmt(assn);

		} 
		else if (curr().equals(new Token("wr"))) {
			match("wr");
			NodeExpr expr = parseExpr();
			
			return new NodeStmt(expr);
			
		} 
		else if (curr().equals(new Token("if"))) {
			match("if");
			NodeBoolexpr boolexpr = parseBoolexpr();
			match("then");
			NodeStmt thenStmt = parseStmt();
			if (curr().equals(new Token("else"))) {
				match("else");
				NodeStmt elseStmt = parseStmt();
				return new NodeStmt(new Token("if"), boolexpr, thenStmt,
						elseStmt);
			} 
			else {
				return new NodeStmt(new Token("if"), boolexpr, thenStmt, null);
			}

		} 
		else if (curr().equals(new Token("while"))) {
			match("while");
			NodeBoolexpr boolexpr = parseBoolexpr();
			match("do");
			NodeStmt doStmt = parseStmt();
			return new NodeStmt(new Token("while"), boolexpr, doStmt);
		} 
		else if (curr().equals(new Token("begin"))) {
			match("begin");
			NodeBlock block = parseBlock();
			match("end");
			return new NodeStmt(block);

		} 
		else {

			NodeAssn assn = parseAssn();
			
			NodeStmt stmt = new NodeStmt(assn);
			return stmt;
		}
		
		
		
	}

	private NodeBlock parseBlock() throws SyntaxException {
	NodeStmt stmt = parseStmt();
	NodeBlock block;
	if (!scanner.done() && !curr().equals(new Token("end"))) {
		match(";");
		block = parseBlock();
		return new NodeBlock(stmt, block);
	} 
	else {
		return new NodeBlock(stmt, null);
	}

}

	public Node parse(String prog) throws SyntaxException {
		scanner = new Scanner(prog);
		scanner.next();
		return parseBlock();
	}


}

