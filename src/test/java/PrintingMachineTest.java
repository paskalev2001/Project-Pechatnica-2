import org.informatics.domain.PrintingMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrintingMachineTest {
    @Test
    void testConstructorAndGetters() {
        PrintingMachine pm = new PrintingMachine("pm1", true, 1000, 40);
        assertEquals("pm1", pm.getId());
        assertTrue(pm.isColorCapable());
        assertEquals(1000, pm.getMaxPaperCapacity());
        assertEquals(40, pm.getPagesPerMinute());
        assertEquals(0, pm.getCurrentPaperLoaded());
        assertTrue(pm.getPrintedEditions().isEmpty());
    }

    @Test
    void testSetAndGetCurrentPaperLoaded() {
        PrintingMachine pm = new PrintingMachine("pm1", false, 500, 20);
        pm.setCurrentPaperLoaded(250);
        assertEquals(250, pm.getCurrentPaperLoaded());
        pm.setCurrentPaperLoaded(0);
        assertEquals(0, pm.getCurrentPaperLoaded());
    }
}
