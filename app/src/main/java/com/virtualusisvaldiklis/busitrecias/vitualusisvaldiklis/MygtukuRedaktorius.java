package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MygtukuRedaktorius extends AppCompatActivity {

    private static final String DEBUGTAG = "BusiTrecias";

    public int newButtonLocationX;
    public int newButtonLocationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygtuku_redaktorius);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //addTouchListener();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Touch screen where you want the button to appear", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }

        );


    }

    public void onBackPressed() {
        Intent intent = new Intent(this, PagrindinisLangas.class);
        startActivity(intent);

    }

    //Sitas crashina
    //Dariau pagal totoriala: https://www.youtube.com/watch?v=ccbMZLuVLLw
    /*public void addTouchListener(){
        Image image = (Image) findViewById(R.id.mygtukuRedaktorius);

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newButtonLocationX = (int)event.getX();
                newButtonLocationY = (int)event.getY();

                String message = String.format("Coordinates: (%, %)", newButtonLocationX, newButtonLocationY);
                Log.d(DEBUGTAG, message);
                return false;
            }
        });
    }*/








}
