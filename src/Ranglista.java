import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ranglista extends JPanel
{
    /**
     * A konstruktor a Ranglista panelt hozza létre.
     */
    public Ranglista()
    {
        JButton konnyu_gomb = new JButton("Könnyű");
        konnyu_gomb.setBackground(new Color(48, 253, 0));
        JButton kozepes_gomb = new JButton("Közepes");
        kozepes_gomb.setBackground(new Color(252, 232, 7));
        JButton nehez_gomb = new JButton("Nehéz");
        nehez_gomb.setBackground(new Color(253, 30, 0));
        JButton vissza_gomb = new JButton("Vissza");
        JPanel ranglista_panel = new JPanel();
        ranglista_panel.add(konnyu_gomb);
        ranglista_panel.add(kozepes_gomb);
        ranglista_panel.add(nehez_gomb);
        ranglista_panel.add(vissza_gomb);

        vissza_gomb.addActionListener(new Fomenu.VisszaListener());
        konnyu_gomb.addActionListener(new Fomenu.KonnyuListener());
        kozepes_gomb.addActionListener(new Fomenu.KozepesListener());
        nehez_gomb.addActionListener(new Fomenu.NehezListener());
        add(ranglista_panel);
    }


    /**
     * A Leaderboard osztály a JPanelből öröklődik. Beolvasást valósít meg.
     */
    public static class LeaderBoard extends JPanel
    {

        /**
         * A grafikus felületre kirajzolt elemeket hozza létre.
         * @param nehezseg nehézésgi szintnek megfelelő integer szám
         */
        public LeaderBoard(int nehezseg)
        {
            ArrayList<Eredmeny> lista = switch (nehezseg) {
                case 0 -> load_leaderboard("ranglista_konnyu.lst"); //konnyu
                case 1 -> load_leaderboard("ranglista_kozepes.lst"); //kozepes
                default -> load_leaderboard("ranglista_nehez.lst"); //nehez
            };

            JButton vissza_gomb = new JButton("Vissza");
            vissza_gomb.addActionListener(new Fomenu.VisszaListener());
            JPanel ranglista_panel = new JPanel();

            String[] columnNames = { "Helyezés", "Név", "Idő" };

            String[][] columnValues = new String[5][3];
            int k = Math.min(lista.size(), 5);
            for (int i = 0; i < k; i++) //top 5
            {
                columnValues[i][0] = Integer.toString(i + 1);
                columnValues[i][1] = lista.get(i).getNev();
                columnValues[i][2] = Integer.toString(lista.get(i).getIdo());
            }
            JTable j = new JTable(columnValues, columnNames);
            j.setBounds(30, 40, 200, 300);
            j.setEnabled(false);
            ranglista_panel.add(new JScrollPane(j));
            ranglista_panel.add(vissza_gomb);
            add(ranglista_panel);
        }
    }

    /**
     * A Ranglistát olvassa be a paraméterül kapott fájlból.
     * A fájl tartalmát ";" mentén tördeli.
     * @param nev a beolvasandó fájl neve
     * @return a feltöltött és rendezett táblázat
     */
    public static ArrayList<Eredmeny> load_leaderboard(String nev)
    {
        File fajl = new File(nev);
        Scanner myReader;
        try
        {
            myReader = new Scanner(fajl);
        }
        catch (FileNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        String[] adatok;
        ArrayList<Eredmeny> tablazat = new ArrayList<>();
        while (myReader.hasNextLine())
        {
            String data = myReader.nextLine();
            adatok = data.split(";");
            Eredmeny b = new Eredmeny(adatok[0], Integer.parseInt(adatok[1]));
            tablazat.add(b);
        }
        Collections.sort(tablazat, new TimeComparator());
        return tablazat;
    }
}
