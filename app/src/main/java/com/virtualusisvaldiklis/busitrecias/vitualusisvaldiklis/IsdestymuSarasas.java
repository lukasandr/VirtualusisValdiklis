package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 2017-05-22.
 */

public class IsdestymuSarasas extends AppCompatActivity {
    public static List<Isdestymas> isdestymai = new ArrayList<Isdestymas>();
    private static IsdestymuSarasas isdestymuSarasas = null;
    protected IsdestymuSarasas() {
        // Exists only to defeat instantiation.
    }
    public static IsdestymuSarasas getInstance(){
        if(isdestymuSarasas == null) {
            isdestymuSarasas = new IsdestymuSarasas();
        }
        return isdestymuSarasas;
    }


    public static int Count() // grazina isdestymu skaiciu
    {
        if (isdestymai == null) return 0;
        else return isdestymai.size();
    }

    public void loadThumbnail(String name, ImageView img) {

        try {
            String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + name + ".jpg";
            File imageFile = new File(mPath);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(imageFile));
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }





}
