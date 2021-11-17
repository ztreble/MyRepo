import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

	private Map<String, List<Employee>> storage = new HashMap<>();

	private static final DataBase INSTANCE = new DataBase();

	public static DataBase getInstance() {
		return INSTANCE;
	}

	public void save(String key, List<Employee> employees) {
		if (employees != null) {
			this.storage.put(key, employees);
		}
	}

	public List<Employee> load(String key) {
		return this.storage.get(key);
	}

	public void clear() {
		this.storage.clear();
	}
}
