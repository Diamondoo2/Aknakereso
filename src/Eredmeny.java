public class Eredmeny
{
    /**
     * A játékos felhasználóneve.
     */
    private final String nev;


    /**
     * A pálya teljesítésének az ideje.
     */
    private final int ido;

    Eredmeny(String a, int b)
    {
        nev = a;
        ido = b;
    }

    /**
     * A függvény visszaadja a játékos nevét.
     * @return String típusú név
     */
    public String getNev()
    {
        return nev;
    }

    /**
     * A függvény visszaadja a pálya teljesítésének az idejét.
     * @return int típusú idő
     */
    public int getIdo()
    {
        return ido;
    }
}
