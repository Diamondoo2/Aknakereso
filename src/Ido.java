import javax.swing.*;

public class Ido extends Thread
{
    /**
     * A másodperceket számoló változó.
     */
    private static int k = 0;

    private Timer timer;

    /**
     * Az idő megjelenítésére használt label.
     */
    private final JLabel ide;

    /**
     * A konstruktor megkapja label-t, amelyben az idő meg fog jelenni.
     * @param ide ezen a label-en jelenik meg
     */
    public Ido(JLabel ide) {
        this.ide = ide;
    }


    /**
     * Leállítja az idő számolását.
     */
    public void vege_szamol() {
        timer.stop();
    }


    /**
     * Visszaadja, hogy mennyi idő telt el.
     * @return eltelt idő
     */
    public static int getTime() {
        return k;
    }


    /**
     * Elindítja az idő múlását és megjeleníti a label-ben.
     */
    public void run() {
        k = 0;
        timer = new Timer(1000, e -> {
            ide.setText("Idő: " + k);
            k++;
        });
        timer.start();
    }
}
