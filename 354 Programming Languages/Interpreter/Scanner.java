
import java.util.HashSet;
import java.util.Set;

/**
 * This is the scanner class for the interpreter. Since the last interpreter assignment,
 * the difference is the newly added delimiters for the scanner.
 * @author lwestmor
 *
 */
public class Scanner {

	private String program; // source program being interpreted
	private int pos; // index of next char in program
	private Token token; // last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace = new HashSet<String>();
	private Set<String> digits = new HashSet<String>();
	private Set<String> letters = new HashSet<String>();
	private Set<String> legits = new HashSet<String>();
	private Set<String> keywords = new HashSet<String>();
	private Set<String> operators = new HashSet<String>();
	private Set<String> comments = new HashSet<String>();
	private Set<String> commentary = new HashSet<String>();

	// initializers for previous sets

	private void fill(Set<String> s, char lo, char hi) {
		for (char c = lo; c <= hi; c++)
			s.add(c + "");
	}

	//whitespace
	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

    //digits
	private void initDigits(Set<String> s) {
		fill(s, '0', '9');
		s.add(".");
	}

    //letters
	private void initLetters(Set<String> s) {
		fill(s, 'A', 'Z');
		fill(s, 'a', 'z');
	}

    //legits
	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

    //operators
	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		s.add("<");
		s.add("<=");
		s.add(">");
		s.add(">=");
		s.add("<>");
		s.add("==");
		
	}

    //keywords
	private void initKeywords(Set<String> s) {
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

    //comments
	private void initComment(Set<String> s) {
		s.add("#");
		s.add("#");
	}

    //commentary
	private void initCommentary(Set<String> s) {
		s.addAll(legits);
		s.addAll(operators);
		s.addAll(whitespace);
	}

    //Scanner implementation
	public Scanner(String program) {
		this.program = program;
		pos = 0;
		token = null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);

		initComment(comments);
		initCommentary(commentary);
	}


	public boolean done() {
		return pos >= program.length();
	}

	private void many(Set<String> s) {
		while (!done() && s.contains(program.charAt(pos) + ""))
			pos++;
	}

	private void past(char c) {
		while (!done() && c != program.charAt(pos))
			pos++;
		if (!done() && c == program.charAt(pos))
			pos++;
	}

	// scan various kinds of lexeme

	private void nextNumber() {
		int old = pos;
		many(digits);
		token = new Token("num", program.substring(old, pos));
	}

	private void nextKwId() {
		int old = pos;
		many(letters);
		many(legits);
		String lexeme = program.substring(old, pos);
		token = new Token((keywords.contains(lexeme) ? lexeme : "id"), lexeme);
	}

	private void nextOp() {
		int old = pos;
		pos = old + 2;
		if (!done()) {
			String lexeme = program.substring(old, pos);
			if (operators.contains(lexeme)) {
				token = new Token(lexeme); // two-char operator
				return;
			}
		}
		pos = old + 1;
		String lexeme = program.substring(old, pos);
		token = new Token(lexeme); // one-char operator
	}

	private void nextComment() {
		many(comments);
		many(commentary);
		many(comments);
		next();
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	public boolean next() {
		if (done())
			return false;
		many(whitespace);
		String c = program.charAt(pos) + "";
		if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (comments.contains(c))
			nextComment();
		else if (operators.contains(c))
			nextOp();
		else if (c.equals("\r")) // i added this conditional because the scanner was never detecting the "\r" escape character. This addition stops "Illegal character" output.
		{
			pos++;
			next();
		}
		
		else {
			System.err.println("illegal character at position " + pos);
			pos++;
			return next();
		}
		return true;
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos, t, curr());
		next();
	}

	public Token curr() throws SyntaxException {
		if (token == null)
			throw new SyntaxException(pos, new Token("ANY"), new Token("EMPTY"));
		return token;
	}

	public int pos() {
		return pos;
	}

	// for unit testing
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}
