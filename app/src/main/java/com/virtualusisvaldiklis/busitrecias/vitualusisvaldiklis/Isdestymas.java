package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Context;
import android.os.Environment;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 5/22/2017.
 */

public class Isdestymas {
    String pavadinimas;
    List<Mygtukas> mygtukai;
    public int id;
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
    public void saveLayout(){
        IsdestymuSarasas.isdestymai.add(this);

        //save to memory
        String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString() + "/" + pavadinimas + ".txt";
        String text = pavadinimas + "\n";
        text += mygtukai.size() + "\n";
        for (Mygtukas m : mygtukai) {
            text += m.komanda + " " + m.pozicija.x + " " + m.pozicija.y + "\n";
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(mPath);
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void restoreLayout(){}



}
