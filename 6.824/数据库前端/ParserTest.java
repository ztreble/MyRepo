
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ParserTest {

	@Test(timeout=1000)
	public void testRetrieve() {
		String text = "RETRIEVE employees FROM employees.xml;";
		Parser parser = new Parser(new Tokeniser(text));

		List<Command> commands = parser.parseCmds();

		assertEquals(1, commands.size());
		assertNotNull(commands.get(0));
		assertTrue(commands.get(0) instanceof RetrieveCommand);

		RetrieveCommand lc = (RetrieveCommand) commands.get(0);

		assertEquals("employees", lc.getKey());
		assertEquals("employees.xml", lc.getFileName());
	}

	@Test(timeout=1000)
	public void testStore() {
		String text = "STORE employees TO employees.xml;";
		Parser parser = new Parser(new Tokeniser(text));

		List<Command> commands = parser.parseCmds();

		assertEquals(1, commands.size());
		assertNotNull(commands.get(0));
		assertTrue(commands.get(0) instanceof StoreCommand);

		StoreCommand sac = (StoreCommand) commands.get(0);

		assertEquals("employees", sac.getKey());
		assertEquals("employees.xml", sac.getFileName());
	}

	@Test(timeout=1000)
	public void testComplex() {
		String text = "STORE   employees   TO employees.xml   ;      RETRIEVE employees  FROM  employees.xml;  ";
		Parser parser = new Parser(new Tokeniser(text));

		List<Command> commands = parser.parseCmds();

		assertEquals(2, commands.size());

		assertNotNull(commands.get(0));
		assertTrue(commands.get(0) instanceof StoreCommand);

		StoreCommand sac = (StoreCommand) commands.get(0);

		assertEquals("employees", sac.getKey());
		assertEquals("employees.xml", sac.getFileName());

		assertNotNull(commands.get(1));
		assertTrue(commands.get(1) instanceof RetrieveCommand);

		RetrieveCommand lc = (RetrieveCommand) commands.get(1);

		assertEquals("employees", lc.getKey());
		assertEquals("employees.xml", lc.getFileName());
	}
}
