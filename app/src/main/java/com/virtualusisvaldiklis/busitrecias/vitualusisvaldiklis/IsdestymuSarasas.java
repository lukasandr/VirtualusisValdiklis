package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

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

    private void loadThumbnail(String name, ImageView img) {

        try {
            String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString() + "/" + name + ".jpg";
            File imageFile = new File(mPath);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(imageFile));
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void loadThumbnails(ImageView [] imgs)
    {
        String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString();
        int count = 0;
        File [] isdestymai = new File [6];
        File [] miniatiuros = new File[6];
        isdestymai = findFilesInDir(mPath, ".txt");
        miniatiuros = findFilesInDir(mPath, ".jpg");

        int kiekis = 0;
        if (miniatiuros.length > 6 ) kiekis = 6;
        else {
            kiekis = miniatiuros.length;
        }


        for(int i = 0; i < kiekis; i++){
            try {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(miniatiuros[i]));
                imgs[i].setImageBitmap(b);
                isdestymoSukurejas(isdestymai[i]);

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

    }
    private File[] findFilesInDir(String dirName, final String ending) {
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.toLowerCase().endsWith(ending);
            }
        });
    }

    private Isdestymas isdestymoSukurejas(File txtFile)
    {
        Isdestymas isd = new Isdestymas();
        String pavadinimas;
        int mygtukuKiekis = 0;
        int mygX;
        int mygY;
        String mygPavadinimas;
        int id;

        try {
            Scanner sc = new Scanner(txtFile);
            pavadinimas = sc.next();
            isd.pavadinimas = pavadinimas;
            mygtukuKiekis = sc.nextInt();
            for(int i = 0; i<mygtukuKiekis; i++)
            {
                mygPavadinimas = sc.next();
                mygX = sc.nextInt();
                mygY = sc.nextInt();
                isd.mygtukai.add(i,new Mygtukas(mygX, mygY, i, mygPavadinimas));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return isd;
    }

 }
