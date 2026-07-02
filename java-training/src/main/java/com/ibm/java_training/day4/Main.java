package com.ibm.java_training.day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name

        // Remove duplicates using hash set
        Set<String> seenNames = new HashSet<>();
        List<Employee> cleanedEmployeeList = new ArrayList<>();
        
        for (Employee employee : employees) {
            if (!seenNames.contains(employee.getName())) {
                seenNames.add(employee.getName());
                cleanedEmployeeList.add(employee);
            }
        }
        
        System.out.println("Cleaned Employee List:\n");
        
        for (Employee employee : cleanedEmployeeList) {
            System.out.println(employee);
        }
        
        // Group employees by department using maps
        Map<String, List<Employee>> departmentMap = new HashMap<>();
        
        for (Employee employee : employees) {
            String department = employee.getDepartment();
            
            if (!departmentMap.containsKey(department)) {
                departmentMap.put(department, new ArrayList<>());
            }
            
            departmentMap.get(department).add(employee);
        }
        
        System.out.println("\nEmployees Grouped By Department:\n");
        
        for (String department : departmentMap.keySet()) {
            System.out.println(department);
            for (Employee employee : departmentMap.get(department)) {
                System.out.printf("- %s (%.2f)%n", employee.getName(), employee.getSalary());
            }
            System.out.println();
        }

        // Highest Paid Employee Per Department
        System.out.println("\nHighest Paid Employee Per Department:\n");
        for (String department : departmentMap.keySet()) {
            List<Employee> departmentEmployees = departmentMap.get(department);
            Employee highestPaidEmployee = departmentEmployees.get(0);
            
            for (Employee employee : departmentEmployees) {
                if (employee.getSalary() > highestPaidEmployee.getSalary()) {
                    highestPaidEmployee = employee;
                }
            }
            
            System.out.println(department);
            System.out.printf("- %s (%.2f)%n", highestPaidEmployee.getName(), highestPaidEmployee.getSalary());
            System.out.println();
        }

        // Sort employees by salary (descending)
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            }
        });
        
        System.out.println("\nTop Earning Employees:\n");
        
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Set of Unique Salaries
        Set<Double> uniqueSalaries = new TreeSet<>(Collections.reverseOrder());
        
        for (Employee employee : employees) {
            uniqueSalaries.add(employee.getSalary());
        }
        
        System.out.println("\nUnique Salaries:");
        
        for (Double salary : uniqueSalaries) {
            System.out.printf("%.2f%n", salary);
        }
	}

}
