import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell_button extends JButton {

    /**
     * Bomba-e a mező.
     */
    private boolean bomba;

    /**
     * Zászló-e a mező.
     */
    private boolean zaszlo;

    /**
     * Már felfedett-e a mező.
     */
    private boolean seen;

    /**
     * Egy mező szomszédjait tároló lista.
     */
    private final List<Cell_button> szomszedok = new ArrayList<>();

    /**
     * Maga a pálya.
     */
    private final Palya palya;

    /**
     * X koordináta.
     */
    private final int x;

    /**
     * Y koordináta.
     */
    private final int y;

    /**
     * Ikonokat tároló Hashmap.
     */
    private final Map<Integer, Icon> icons = new HashMap<>();

    /**
     * A bal vagy jobb klikket megkülönböztető MouseAdapter.
     */
    private class Click_Listener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                leftClicked();
                palya.setElso_kattintas(false);
            }
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                rightClicked();
            }
        }
    }

    /**
     * A bal klikkre, ha az bombára történik, akkor, ha első kattintás volt,
     * akkor meghívja a bombát elrakó change_bomba függvényt, egyébként a boom függvényt.
     * Ha nem bombás mezőre kattintott a felhasználó, akkor a szomszédok alapján megszámolja,
     * hogy hány bomba van a környezetében.
     */
    public void leftClicked()
    {
        if (bomba)
        {
            if (palya.isElso_kattintas())
            {
                palya.change_bomba();
                bomba = false;
            }
            else
            {
                boom();
                return;
            }
        }

        int szomszedos_bombak = 0;
        for (Cell_button szomszed : szomszedok)
        {
            if (szomszed.isBomba())
            {
                szomszedos_bombak++;
            }
        }
        reveal(szomszedos_bombak);
    }


    /**
     * Bombára történő kattintáskor hívódik meg, beállítja a bomba ikont és
     * meghívja az end_game függvényt.
     */
    private void boom()
    {
        setIcon(new ImageIcon("bomba.png"));
        palya.end_game();
    }


    /**
     * Az adott mező, ha zászló, akkor nem történik semmi, viszont, ha nem,
     * akkor beállítja már felfedettre és az ikonját, ha értéke nulla, akkor
     * pedig a szomszédosokat is felfedi.
     * @param bombak a mező környezetében lévő bombák számát adja meg.
     */
    public void reveal(int bombak)
    {
        if(!zaszlo)
        {
            seen = true;
            setIcon(icons.get(bombak));
            setDisabledIcon(icons.get(bombak));
            setEnabled(false);
        }
        if (bombak == 0)
        {
            for (Cell_button szomszed : szomszedok)
            {
                szomszed.szomszedReveal(this);
            }
        }
    }

    /**
     * Abban az esetben, ha a mező már fel van fedve vagy meg van jelölve zászlóval,
     * akkor nem történik semmi, viszont, ha nem, akkor rekurzióval rovábbterjed a
     * feledés.
     * @param sender melyik mezőről érkezett a hívás
     */
    private void szomszedReveal(Cell_button sender)
    {
        if (seen) return;
        if (zaszlo) return;

        int szomszedos_bombak = 0;
        for (Cell_button szomszed : szomszedok)
        {
            if (sender == szomszed) continue; // rekurziót kihagy

            if (szomszed.isBomba())
            {
                szomszedos_bombak++;
            }
        }
        reveal(szomszedos_bombak);
    }


    /**
     * A jobb klikket megvalósító függvény, amely felfedett mezőre érvénytelen,
     * más esetben megjelöli egy zászlóval a mezőt, ha pedig már megjelölt,
     * akkor pedig leszedi róla.
     */
    private void rightClicked()
    {
        if(seen)
        {
            return;
        }
        if (zaszlo)
        {
            zaszlo = false;
            setIcon(null);
            palya.zaszlo_removed(this);
        }
        else
        {
            zaszlo = true;
            setIcon(new ImageIcon("flag.png"));
            palya.zaszlo_added(this);
        }
    }


    /**
     * A konstruktor feltölti a hashmap-et a megfelelő ikonokkal.
     * Beállítja a mezőkre a méretet és Click_Listenert.
     * @param palya pálya
     * @param x koordináta
     * @param y koordináta
     */
    public Cell_button(Palya palya, int x, int y)
    {
        this.palya = palya;
        this.x = x;
        this.y = y;
        addMouseListener(new Click_Listener());
        setPreferredSize(new Dimension(40, 40));

        icons.put(0, new ImageIcon("nulla.png"));
        icons.put(1, new ImageIcon("egy.png"));
        icons.put(2, new ImageIcon("ketto.png"));
        icons.put(3, new ImageIcon("harom.png"));
        icons.put(4, new ImageIcon("negy.png"));
        icons.put(5, new ImageIcon("ot.png"));
        icons.put(6, new ImageIcon("hat.png"));
        icons.put(7, new ImageIcon("het.png"));
        icons.put(8, new ImageIcon("nyolc.png"));
    }


    /**
     * A helyes működés ellenőrzésére.
     * @return String a koordinátákkal.
     */
    @Override
    public String toString()
    {
        return "Cella[" + x + "][" + y + "]";
    }


    /**
     * Az arraylist-be beleteszi a mező zomszédját.
     * @param szomszed szomszédos mező
     */
    public void szomszed_hozzaad(Cell_button szomszed)
    {
        szomszedok.add(szomszed);
    }


    /**
     * Lekérdezhető, hogy bomba-e a mező.
     * @return boolean bomba-e
     */
    public boolean isBomba()
    {
        return bomba;
    }

    /**
     * Lekérdezhető, hogy felfedett-e a mező.
     * @return boolean felfedett-e
     */
    public boolean isReveal()
    {
        return seen;
    }

    /**
     * Lekérdezhető, hogy zászló-e a mező.
     * @return boolean zászló-e
     */
    public boolean isZaszlo()
    {
        return zaszlo;
    }

    /**
     * Beállítható, hogy bomba legyen-e a mező.
     * @param bomba boolean bomba legyen-e
     */
    public void setBomba(boolean bomba)
    {
        this.bomba = bomba;
    }

    /**
     * Beállítható, hogy zászló legyen-e a mező.
     * @param zaszlo boolean zászló legyen-e
     */
    public void setZaszlo(boolean zaszlo)
    {
        this.zaszlo = zaszlo;
    }

    /**
     * Beállítható, hogy felfedett legyen-e a mező.
     * A játék elvesztésekor van rá szükség az összes mező felfedéséhez.
     * @param feltarva booelan fel van-e fedve
     */
    public void setSeen(boolean feltarva)
    {
        seen = feltarva;
        if(feltarva)
        {
            leftClicked();
        }
    }
}
