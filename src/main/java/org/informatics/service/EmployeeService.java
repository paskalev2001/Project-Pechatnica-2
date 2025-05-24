package org.informatics.service;
import org.informatics.domain.Employee;
import java.util.Map;

public class EmployeeService {

    /**
     * @param employees   Map of employees by ID as key
     * @param totalIncome Total income to use for salary calculation (passed to each employee)
     * @return The sum of all employee salaries
     */
    public double getTotalPayroll(Map<String, Employee> employees, double totalIncome) {
        if (employees == null) throw new IllegalArgumentException("employees map cannot be null");
        return employees.values().stream()
                .mapToDouble(e -> e.calculateSalary(totalIncome))
                .sum();
    }
}