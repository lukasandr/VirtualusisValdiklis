package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Galerija extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerija);
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
        IsdestymuSarasas isdestymuSarasas = IsdestymuSarasas.getInstance();
      //  isdestymuSarasas.loadThumbnails("pirmas", (ImageView)findViewById(R.id.imageView1));

        ImageView [] imgs = new ImageView[6];
        imgs[0] = (ImageView)findViewById(R.id.imageView1);
        imgs[1] = (ImageView)findViewById(R.id.imageView2);
        imgs[2] = (ImageView)findViewById(R.id.imageView3);
        imgs[3] = (ImageView)findViewById(R.id.imageView4);
        imgs[4] = (ImageView)findViewById(R.id.imageView5);
        imgs[5] = (ImageView)findViewById(R.id.imageView6);

        isdestymuSarasas.loadThumbnails(imgs);

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, PagrindinisLangas.class);
        startActivity(intent);
    }
}
