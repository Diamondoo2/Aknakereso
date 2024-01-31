import org.junit.Test;

import static org.junit.Assert.*;

public class PalyaTest
{
    @Test
    public void zaszlo_addedtest()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        palya.zaszlo_added(gomb);
        assertTrue(gomb.isZaszlo());
    }

    @Test
    public void zaszlo_removedtest()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setZaszlo(true);
        palya.zaszlo_removed(gomb);
        assertFalse(gomb.isZaszlo());
    }
}
