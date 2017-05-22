package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import java.util.List;

/**
 * Created by Lukas on 2017-05-22.
 */

public class IsdestymuSarasas {
    public List<Isdestymas> isdestymai;
    public int Count() // grazina isdestymu skaiciu
    {
        if (isdestymai == null) return 0;
        else return isdestymai.size();
    }

}
