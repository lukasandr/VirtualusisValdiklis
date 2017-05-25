package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class GautiIsdestymai extends AppCompatActivity {
    public int countFriends = 0;
    ImageView[] receivedImgs;
    IsdestymuSarasas gautuIsdestymuSarasas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauti_isdestymai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(countFriends == 0) InformacinisLangas.showMessage("Gautu isdestymu nera", this);
        receivedImgs = new ImageView[6];
    }

    //TODO: papildyti kad im,tu is atminties kazkoki .txt

    public void showReceived(){
        if(countFriends == 0) InformacinisLangas.showMessage("Gautu isdestymu nera", this);
        gautuIsdestymuSarasas = IsdestymuSarasas.getInstance();
        gautuIsdestymuSarasas.loadThumbnails(receivedImgs, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
    }

    public void onCreateContextMenu(){}

    public void saveToGallery(){
        gautuIsdestymuSarasas.getIsdestymas();
        try{
            //Save to gallert
            //isdestymas.saveLayout()
            InformacinisLangas.showMessage("Sekmingai issaugota!", this);

        }catch(Exception e){
            //Nepavyko issaugoti
            InformacinisLangas.showMessage("Issaugoti nepavyko!", this);
        }
    }

    public void deleteReceivedLayout()
    {
        gautuIsdestymuSarasas.getIsdestymas();
        try{
            //Delete Layout
            InformacinisLangas.showMessage("Sekmingai istrinta!", this);

        }catch(Exception e){
            //Nepavyko istrinti
            InformacinisLangas.showMessage("istrinti nepavyko!", this);
        }
    }


    public void onBackPressed() {
        finish();
    }




}
