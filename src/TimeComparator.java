import java.util.Comparator;

public class TimeComparator implements Comparator<Eredmeny>
{
    /**
     * Két Eredmény típusú objektum összevetése idő alapján.
     * @param o1 egyik objektum
     * @param o2 másik objektum
     * @return különbséget ad vissza
     */
    public int compare(Eredmeny o1, Eredmeny o2)
    {
        return o1.getIdo()-o2.getIdo();
    }
}
