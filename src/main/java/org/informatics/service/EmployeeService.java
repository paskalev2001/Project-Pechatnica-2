package org.informatics.service;
import org.informatics.domain.Employee;
import java.util.Map;

public class EmployeeService {

    public double getTotalPayroll(Map<String, Employee> employees, double totalIncome) {
        if (employees == null) throw new IllegalArgumentException("employees map cannot be null");
        return employees.values().stream()
                .mapToDouble(e -> e.calculateSalary(totalIncome))
                .sum();
    }

    public Employee findEmployeeById(Map<String, Employee> employees, String id) {
        if (employees == null) throw new IllegalArgumentException("employees map cannot be null");
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        return employees.get(id);
    }
}