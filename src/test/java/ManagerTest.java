import org.informatics.domain.Manager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    @Test
    void testConstructorAndGetters() {
        Manager m = new Manager("m1", "Alice", 3000.0, "HR", 10.0, 10000.0);
        assertEquals("m1", m.getId());
        assertEquals("Alice", m.getName());
        assertEquals(3000.0, m.getBaseSalary());
        assertEquals("HR", m.getDepartment());
        assertEquals(10.0, m.getBonusPercentage());
        assertEquals(10000.0, m.getIncomeThreshold());
    }

    @Test
    void testNegativeValuesThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Manager("m1", "Alice", -1000, "HR", 10, 10000));
        assertThrows(IllegalArgumentException.class, () -> new Manager("m1", "Alice", 1000, "HR", -10, 10000));
        assertThrows(IllegalArgumentException.class, () -> new Manager("m1", "Alice", 1000, "HR", 10, -10000));
        assertThrows(IllegalArgumentException.class, () -> new Manager(null, "Alice", 1000, "HR", 10, 10000));
        assertThrows(IllegalArgumentException.class, () -> new Manager("m1", "", 1000, "HR", 10, 10000));
    }

    @Test
    void testCalculateSalaryNoBonus() {
        Manager m = new Manager("m1", "Alice", 3000.0, "HR", 10.0, 10000.0);
        assertEquals(3000.0, m.calculateSalary(9000.0));
    }

    @Test
    void testCalculateSalaryWithBonus() {
        Manager m = new Manager("m1", "Alice", 3000.0, "HR", 10.0, 10000.0);
        assertEquals(3300.0, m.calculateSalary(10000.0)); // 10% bonus
        assertEquals(3300.0, m.calculateSalary(12000.0));
    }
}
