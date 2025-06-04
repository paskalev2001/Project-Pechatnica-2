
import org.informatics.domain.*;
import org.informatics.service.PublicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicationServiceTest {

    private PublicationService service;

    @BeforeEach
    void setUp() {
        service = new PublicationService();
    }

    @Test
    void testAddAndGetPublication() {
        Publication pub = new Publication("1", "Book", PageSize.A4, PaperType.NORMAL, 0, 100);
        service.addPublication(pub);
        Publication retrieved = service.getPublicationById("1");
        assertEquals("Book", retrieved.getTitle());
    }

    @Test
    void testAddPublicationDuplicateThrows() {
        Publication pub = new Publication("2", "Book2", PageSize.A4, PaperType.NORMAL, 0, 100);
        service.addPublication(pub);
        assertThrows(IllegalArgumentException.class, () -> service.addPublication(pub));
    }

    @Test
    void testGetPublicationNotFoundThrows() {
        assertThrows(java.util.NoSuchElementException.class, () -> service.getPublicationById("999"));
    }

    @Test
    void testRemovePublication() {
        Publication pub = new Publication("3", "RemoveMe", PageSize.A3, PaperType.GLOSSY, 0, 50);
        service.addPublication(pub);
        service.removePublication("3");
        assertThrows(java.util.NoSuchElementException.class, () -> service.getPublicationById("3"));
    }

    @Test
    void testUpdatePublication() {
        Publication pub = new Publication("4", "OldTitle", PageSize.A2, PaperType.RICE, 0, 10);
        service.addPublication(pub);
        Publication updated = new Publication("4", "NewTitle", PageSize.A2, PaperType.RICE, 0, 10);
        service.updatePublication("4", updated);
        assertEquals("NewTitle", service.getPublicationById("4").getTitle());
    }

    @Test
    void testCalculatePricePerCopy() {
        double price = service.calculatePricePerCopy(PaperType.NORMAL, PageSize.A3, 10);
        assertEquals(0.10 * 1.5 * 10, price, 0.0001);
    }

    @Test
    void testCalculatePricePerCopyInvalidPageCount() {
        assertThrows(IllegalArgumentException.class, () ->
                service.calculatePricePerCopy(PaperType.NORMAL, PageSize.A3, 0));
    }

    @Test
    void testRecalculatePricePerCopy() {
        Publication pub = new Publication("5", "Book5", PageSize.A2, PaperType.GLOSSY, 0, 20);
        service.addPublication(pub);
        service.recalculatePricePerCopy("5");
        double expected = PaperType.GLOSSY.getBasePriceForA5() * PageSize.A2.getPriceMultiplier() * 20;
        assertEquals(expected, service.getPublicationById("5").getPricePerCopy(), 0.0001);
    }
}
