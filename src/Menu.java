import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel
{

    /**
     * A konstruktor a menü panel létrehozására szolgál.
     */
    public Menu()
    {
        JButton uj_jatek_gomb = new JButton("Új játék");
        uj_jatek_gomb.setBackground(new Color(48, 253, 0));
        JButton ranglista_gomb = new JButton("Ranglista");
        ranglista_gomb.setBackground(new Color(252, 232, 7));
        JButton kilepes_gomb = new JButton("Kilépés");
        kilepes_gomb.setBackground(new Color(255, 0, 0));
        JPanel menu_panel = new JPanel();
        menu_panel.add(uj_jatek_gomb);
        menu_panel.add(ranglista_gomb);
        menu_panel.add(kilepes_gomb);
        add(menu_panel);
        uj_jatek_gomb.addActionListener(new Fomenu.UjjatekListener());
        kilepes_gomb.addActionListener(new Fomenu.KilepesListener());
        ranglista_gomb.addActionListener(new Fomenu.RanglistaListener());
    }

}