package com.virtualusisvaldiklis.busitrecias.vitualusisvaldiklis;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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
        // load isdestymas
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
                //todo - send string to server
                    //String message = button.getText().toString();

                }
            });
            button.setMinHeight(0);
            button.setMinWidth(0);
            isdestymuLaukas.addView(button);
        }
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

}
