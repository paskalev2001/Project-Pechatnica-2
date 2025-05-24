import org.informatics.domain.*;
import org.informatics.service.PrintingHouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PrintingHouseServiceTest {
    PrintingHouse house;
    PrintingHouseService service;
    PrintingMachine pm;
    Publication pub;
    Edition edition;

    @BeforeEach
    void setup() {
        house = new PrintingHouse("Alpha", 10000, 20, 100, 10.0);
        Map<PaperType, Double> prices = Map.of(PaperType.GLOSSY, 0.1, PaperType.NEWSPAPER, 0.05);
        service = new PrintingHouseService(house, prices, 20);
        pm = new PrintingMachine("pm1", true, 200, 30);
        pub = new Publication("p1", "Magazine", 500, PageSize.A4, PaperType.GLOSSY, true, 2.0, 16);
        edition = new Edition("e1", pub, 10, true);

        house.addMachine(pm);
        house.addEmployee(new Operator("o1", "Operator", 1500, "Print"));
        house.addEmployee(new Manager("m1", "Manager", 2500, "Management", 10, 10000));
    }

    @Test
    void testGetPaperPriceIncreasesWithSize() {
        double basePrice = service.getPaperPrice(PaperType.GLOSSY, PageSize.A4);
        double larger = service.getPaperPrice(PaperType.GLOSSY, PageSize.A2);
        assertTrue(larger > basePrice);
    }

    @Test
    void testLoadPaperToMachineSetsPaper() {
        service.loadPaperToMachine("pm1", 150);
        assertEquals(150, pm.getCurrentPaperLoaded());
    }

    @Test
    void testPrintEditionAddsEditionAndDecreasesPaper() {
        service.loadPaperToMachine("pm1", 200);
        service.printEdition("pm1", pub, true, 10);
        assertEquals(40, pm.getCurrentPaperLoaded());
        // Machine's printedEditions map should contain the edition
        assertTrue(pm.getPrintedEditions().size() > 0);
    }

    @Test
    void testPrintEditionColorOnBWThrows() {
        PrintingMachine bwOnly = new PrintingMachine("pm2", false, 100, 30);
        house.addMachine(bwOnly);
        service.loadPaperToMachine("pm2", 100);
        assertThrows(IllegalArgumentException.class, () -> service.printEdition("pm2", pub, true, 1));
    }

    @Test
    void testCalculateSalaryExpensesWithBonus() {
        double noBonus = service.calculateSalaryExpenses();
        assertTrue(noBonus > 0);

        Publication expensive = new Publication("expensive", "ExpensivePub", 2000, PageSize.A4, PaperType.GLOSSY, true, 6.0, 20);
        house.addEdition(new Edition("e2", expensive, 2000, true)); // 2000 * 6 = 12,000 > 10,000 threshold

        double withBonus = service.calculateSalaryExpenses();
        //System.out.println("no bonus = " + noBonus + " withBonus = " + withBonus);

        assertTrue(withBonus > noBonus);
    }

    @Test
    void testCalculateTotalIncomeWithDiscount() {
        Publication cheap = new Publication("cheap", "CheapPub", 150, PageSize.A4, PaperType.NEWSPAPER, false, 1.0, 8);
        Edition discEdition = new Edition("e3", cheap, 150, false);
        house.addEdition(discEdition);

        double income = service.calculateTotalIncome();
        assertTrue(income < (150 * 1.0));
    }

    @Test
    void testCalculatePaperExpenses() {
        service.loadPaperToMachine("pm1", 200);
        service.printEdition("pm1", pub, true, 10);
        double paperExpense = service.calculatePaperExpenses();
        assertTrue(paperExpense > 0);
    }

    @Test
    void testGetTotalPrintedPages() {
        service.loadPaperToMachine("pm1", 200);
        service.printEdition("pm1", pub, true, 10);
        int pages = service.getTotalPrintedPages("pm1");
        assertEquals(160, pages);
    }
}
