import org.informatics.domain.Edition;
import org.informatics.domain.Publication;
import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditionIntegrationTest {

    @Test
    public void testEditionWithRealPublication() {
        Publication publication = new Publication(
                "pub001",
                "National Geographic",
                PageSize.A4,
                PaperType.GLOSSY,
                2.5,
                120
        );

        Edition edition = new Edition("ed001", publication, 5000, true);

        assertEquals("ed001", edition.getId());
        assertEquals(publication, edition.getPublication());
        assertEquals(5000, edition.getCopiesPrinted());
        assertTrue(edition.isColorPrint());

        String expected = "Edition of 'National Geographic' - printed 5000 copies, Color print";
        assertEquals(expected, edition.toString());
    }
}