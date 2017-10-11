// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

    private String token;
    private String lexeme;

	/*
 	* Initializes the token and lexeme
 	*
 	* @param String token
 	* @param String lexeme
 	*/
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }

	/*
 	* Initializes token and lexeme to the same thing
 	*
 	* @param String token
 	*/
    public Token(String token) {
	this(token,token);
    }

	/*
 	* Gets the token
 	*
 	* @return String token
 	*/
    public String tok() { return token; } 

	/*
 	* Gets the lexeme
 	*
 	* @return String lexeme
 	*/ 
    public String lex() { return lexeme; }

    public boolean equals(Token t) {
	return token.equals(t.token);
    }

	/*
 	* toString that returns the token and lexeme
 	*
 	* @return String
 	*/ 
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
