import org.informatics.domain.Edition;
import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;
import org.informatics.domain.Publication;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EditionUnitTest {

    @Test
    public void testEditionConstructorWithValidData() {
        Publication publication = new Publication(
                "pub1",
                "Science Weekly",
                1000,
                PageSize.A4,
                PaperType.GLOSSY,
                true,
                2.0,
                50
        );
        Edition edition = new Edition("ed1", publication, 5000, true);

        assertEquals("ed1", edition.getId());
        assertEquals(publication, edition.getPublication());
        assertEquals(5000, edition.getCopiesPrinted());
        assertTrue(edition.isColorPrint());
    }

    @Test
    public void testToStringReturnsCorrectFormat() {
        Publication publication = new Publication(
                "pub2",
                "Unit title",
                1000,
                PageSize.A4,
                PaperType.GLOSSY,
                true,
                2.0,
                50
        );
        Edition edition = new Edition("ed2", publication, 1000, false);


        String expected = "Edition of 'Unit title' - printed 1000 copies, Black & White print";
        assertEquals(expected, edition.toString());
    }

    @Test
    public void testConstructorThrowsExceptionForInvalidCopies() {
        Publication publication = new Publication(
                "pub3",
                "Unit title2",
                1000,
                PageSize.A4,
                PaperType.GLOSSY,
                true,
                2.0,
                50
        );
        Edition edition = new Edition("ed3", publication, 1000, false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Edition("ed3", publication, 0, true)
        );

        assertEquals("Number of printed copies must be positive.", exception.getMessage());
    }
}
