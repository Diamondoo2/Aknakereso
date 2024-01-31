import javax.swing.*;
import java.awt.*;

public class Nehezseg extends JPanel
{
    /**
     * A konstruktor a Nehézség választás panelt hozza létre.
     */
    public Nehezseg()
    {
        JButton konnyu_gomb = new JButton("Könnyű");
        konnyu_gomb.setBackground(new Color(48, 253, 0));
        JButton kozepes_gomb = new JButton("Közepes");
        kozepes_gomb.setBackground(new Color(252, 232, 7));
        JButton nehez_gomb = new JButton("Nehéz");
        nehez_gomb.setBackground(new Color(253, 30, 0));
        JButton vissza_gomb = new JButton("Vissza");
        JPanel nehezseg_panel = new JPanel();
        nehezseg_panel.add(konnyu_gomb);
        nehezseg_panel.add(kozepes_gomb);
        nehezseg_panel.add(nehez_gomb);
        nehezseg_panel.add(vissza_gomb);
        add(nehezseg_panel);
        vissza_gomb.addActionListener(new Fomenu.VisszaListener());
        konnyu_gomb.addActionListener(new Fomenu.Start_Kony_Listener());
        kozepes_gomb.addActionListener(new Fomenu.Start_Koz_Listener());
        nehez_gomb.addActionListener(new Fomenu.Start_Nehez_Listener());
    }
}
