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
        isdestymuSarasas.loadThumbnail("pirmas", (ImageView)findViewById(R.id.imageView1));
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, PagrindinisLangas.class);
        startActivity(intent);
    }
}
