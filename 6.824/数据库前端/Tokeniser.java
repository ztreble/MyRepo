public class Tokeniser {

	private String buffer; // save text
	private Token currentToken; // save token extracted from next()

	public Tokeniser(String text) {
		buffer = text; // save input text (string)
		next(); // extracts the first token.
	}

	/**
	 * This function will find and extract a next token from {@code buffer} and save
	 * the token to {@code currentToken}.
	 */
	public void next() {

		buffer = buffer.trim(); // remove whitespace
		if (buffer.isEmpty()) {
			currentToken = null; // if there's no string left, set currentToken null and return
			return;
		}

		// TODO
		// ########## YOUR CODE STARTS HERE ##########
		if (buffer.startsWith(Token.Type.RETRIEVE.toString())||buffer.startsWith(Token.Type.RETRIEVE.toString().toLowerCase())){
			currentToken = new Token(Token.Type.RETRIEVE,buffer.substring(0,8));
		}
		else if (buffer.startsWith(Token.Type.FROM.toString())||buffer.startsWith(Token.Type.FROM.toString().toLowerCase())){
			currentToken = new Token(Token.Type.FROM,buffer.substring(0,4));
		}
		else if (buffer.startsWith(Token.Type.TO.toString())||buffer.startsWith(Token.Type.TO.toString().toLowerCase())){
			currentToken = new Token(Token.Type.TO,buffer.substring(0,2));
		}
		else if (buffer.startsWith(Token.Type.STORE.toString())||buffer.startsWith(Token.Type.STORE.toString().toLowerCase())){
			currentToken = new Token(Token.Type.STORE,buffer.substring(0,5));
		}
		else if (currentToken.getType()==Token.Type.RETRIEVE||currentToken.getType()==Token.Type.FROM||
				currentToken.getType()==Token.Type.TO||currentToken.getType()==Token.Type.STORE){
			if(buffer.contains(" ")){
				var s = buffer.substring(0,buffer.indexOf(" "));
				currentToken = new Token(Token.Type.PARAMETER,s);
			}else{
				var s = buffer.substring(0,buffer.indexOf(";"));
				currentToken = new Token(Token.Type.PARAMETER,s);
			}
		}
		else if (buffer.startsWith(";")){
			currentToken = new Token(Token.Type.TERMINATOR,";");
		}
		// ########## YOUR CODE ENDS HERE ##########

		// Remove the extracted token from buffer
		int tokenLen = currentToken.getValue().length();
		buffer = buffer.substring(tokenLen);
	}

	/**
	 * returned the current token extracted by {@code next()} **** please do not
	 * modify this part ****
	 * 
	 * @return type: Token
	 */
	public Token current() {
		return currentToken;
	}

	/**
	 * check whether there still exists another tokens in the buffer or not ****
	 * please do not modify this part ****
	 * 
	 * @return type: boolean
	 */
	public boolean hasNext() {
		return currentToken != null;
	}
}