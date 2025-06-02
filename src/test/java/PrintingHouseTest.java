import org.informatics.domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrintingHouseTest {
    @Test
    void testConstructionAndAdd() {
        PrintingHouse ph = new PrintingHouse("Alpha", 10000, 15.0, 1000, 10.0);
        PrintingMachine pm = new PrintingMachine("pm1", true, 1000, 40);
        Employee emp = new Operator("o1", "Jane", 1500, "Print");

        assertTrue(ph.addMachine(pm));
        assertFalse(ph.addMachine(pm)); // ensures no duplicates

        assertTrue(ph.addEmployee(emp));
        assertFalse(ph.addEmployee(emp)); // ensure no duplicates

        Edition ed = new Edition("ed1", new Publication("p1", "Pub",  PageSize.A4, PaperType.GLOSSY,  2.0, 20), 100, false);
        assertTrue(ph.addEdition(ed));
        assertFalse(ph.addEdition(ed));
    }
}