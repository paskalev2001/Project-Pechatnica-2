import org.informatics.domain.Employee;
import org.informatics.domain.Manager;
import org.informatics.domain.Operator;
import org.informatics.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Test
    void testGetTotalPayroll() {
        EmployeeService service = new EmployeeService();
        Map<String, Employee> employees = new HashMap<>();
        employees.put("m1", new Manager("m1", "Alice", 3000, "HR", 10.0, 10000));
        employees.put("o1", new Operator("o1", "Bob", 2000, "Printing"));

        double payrollLowIncome = service.getTotalPayroll(employees, 5000);
        assertEquals(3000 + 2000, payrollLowIncome);

        double payrollHighIncome = service.getTotalPayroll(employees, 12000);
        assertEquals(3300 + 2000, payrollHighIncome);
    }

    @Test
    void testFindEmployeeById() {
        EmployeeService service = new EmployeeService();
        Map<String, Employee> employees = new HashMap<>();
        Employee op = new Operator("o1", "Bob", 2000, "Printing");
        employees.put("o1", op);

        assertEquals(op, service.findEmployeeById(employees, "o1"));
        assertNull(service.findEmployeeById(employees, "nonexistent"));
    }

    @Test
    void testNullMapThrowsException() {
        EmployeeService service = new EmployeeService();
        assertThrows(IllegalArgumentException.class, () -> service.getTotalPayroll(null, 1000));
        assertThrows(IllegalArgumentException.class, () -> service.findEmployeeById(null, "x"));
    }

    @Test
    void testNullIdThrowsException() {
        EmployeeService service = new EmployeeService();
        Map<String, Employee> employees = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> service.findEmployeeById(employees, null));
    }
}