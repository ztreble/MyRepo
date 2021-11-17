
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExecutorTest {

	private List<Employee> employees = new LinkedList<>();

	private final DataBase db = DataBase.getInstance();

	@Before
	public void init() {
		this.employees.add(new Employee("David", "male", 3100, "Production"));
		this.employees.add(new Employee("Patrick", "male", 4300, "Research"));
		this.employees.add(new Employee("Lisa", "female", 2800, "HumanResource"));
		this.employees.add(new Employee("Michael", "male", 3700, "Accouting"));
		this.employees.add(new Employee("Emma", "female", 2600, "Purchasing"));
		this.employees.add(new Employee("Alice", "female", 2700, "Marketing"));

		this.db.save("employees", employees);
	}

	@After
	public void destruct() {
		File f = new File("employees.xml");
		if (f.exists()) {
			// HINT: You are allowed to comment this statement to see the file content
			f.delete();
		}

		this.employees.clear();
		this.db.clear();
	}

	@Test(timeout = 1000)
	public void testStore() {
		execute("STORE employees TO employees.xml;");

		File f = new File("employees.xml");
		assertTrue(f.exists());
	}

	/**
	 * 
	 * Note that in the given test cases, we only check whether the xml file exists
	 * instead of whether the file content is correct.
	 * 
	 * Passing the given test cases does not indicate the correctness of your
	 * solutions. Thus, you are encouraged to write your own test cases to check the
	 * file content.
	 * 
	 * We will check the file content in the marking test cases.
	 * 
	 */

	@Test(timeout = 1000)
	public void testStoreRetrieve() {
		execute("STORE employees TO employees.xml;RETRIEVE employees FROM employees.xml;");

		File f = new File("employees.xml");
		assertTrue(f.exists());

		List<Employee> loadedEmployees = this.db.load("employees");

		assertTrue(employeesCompare(this.employees, loadedEmployees));
	}

	@Test(timeout = 1000)
	public void testComplex() {
		execute("STORE   employees   TO employees.xml   ;     RETRIEVE employees  FROM  employees.xml;  ");

		File f = new File("employees.xml");
		assertTrue(f.exists());

		List<Employee> loadedEmployees = this.db.load("employees");

		assertTrue(employeesCompare(this.employees, loadedEmployees));
	}

	private void execute(String text) {
		Parser parser = new Parser(new Tokeniser(text));

		Executor executor = new Executor(parser.parseCmds());

		executor.execute();
	}

	private boolean employeesCompare(List<Employee> p1, List<Employee> p2) {
		if (p1 == null && p2 != null) {
			return false;
		} else if (p1 != null && p2 == null) {
			return false;
		}

		if (p1.size() != p2.size()) {
			return false;
		}

		for (int i = 0; i < p1.size(); i++) {
			if (!p1.get(i).equals(p2.get(i))) {
				return false;
			}
		}

		return true;
	}
}
