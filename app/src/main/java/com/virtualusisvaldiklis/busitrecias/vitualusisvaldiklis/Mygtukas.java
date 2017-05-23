package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

/**
 * Created by Tomas on 5/22/2017.
 */
public class Mygtukas {
    public Pozicija pozicija;
    String komanda;
    int id;
    public Mygtukas(int x, int y, int id, String komanda)
    {
        pozicija = new Pozicija(x,y);
        this.komanda = komanda;
        this.id = id;
    }
    public void ChangePosition(int x, int y)
    {
        pozicija.x = x;
        pozicija.y = y;
    }

}
class Pozicija
{
    public int x;
    public int y;
    public Pozicija(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
