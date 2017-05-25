package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.drm.DrmInfoRequest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DrauguSarasas extends AppCompatActivity {
    public int countFriends = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draugu_sarasas);
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
        showFriendsLis();
    }

    public void onBackPressed() {
        finish();
    }


    public void showFriendsLis(){
        //takes data from the phone memory and makes a list of friends
        if(countFriends == 0) InformacinisLangas.showMessage("Jus neturite draugu!", this);

    }

    public void onFriendSelected(){
        //determins which friend is selected

        try
        {
            //contacts Google Firebase and sends Isdestymas to it
            InformacinisLangas.showMessage("Sekmingai issiusta!", this);
        }catch(Exception e)
        {
            InformacinisLangas.showMessage("Issiusti neapvyko!", this);
        }
        }
    }







