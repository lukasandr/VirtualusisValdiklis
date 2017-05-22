package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 5/22/2017.
 */

public class Isdestymas {
    String pavadinimas;
    List<Mygtukas> mygtukai;
    public Isdestymas()
    {
        mygtukai = new ArrayList<Mygtukas>();
    }
    public void MoveButton(int id, int x, int y)
    {
        if (mygtukai == null) return;
        if (id < mygtukai.size())
        {
            mygtukai.get(id).ChangePosition(x,y);
        }
    }
    public void SaveLayout(){
        IsdestymuSarasas.isdestymai.add(this);
    }
    public void RestoreLayout(){}

}
