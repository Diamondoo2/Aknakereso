import org.junit.Test;
import static org.junit.Assert.*;

public class TimeComparatorTest {
    @Test
    public void testNagyobbEredmenyHelyes()
    {
        Eredmeny john = new Eredmeny("Anna", 12);
        Eredmeny dave = new Eredmeny("Bob", 14);

        TimeComparator comp = new TimeComparator();

        assertTrue(comp.compare(john, dave) < 0);
    }
}
