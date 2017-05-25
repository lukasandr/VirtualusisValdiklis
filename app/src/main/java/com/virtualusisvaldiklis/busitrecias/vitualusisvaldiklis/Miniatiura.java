package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by Lukas on 2017-05-22.
 */

public class Miniatiura extends AppCompatActivity
{


        public static Bitmap screenShot(View view) {
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
        }

        public static boolean saveToGallery(Bitmap bitmap, String name, Context context) {
            try {
               // Date now = new Date();
               // android.text.format.DateFormat.format(name, now);
                String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString() + "/" + name + ".jpg";
                //String mPath =  now + ".jpg";
                File imageFile = new File(mPath);
                FileOutputStream outputStream = new FileOutputStream(mPath);
                int quality = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                outputStream.flush();
                outputStream.close();
                return true;
            }
            catch (Throwable e) {
                // Several error may come out with file handling or OOM
                e.printStackTrace();
                InformacinisLangas.showMessage("Nepavyko issaugoti! \n Isitikinkite, kad programa turi galimybe pasiekti Failu narsykle (Settings > Apps > Virtualusis Valdiklis > Permissions > Storage)", context);
                final boolean[] temp = new boolean[1];
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                          temp[0] = returnTrue();
                    }
                }, 2000);
                return temp[0];


            }
        }

        private static boolean returnTrue()
        {
            return true;
        }


}
