import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EredmenyTest
{
    @Test
    public void testgetnev()
    {
        Eredmeny anna = new Eredmeny("Anna", 15);
        assertEquals("Anna", anna.getNev());
    }

    @Test
    public void testgetIdo()
    {
        Eredmeny anna = new Eredmeny("Anna", 15);
        assertEquals(15, anna.getIdo());
    }
}
