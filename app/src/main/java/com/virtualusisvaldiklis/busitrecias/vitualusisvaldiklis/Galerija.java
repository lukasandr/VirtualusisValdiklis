package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

public class Galerija extends AppCompatActivity {
    int kiekis;
    int selectedID;
    ImageView [] imgs;
    @RequiresApi(api = Build.VERSION_CODES.M)
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

        imgs = new ImageView[6];
        imgs[0] = (ImageView)findViewById(R.id.imageView1);
        imgs[1] = (ImageView)findViewById(R.id.imageView2);
        imgs[2] = (ImageView)findViewById(R.id.imageView3);
        imgs[3] = (ImageView)findViewById(R.id.imageView4);
        imgs[4] = (ImageView)findViewById(R.id.imageView5);
        imgs[5] = (ImageView)findViewById(R.id.imageView6);

        kiekis = isdestymuSarasas.loadThumbnails(imgs);
        for (int i = 0; i < kiekis; i++)
        {
            final int thisCycleNo = i;
            registerForContextMenu(imgs[i]);
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadLayout(thisCycleNo);
                }
            });
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                // load edit
                return true;
            case R.id.menu_delete:

                recreate();
                return true;
            case R.id.menu_share:
                Intent intent = new Intent(this, DrauguSarasas.class);
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    void loadLayout(int item)
    {
        Intent intent = new Intent(this, PagrindinisLangas.class);
        Bundle b = new Bundle();
        b.putBoolean("loadLayout", true); //Your id
        b.putInt("id", item);
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);

    }
    public void onBackPressed() {
        finish();
    }
}