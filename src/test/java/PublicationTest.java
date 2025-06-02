import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;
import org.informatics.domain.Publication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {

    @Test
    void testConstructorAndGetters() {
        Publication pub = new Publication(
                "123",
                "Test Title",
                PageSize.A4,
                PaperType.GLOSSY,
                1.99,
                50
        );

        assertEquals("123", pub.geId());
        assertEquals("Test Title", pub.getTitle());
        assertEquals(PageSize.A4, pub.getPageSize());
        assertEquals(PaperType.GLOSSY, pub.getPaperType());
        assertEquals(1.99, pub.getPricePerCopy());
        assertEquals(50, pub.getPageCount());
    }

    @Test
    void testSetPricePerCopy() {
        Publication pub = new Publication("1", "Title", PageSize.A5, PaperType.NORMAL, 2.5, 10);
        pub.setPricePerCopy(3.75);
        assertEquals(3.75, pub.getPricePerCopy());
    }

    @Test
    void testToString() {
        Publication pub = new Publication("1", "Title", PageSize.A5, PaperType.NORMAL, 2.5, 10);
        String result = pub.toString();
        assertTrue(result.contains("Title"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("pageSize=A5"));
        assertTrue(result.contains("paperType=NORMAL"));
        assertTrue(result.contains("pricePerCopy=2.5"));
    }

    @Test
    void testNegativePricePerCopyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Publication(
                "id", "title", PageSize.A4, PaperType.GLOSSY, -1.0, 10
        ));
    }

    @Test
    void testNegativePageCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Publication(
                "id", "title", PageSize.A4, PaperType.GLOSSY, 1.0, -10
        ));
    }

    @Test
    void testSetNegativePricePerCopyThrowsException() {
        Publication pub = new Publication("id", "title", PageSize.A4, PaperType.GLOSSY, 1.0, 10);
        assertThrows(IllegalArgumentException.class, () -> pub.setPricePerCopy(-5.0));
    }
}
