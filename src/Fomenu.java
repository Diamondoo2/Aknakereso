import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fomenu extends JFrame
{
    private static int currentCard = 1;
    private static CardLayout cl;
    static JPanel cardPanel = new JPanel();
    public static void main(String[] args)
    {
        Fomenu ablak = new Fomenu();
        ablak.getContentPane().setBackground(Color.GRAY);
    }

    /**
     * A Fomenu osztály konstruktora hozza létre a Cardpaneleket.
     */
    public Fomenu()
    {
        setTitle("Aknakereső");
        setSize(1500,800);

        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel menu = new Menu();
        JPanel nehezseg = new Nehezseg();
        JPanel ranglista = new Ranglista();

        cardPanel.add(menu, "1");
        cardPanel.add(nehezseg, "2");
        cardPanel.add(ranglista, "3");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * A Kilépés gomb ActionListenere.
     */
    static class KilepesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            System.exit(0);
        }
    }

    /**
     * Az Új játék gomb ActionListenere.
     */
    static class UjjatekListener implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
                currentCard = 2;
                cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Ranglista gomb ActionListenere.
     */
    static class RanglistaListener implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            currentCard = 3;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Vissza gomb ActionListenere.
     */
    static class VisszaListener implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {

            if(currentCard == 3 || currentCard == 4 || currentCard == 2)
            {
                currentCard = 1;
            }
            else if(currentCard == 6)
            {
                currentCard = 3;
            }
            else if (currentCard == 7 || currentCard == 8 || currentCard == 9)
            {
                currentCard = 2;
            }
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Könnyű gomb ActionListenere.
     */
    static class KonnyuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel leaderboard = new Ranglista.LeaderBoard(0);

            cardPanel.add(leaderboard, "6");
            currentCard = 6;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Közepes gomb ActionListenere.
     */
    static class KozepesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel leaderboard = new Ranglista.LeaderBoard(1);

            cardPanel.add(leaderboard, "6");
            currentCard = 6;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Nehéz gomb ActionListenere.
     */
    static class NehezListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel leaderboard = new Ranglista.LeaderBoard(2);

            cardPanel.add(leaderboard, "6");
            currentCard = 6;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Könnyű nehézség gomb ActionListenere.
     */
    static class Start_Kony_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel palya = new Palya(9, 9, 10);
            cardPanel.add(palya, "7");
            currentCard = 7;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Közepes nehézség gomb ActionListenere.
     */
    static class Start_Koz_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel palya = new Palya(16, 16, 40);

            cardPanel.add(palya, "8");
            currentCard = 8;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A Nehéz nehézség gomb ActionListenere.
     */
    static class Start_Nehez_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JPanel palya = new Palya(30, 16, 99);

            cardPanel.add(palya, "9");
            currentCard = 9;
            cl.show(cardPanel, "" + (currentCard));
        }
    }
}
