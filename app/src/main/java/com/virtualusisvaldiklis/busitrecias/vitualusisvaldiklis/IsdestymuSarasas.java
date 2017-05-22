package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 2017-05-22.
 */

public class IsdestymuSarasas {
    public static List<Isdestymas> isdestymai = new ArrayList<Isdestymas>();
    public static int Count() // grazina isdestymu skaiciu
    {
        if (isdestymai == null) return 0;
        else return isdestymai.size();
    }

}
