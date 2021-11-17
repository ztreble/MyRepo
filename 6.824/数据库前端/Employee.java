
public class Employee {

	public static final String KEY_ROOT = "employees";
	public static final String KEY_ELEMENT = "employee";
	public static final String KEY_NAME = "name";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_DEPARTMENT = "department";
	public static final String KEY_SALARY = "salary";

	private String name;

	private String gender;

	private String department;

	private int salary;

	public Employee() {
	}

	public Employee(String name, String gender, int salary, String department) {
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int getSalary() {
		return salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof Employee) {
			Employee p = (Employee) o;

			if (this.name != null) {
				if (!this.name.equals(p.name)) {
					return false;
				}
			} else if (p.name != null) {
				return false;
			}

			if (this.gender != null) {
				if (!this.gender.equals(p.gender)) {
					return false;
				}
			} else if (p.gender != null) {
				return false;
			}

			if (this.salary != p.salary) {
				return false;
			}

			if (this.department != null) {
				if (!this.department.equals(p.department)) {
					return false;
				}
			} else if (p.department != null) {
				return false;
			}
		}

		return true;
	}
}
