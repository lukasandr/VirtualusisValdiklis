package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PagrindinisLangas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    IsdestymuSarasas isdestymuSarasas;
    RelativeLayout isdestymuLaukas;
    PrintWriter outStream;
    InetAddress serverAddr;
    Socket socket;
    Boolean isConnected = false;
    PrintWriter out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isdestymuSarasas = IsdestymuSarasas.getInstance();
        setContentView(R.layout.activity_pagrindinis_langas);
        isdestymuLaukas = (RelativeLayout)findViewById(R.id.isdestymuLaukas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle b = getIntent().getExtras();
        boolean loadLayout = false;
        if(b != null)
        {
            loadLayout = b.getBoolean("loadLayout");
        }
        if (loadLayout)
        {
            int id = b.getInt("id");
            setupButtons(id);
            //promptIP(); prasyti ivesti IP, laikinai isjungta
            //startServer(""); // laikina funckija startuoti serveri
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    void setupButtons(int id)
    {
        Isdestymas isdestymas = IsdestymuSarasas.isdestymai.get(id);
        for(int i = 0; i < isdestymas.mygtukai.size(); i++)
        {
            final Button button = new Button(this);
            button.setText(isdestymas.mygtukai.get(i).komanda);
            //button.setId(id);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_START);
            lp.setMargins(isdestymas.mygtukai.get(i).pozicija.x,isdestymas.mygtukai.get(i).pozicija.y,0,0);
            lp.setMarginStart(isdestymas.mygtukai.get(i).pozicija.x);
            button.setLayoutParams(lp);
            button.setOnClickListener(new View.OnClickListener() { // clicking the button opens edit prompt
                @Override
                public void onClick(View v) {
                //sendToServer(button.getText().toString());
                    try{
                    out.println(button.getText().toString());}
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            button.setMinHeight(0);
            button.setMinWidth(0);
            isdestymuLaukas.addView(button);
        }
    }
    void promptIP()
    {
        final String ip[] = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jugimasis prie serverio");
        builder.setMessage("Iveskite serverio IP adresa (10.37.35.120)");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ip[0] = input.getText().toString();
                startServer(ip[0]);
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


        void startServer(String ip)
    {
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pagrindinis_langas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_galerija) {
            Intent intent = new Intent(this, Galerija.class);
            startActivity(intent);
        } else if (id == R.id.nav_gautiIsdestymai) {
            Intent intent = new Intent(this, GautiIsdestymai.class);
            startActivity(intent);
        } else if (id == R.id.nav_naujasIsdestymas) {
            Intent intent = new Intent(this, MygtukuRedaktorius.class);
            startActivity(intent);
        } else if (id == R.id.nav_view) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void count(){}
    public void showMainMenu(){}
    public void showReceivedLayouts(){}
    public void showMessage(String message){}
    public void showGallery(){}
    public void loadThumbnails(){}
}
