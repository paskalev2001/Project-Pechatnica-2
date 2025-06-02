import org.informatics.domain.Publication;
import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;
import org.informatics.service.PublicationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicationServiceTest {

    @Test
    void testCalculateTotalIncome() {
        Publication pub = new Publication(
                "abc", "Title",  PageSize.A4, PaperType.GLOSSY,  3.5, 100);
        PublicationService service = new PublicationService();
        double expected = 20 * 3.5;
        assertEquals(expected, service.calculateTotalIncome(pub));
    }

    @Test
    void testCalculateTotalIncomeZeroCopies() {
        Publication pub = new Publication(
                "abc", "Title",  PageSize.A4, PaperType.GLOSSY,  3.5, 100);
        PublicationService service = new PublicationService();
        assertEquals(0, service.calculateTotalIncome(pub));
    }

    @Test
    void testCalculateTotalIncomeZeroPrice() {
        Publication pub = new Publication(
                "abc", "Title",  PageSize.A4, PaperType.GLOSSY, 0.0, 100);
        PublicationService service = new PublicationService();
        assertEquals(0, service.calculateTotalIncome(pub));
    }
}
