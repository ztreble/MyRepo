
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TokeniserTest {

	private static void generalTest(Tokeniser tokeniser, Token token) {
		assertEquals(token, tokeniser.current());
		tokeniser.next();
	}

	@Test(timeout=1000)
	public void testRetrieve() {
		String text = "RETRIEVE employees FROM employees.xml;";
		Tokeniser tokeniser = new Tokeniser(text);

		List<Token> tokens = new LinkedList<>();
		tokens.add(new Token(Token.Type.RETRIEVE, "RETRIEVE"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees"));
		tokens.add(new Token(Token.Type.FROM, "FROM"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees.xml"));
		tokens.add(new Token(Token.Type.TERMINATOR, ";"));

		for (Token t : tokens) {
			generalTest(tokeniser, t);
		}
	}

	@Test(timeout=1000)
	public void testStore() {
		String text = "store employees to employees.xml;";
		Tokeniser tokeniser = new Tokeniser(text);

		List<Token> tokens = new LinkedList<>();
		tokens.add(new Token(Token.Type.STORE, "store"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees"));
		tokens.add(new Token(Token.Type.TO, "to"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees.xml"));
		tokens.add(new Token(Token.Type.TERMINATOR, ";"));

		for (Token t : tokens) {
			generalTest(tokeniser, t);
		}
	}

	@Test(timeout=1000)
	public void testComplex() {
		String text = "STORE   employees   TO employees.xml   ; RETRIEVE employees  FROM  employees.xml;  ";
		Tokeniser tokeniser = new Tokeniser(text);

		List<Token> tokens = new LinkedList<>();
		tokens.add(new Token(Token.Type.STORE, "STORE"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees"));
		tokens.add(new Token(Token.Type.TO, "TO"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees.xml"));
		tokens.add(new Token(Token.Type.TERMINATOR, ";"));
		tokens.add(new Token(Token.Type.RETRIEVE, "RETRIEVE"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees"));
		tokens.add(new Token(Token.Type.FROM, "FROM"));
		tokens.add(new Token(Token.Type.PARAMETER, "employees.xml"));
		tokens.add(new Token(Token.Type.TERMINATOR, ";"));

		for (Token t : tokens) {
			generalTest(tokeniser, t);
		}
	}
}
