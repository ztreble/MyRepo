public class Token {

	public enum Type {
		RETRIEVE, FROM, STORE, TO, TERMINATOR, PARAMETER;
	}

	private final Type type;
	private final String value;

	public Token(Type type, String value) {
		this.value = value;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof Token) {
			Token t = (Token) o;

			if (this.type == t.type && this.value.equals(t.value)) {
				return true;
			}
		}

		return false;
	}
}
