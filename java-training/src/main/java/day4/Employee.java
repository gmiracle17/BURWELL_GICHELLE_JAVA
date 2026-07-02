package day4;

public class Employee {
	String name;
	String department;
	double salary;

	// Constructor for Employee class
	public Employee(String name, String department, double salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
	    return String.format("%s from %s (Salary: %.2f)", name, department, salary);
	}
}
