import org.informatics.domain.*;
import org.informatics.service.PublicationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicationIntegrationTest {

    @Test
    void testFullPublicationFlow() {
        PublicationService service = new PublicationService();

        Publication pub = new Publication("100", "Integration Book", PageSize.A1, PaperType.GLOSSY, 0, 50);
        service.addPublication(pub);

        assertNotNull(service.getPublicationById("100"));

        service.recalculatePricePerCopy("100");
        double expected = PaperType.GLOSSY.getBasePriceForA5() * PageSize.A1.getPriceMultiplier() * 50;
        assertEquals(expected, service.getPublicationById("100").getPricePerCopy(), 0.0001);

        Publication updated = new Publication("100", "Integration Book Updated", PageSize.A1, PaperType.GLOSSY, 1.0, 50);
        service.updatePublication("100", updated);
        assertEquals("Integration Book Updated", service.getPublicationById("100").getTitle());

        service.removePublication("100");
        assertThrows(java.util.NoSuchElementException.class, () -> service.getPublicationById("100"));
    }
}
