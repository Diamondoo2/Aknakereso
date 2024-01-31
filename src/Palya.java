import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class Palya extends JPanel
{
    /**
     * Oszlopok száma.
     */
    private final int oszlop;

    /**
     * Sorok száma.
     */
    private final int sor;

    /**
     * A bombák száma.
     */
    private final int bombak_szama;

    /**
     * Ido típusú időt számláló és megjelenítő objektum.
     */
    private final Ido ido;

    /**
     * A helyes zászlók száma.
     */
    private int helyes_zaszlok_szama;

    /**
     * A rossz helyre lerakott zászlók száma.
     */
    private int rossz_zaszlok_szama;


    /**
     * Nevet bekérő szövegmező végtelenszer történő
     * megjelenítését megakadályozó változó.
     */
    boolean mar_kesz = false;

    /**
     * Vesztettél felirat végtelenszer történő
     * megjelenítését megakadályozó változó.
     */
    boolean mar_vege = false;


    /**
     * A pálya.
     */
    private final Cell_button[][] cellak;


    /**
     * Első kattintást figyelő váltoró.
     */
    boolean elso_kattintas = true;


    /**
     * A lerakott zászlók számát megjelenítő JLabel.
     */
    private final JLabel szamlalo = new JLabel();

    /**
     * Első kattintásra nem lehet bombára kattintani.
     * @return első kattintás vagy sem
     */
    public boolean isElso_kattintas()
    {
        return elso_kattintas;
    }

    /**
     * Első kattintás értékét módosítja.
     * @param elso_kattintas boolean érték
     */
    public void setElso_kattintas(boolean elso_kattintas)
    {
        this.elso_kattintas = elso_kattintas;
    }

    /**
     * Ha első kattintás bombára történne, akkor átrakja azt egy random üres helyre.
     */
    public void change_bomba()
    {
        Random rand = new Random();
        int hely = rand.nextInt((sor * oszlop - bombak_szama));
        int szamol = 0;
            for (int i = 0; i < sor; i++)
            {
                for(int j = 0; j < oszlop; j++)
                {
                    if(!cellak[i][j].isBomba())
                    {
                        szamol++;
                    }
                    if(szamol == hely)
                    {
                        cellak[i][j].setBomba(true);
                    }
                }
            }
    }

    /**
     * A konstruktor állítja be a nehézésgi szintnek megfelelő paramétereket.
     * Létrehozza a pályát gombokból és beállítja azok szomszédait.
     * @param osz oszlopok száma
     * @param so sorok száma
     * @param bomb bombák száma
     */
    public Palya(int osz, int so, int bomb) {
        helyes_zaszlok_szama = 0;
        oszlop = osz;
        sor = so;
        bombak_szama = bomb;
        cellak = new Cell_button[sor][oszlop];
        JPanel palya_panel = new JPanel();

        for (int i = 0; i < sor; i++) {
            for (int j = 0; j < oszlop; j++) {
                cellak[i][j] = new Cell_button(this, i, j);
                palya_panel.add(cellak[i][j]);
                cellak[i][j].setBackground(Color.black);
            }
        }

        for (int i = 0; i < sor; i++) {
            for (int j = 0; j < oszlop; j++) {
                if (i - 1 >= 0 && j - 1 >= 0)        cellak[i][j].szomszed_hozzaad(cellak[i - 1][j - 1]);
                if (i - 1 >= 0 && j + 1 < oszlop)    cellak[i][j].szomszed_hozzaad(cellak[i - 1][j + 1]);
                if (i - 1 >= 0)                      cellak[i][j].szomszed_hozzaad(cellak[i - 1][j]);
                if (i + 1 < sor && j - 1 >= 0)       cellak[i][j].szomszed_hozzaad(cellak[i + 1][j - 1]);
                if (i + 1 < sor && j + 1 < oszlop)   cellak[i][j].szomszed_hozzaad(cellak[i + 1][j + 1]);
                if (i + 1 < sor)                     cellak[i][j].szomszed_hozzaad(cellak[i + 1][j]);
                if (j - 1 >= 0)                      cellak[i][j].szomszed_hozzaad(cellak[i][j - 1]);
                if (j + 1 < oszlop)                  cellak[i][j].szomszed_hozzaad(cellak[i][j + 1]);
            }
        }

        int[][] bombak = bomba_hely();
        for (int[] coords : bombak) {
            int x = coords[0];
            int y = coords[1];
            cellak[x][y].setBomba(true);
        }

        palya_panel.setLayout(new GridLayout(sor, oszlop));

        JButton vissza = new JButton("Vissza");
        JPanel menu = new JPanel();

        JLabel label = new JLabel();
        ido = new Ido(label);
        ido.start();

        menu.add(label);
        menu.add(vissza);

        vissza.addActionListener(new Fomenu.VisszaListener());
        vissza.addActionListener(actionEvent -> ido.vege_szamol());

        add(palya_panel);

        add(szamlalo);
        add(menu);
    }


    /**
     * Egy mező megjelölésekor, ha az bomba, akkor a helyes zászlók számához ad egyet,
     * egyébként a rossz zászlókéhoz. Ha a helyes zászlók száma eléri a bombák számát,
     * akkor meghívja a win függvényt.
     * @param cell Az adott mező.
     */
    public void zaszlo_added(Cell_button cell)
    {
        cell.setZaszlo(true);
        if (cell.isBomba())
        {
            helyes_zaszlok_szama++;
            if (helyes_zaszlok_szama == bombak_szama)
            {
                win();
            }
        }
        else
        {
            rossz_zaszlok_szama++;
        }
        szamlalo.setText("Megjelölt mezők: " + (helyes_zaszlok_szama + rossz_zaszlok_szama) + "/" + bombak_szama);
    }


    /**
     * A helyes vagy rossz zászlók számából levon egyet attól függően,
     * hogy bomba volt alatta vagy sem.
     * @param cell  Az adott mező.
     */
    public void zaszlo_removed(Cell_button cell)
    {
        cell.setZaszlo(false);
        if (cell.isBomba())
        {
            helyes_zaszlok_szama--;
        }
        else
        {
            rossz_zaszlok_szama--;
        }
        szamlalo.setText("Megjelölt mezők: " + (helyes_zaszlok_szama + rossz_zaszlok_szama) + "/" + bombak_szama);
    }


    /**
     * A játék megnyerésekor meghívódó függvény.
     * "Gratulálok nyertél!" szöveget jelenít meg, felhasználónevet kér be és
     * gombokat jelenít meg a panelen.
     * A nevet és az időt fájlban rögzíti a nehézségi szintnek megfelelően.
     */
    private void win()
    {
        if(!mar_kesz)
        {
            ido.vege_szamol();
            mar_kesz = true;
            int mert_ido = Ido.getTime();
            JLabel nyert_szoveg = new JLabel("Gratulálok nyertél!");//   Időd: " + mert_ido);
            add(nyert_szoveg);
            JLabel nev_keres_label = new JLabel("   Kérlek add meg a felhasználóneved: ");
            add(nev_keres_label);
            JTextField felhasz_nev = new JTextField(30);
            add(felhasz_nev);
            JButton nev_rogzit_gomb = new JButton("Név rögzítése");
            add(nev_rogzit_gomb);
            nev_rogzit_gomb.addActionListener(actionEvent -> {
                File dicsosegtabla;
                if (bombak_szama == 10)
                {
                    dicsosegtabla = new File("ranglista_konnyu.lst");
                }
                else if (bombak_szama == 40)
                {
                    dicsosegtabla = new File("ranglista_kozepes.lst");
                }
                else
                {
                    dicsosegtabla = new File("ranglista_nehez.lst");
                }
                try
                {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(dicsosegtabla, true)));
                    out.println(felhasz_nev.getText() + ';' + mert_ido);
                    out.close();
                } catch (IOException ignored)
                {
                }
                nev_rogzit_gomb.setEnabled(false);
            });
            JButton uj_jatek_gomb = new JButton("Új játék");
            uj_jatek_gomb.setBackground(new Color(48, 253, 0));
            JButton kilepes_gomb = new JButton("Kilépés");
            kilepes_gomb.setBackground(new Color(255, 0, 0));
            add(uj_jatek_gomb);
            add(kilepes_gomb);
            uj_jatek_gomb.addActionListener(new Fomenu.UjjatekListener());
            kilepes_gomb.addActionListener(new Fomenu.KilepesListener());
        }

    }


    /**
     * A bombák helyét sorsolja ki a nehézségi szint paramétereinek megfelelően random módon.
     * Garantálja, hogy az összes bomba különböző helyre kerüljön.
     * @return két dimenziós int tömb, amely tartalmazza a bombák helyeit.
     */
    public int[][] bomba_hely() {
        int[][] helyek = new int[bombak_szama][2];
        Random rand = new Random();
        for (int i = 0; i < bombak_szama; i++) {
            helyek[i][0] = rand.nextInt(sor);
            helyek[i][1] = rand.nextInt(oszlop);
            if (i > 0) {
                boolean kulonbozik = true;
                while (kulonbozik) {
                    boolean volt_mar = false;
                    for (int j = 0; j < i; j++) {
                        if (helyek[i][0] == helyek[j][0] && helyek[i][1] == helyek[j][1]) {
                            volt_mar = true;
                            break;
                        }
                    }
                    if (volt_mar) {
                        helyek[i][0] = rand.nextInt(sor);
                        helyek[i][1] = rand.nextInt(oszlop);
                    } else {
                        kulonbozik = false;
                    }
                }
            }
        }
        return helyek;
    }


    /**
     * Bomba felfedése esetén ez a függvény leállítja az idő számolását,
     * "Vesztettél" feliratot jelenít meg és felfedi az összes mező értékét.
     */
    public void end_game()
    {
        if(!mar_vege)
        {
            mar_vege = true;
            ido.vege_szamol();
            JLabel vesztes = new JLabel("Vesztettél!");
            add(vesztes);
            for (int i = 0; i < sor; i++)
            {
                for (int j = 0; j < oszlop; j++)
                {
                    cellak[i][j].setEnabled(false);
                    if(cellak[i][j].isBomba())
                    {
                        cellak[i][j].setDisabledIcon((new ImageIcon("bomba.png")));
                    }
                    if(!cellak[i][j].isReveal())
                    {
                        cellak[i][j].setSeen(true);
                    }
                }
            }
        }
    }
}