import org.junit.Test;
import static org.junit.Assert.*;

public class cellButtonTest
{
    @Test
    public void testisBomba()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setBomba(true);
        assertTrue(gomb.isBomba());

    }

    @Test
    public void testisReveal()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setSeen(true);
        assertTrue(gomb.isReveal());
    }

    @Test
    public void testiZaszlo()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setZaszlo(true);
        assertTrue(gomb.isZaszlo());
    }

    @Test
    public void testreveal_ha_zaszlo()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setZaszlo(true);
        gomb.setSeen(false);
        gomb.reveal(0);
        assertFalse(gomb.isReveal());
    }

    @Test
    public void testreveal_ha_nem_zaszlo()
    {
        int sor = 9, oszlop = 9, bomb = 10;
        Palya palya = new Palya(sor, oszlop, bomb);
        int x = 0, y = 0;
        Cell_button gomb = new Cell_button(palya, x, y);
        gomb.setZaszlo(false);
        gomb.setSeen(false);
        gomb.reveal(0);
        assertTrue(gomb.isReveal());
    }
}
