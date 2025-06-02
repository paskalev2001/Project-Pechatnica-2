import org.informatics.domain.*;
import org.informatics.service.PrintingMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class PrintingMachineServiceTest {

    PrintingMachineService service;
    PrintingMachine pm;
    Publication pub;
    Edition editionBW;
    Edition editionColor;

    @BeforeEach
    void setup() {
        service = new PrintingMachineService();
        pm = new PrintingMachine("pm1", true, 500, 30);
        pub = new Publication("p1", "Newspaper", PageSize.A4, PaperType.NEWSPAPER,  0.5, 10);
        editionBW = new Edition("123",pub,  10, false);
        editionColor = new Edition("123",pub,  10, true);
    }

    @Test
    void testLoadPaperSuccess() {
        pm.setCurrentPaperLoaded(100);
        int loaded = service.loadPaper(pm, 50);
        assertEquals(150, loaded);
    }

    @Test
    void testLoadPaperExceedsCapacityThrows() {
        pm.setCurrentPaperLoaded(480);
        assertThrows(IllegalArgumentException.class, () -> service.loadPaper(pm, 30));
    }

    @Test
    void testLoadPaperNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.loadPaper(pm, -10));
    }

    @Test
    void testPrintEditionSuccessBW() {
        pm.setCurrentPaperLoaded(200);
        service.printEdition(pm, editionBW);
        assertEquals(100, pm.getCurrentPaperLoaded());
        assertEquals(1, pm.getPrintedEditions().size());
        assertEquals(10, pm.getPrintedEditions().get(editionBW));
    }

    @Test
    void testPrintEditionNotEnoughPaperThrows() {
        pm.setCurrentPaperLoaded(50);
        assertThrows(IllegalStateException.class, () -> service.printEdition(pm, editionBW));
    }

    @Test
    void testPrintEditionColorNotSupportedThrows() {
        PrintingMachine bwOnly = new PrintingMachine("pm2", false, 300, 30);
        bwOnly.setCurrentPaperLoaded(200);
        assertThrows(UnsupportedOperationException.class, () -> service.printEdition(bwOnly, editionColor));
    }
}
