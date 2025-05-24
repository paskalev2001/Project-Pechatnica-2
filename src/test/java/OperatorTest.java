import org.informatics.domain.Operator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {
    @Test
    void testConstructorAndGetters() {
        Operator o = new Operator("o1", "Bob", 2000.0, "Printing");
        assertEquals("o1", o.getId());
        assertEquals("Bob", o.getName());
        assertEquals(2000.0, o.getBaseSalary());
        assertEquals("Printing", o.getDepartment());
    }

    @Test
    void testNegativeValuesThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Operator("o1", "Bob", -500, "Printing"));
        assertThrows(IllegalArgumentException.class, () -> new Operator(null, "Bob", 2000, "Printing"));
        assertThrows(IllegalArgumentException.class, () -> new Operator("o1", "", 2000, "Printing"));
    }

    @Test
    void testCalculateSalary() {
        Operator o = new Operator("o1", "Bob", 2000.0, "Printing");
        assertEquals(2000.0, o.calculateSalary(0.0));
        assertEquals(2000.0, o.calculateSalary(10000.0));
    }
}
