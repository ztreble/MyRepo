import java.util.LinkedList;
import java.util.List;

public class Parser {

	private final Tokeniser tokeniser;

	public Parser(Tokeniser tokeniser) {
		this.tokeniser = tokeniser;
	}

	public List<Command> parseCmds() {

		List<Command> commands = new LinkedList<>();

		// TODO
		// ########## YOUR CODE STARTS HERE ##########
		while (tokeniser.hasNext()){
			switch (tokeniser.current().getType()){
				case RETRIEVE->{
					RetrieveCommand command = new RetrieveCommand();
					tokeniser.next();
					command.setKey(tokeniser.current().getValue());
					tokeniser.next();
					tokeniser.next();
					command.setFileName(tokeniser.current().getValue());
					tokeniser.next();
					commands.add(command);
					tokeniser.next();
				}
				case STORE -> {
					StoreCommand command = new StoreCommand();
					tokeniser.next();
					command.setKey(tokeniser.current().getValue());
					tokeniser.next();
					tokeniser.next();
					command.setFileName(tokeniser.current().getValue());
					tokeniser.next();
					commands.add(command);
					tokeniser.next();
				}
				default -> {}
			}
		}
		// ########## YOUR CODE ENDS HERE ##########

		return commands;
	}
}
