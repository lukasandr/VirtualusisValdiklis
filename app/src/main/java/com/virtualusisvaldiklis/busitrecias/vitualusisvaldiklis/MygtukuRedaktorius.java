package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MygtukuRedaktorius extends AppCompatActivity {

    private static final String DEBUGTAG = "BusiTrecias";

    public int newButtonLocationX;
    public int newButtonLocationY;
    RelativeLayout isdestymuLaukas;
    FloatingActionButton saveLayout;
    int buttonIDCount;
    Isdestymas isdestymas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonIDCount = 0;
        isdestymas = new Isdestymas();
        setContentView(R.layout.activity_mygtuku_redaktorius);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        saveLayout = (FloatingActionButton) findViewById(R.id.saveLayout);
        saveLayout.setActivated(false);
        isdestymuLaukas = (RelativeLayout) findViewById(R.id.isdestymuLaukas);
        //addTouchListener();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Snackbar.make(view, "Paspauskite ant ekrano ten, kur norite issaugoti", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();
                                       listenForInput();
                                   }

                               }

        );
        saveLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveLayoutClicked();
            }
        });
    }
    void saveLayoutClicked()
    {
        final String pav[] = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Issaugoti isdestyma");
        builder.setMessage("Iveskite isdestymo pavadinima:");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isdestymuLaukas.setOnTouchListener(null); // disable adding buttons by clicking on screen
                pav[0] = input.getText().toString();
                saveLayout(pav[0]);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    void saveLayout(String name)
    {
        isdestymas.pavadinimas = name;
        isdestymas.saveLayout();
        Bitmap screenshot = Miniatiura.screenShot(findViewById(R.id.isdestymuLaukas));
        if(Miniatiura.saveToGallery(screenshot, name, this)) finish();


    }
    void listenForInput()
    {
        isdestymuLaukas.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    if(event.getAction() == MotionEvent.ACTION_UP)
                    {
                        int x, y;
                        x = (int)event.getX();
                        y = (int)event.getY();
                        createNewButtonPrompt(x, y);
                    }
                    return true;
                }

            });
    }

    void createNewButtonPrompt(int x, int y)
    {
        final String[] komanda = new String[1];
        final int posX = x;
        final int posY = y;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Naujo mygtuko sukurimas");
        builder.setMessage("Iveskite komanda (1 simbolis arba LEFT, RIGHT, UP, DOWN):");
// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isdestymuLaukas.setOnTouchListener(null); // disable adding buttons by clicking on screen
                komanda[0] = input.getText().toString();
                createButton(posX, posY, komanda[0]);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    void createButton(int x, int y, String komanda)
    {
        final String[] _komanda = new String[1];
        _komanda[0] = komanda;
        buttonIDCount++;
        final Button button = new Button(this);
        button.setText(komanda);
        button.setId(buttonIDCount);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_START);
        lp.setMargins(x,y,0,0);
        lp.setMarginStart(x);
        button.setLayoutParams(lp);
        button.setOnClickListener(new View.OnClickListener() { // clicking the button opens edit prompt
            @Override
            public void onClick(View v) {
                String naujaKomanda = editButtonPrompt(_komanda[0]);
                button.setText(naujaKomanda);
            }
        });
        button.setMinHeight(0);
        button.setMinWidth(0);
        isdestymuLaukas.addView(button);
        Mygtukas mygtukas = new Mygtukas(x, y, buttonIDCount, button.getText().toString());
        isdestymas.mygtukai.add(mygtukas);
        if (saveLayout.isActivated() == false) saveLayout.setActivated(true);
    }
    String editButtonPrompt(String komanda)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Redagavimas");
        builder.setMessage("Iveskite nauja komanda (1 simbolis arba LEFT, RIGHT, UP, DOWN):");
// Set up the input
        final EditText input = new EditText(this);
        final String[] naujaKomanda = new String[1];
        final boolean[] isCancel = {true};
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                naujaKomanda[0] = input.getText().toString();
                isCancel[0] = false;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        if(!isCancel[0])
            return naujaKomanda[0];
        else
            return komanda;
    }
    public void onBackPressed() {
//        Intent intent = new Intent(this, PagrindinisLangas.class);
//        startActivity(intent);
            finish();
    }






}
