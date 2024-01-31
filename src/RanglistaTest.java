import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RanglistaTest
{
    @Test
    public void testload_leaderboard_fajlhossz()
    {
        ArrayList<Eredmeny> lista = Ranglista.load_leaderboard("testfajl.txt");
        assertEquals(lista.size(), 4);
    }

    @Test
    public void testload_leaderboard_rendezes_elso()
    {
        ArrayList<Eredmeny> lista = Ranglista.load_leaderboard("testfajl.txt");
        Eredmeny elso = lista.get(0);
        assertEquals(elso.getNev(), "Nora");
    }

    @Test
    public void testload_leaderboard_rendezes_utolso()
    {
        ArrayList<Eredmeny> lista = Ranglista.load_leaderboard("testfajl.txt");
        Eredmeny utolso = lista.get(lista.size() - 1);
        assertEquals(utolso.getNev(), "David");
    }

}
