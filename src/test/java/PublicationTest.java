import org.informatics.domain.PageSize;
import org.informatics.domain.PaperType;
import org.informatics.domain.Publication;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {

    @Test
    void testPublicationConstructorAndGetters() {
        Publication pub = new Publication("id1", "Test Title", PageSize.A4, PaperType.GLOSSY, 5.0, 50);
        assertEquals("id1", pub.geId());
        assertEquals("Test Title", pub.getTitle());
        assertEquals(PageSize.A4, pub.getPageSize());
        assertEquals(PaperType.GLOSSY, pub.getPaperType());
        assertEquals(5.0, pub.getPricePerCopy());
        assertEquals(50, pub.getPageCount());
    }

    @Test
    void testSetPricePerCopyValid() {
        Publication pub = new Publication("id2", "Test2", PageSize.A5, PaperType.NORMAL, 3.0, 20);
        pub.setPricePerCopy(10.0);
        assertEquals(10.0, pub.getPricePerCopy());
    }

    @Test
    void testSetPricePerCopyNegativeThrows() {
        Publication pub = new Publication("id3", "Test3", PageSize.A5, PaperType.NORMAL, 3.0, 20);
        assertThrows(IllegalArgumentException.class, () -> pub.setPricePerCopy(-1.0));
    }

    @Test
    void testConstructorNegativePriceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Publication("id4", "Test4", PageSize.A3, PaperType.RICE, -5.0, 10));
    }

    @Test
    void testConstructorNegativePageCountThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Publication("id5", "Test5", PageSize.A3, PaperType.GLOSSY, 5.0, -10));
    }

    @Test
    void testToString() {
        Publication pub = new Publication("id6", "Test6", PageSize.A2, PaperType.NEWSPAPER, 7.0, 5);
        String result = pub.toString();
        assertTrue(result.contains("Test6"));
        assertTrue(result.contains("A2"));
        assertTrue(result.contains("NEWSPAPER"));
    }
}
