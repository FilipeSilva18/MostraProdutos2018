package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;
import tecnologica.produtos.mostra.iftm.culturaba.ui.adapter.AdapterEvento;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lista;
    List<Evento> eventos;
    AdapterEvento adapter;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = findViewById(R.id.list_eventos);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        eventos = new ArrayList<>();
        eventos.add(new Evento("Filipe", "Teste", "25/10/2018", "26/10/2018", "12:00", "14:00", "DANCA", "Rua dos laaaaaaairios", "https://i.ytimg.com/vi/zL5nyXPjJjU/maxresdefault.jpg"));
        eventos.add(new Evento("Filipe", "Teste", "25/10/2018", "26/10/2018", "12:00", "14:00", "DANCA", "Rua dos lirdasdasios", "http://2.bp.blogspot.com/-z9-2nuEr4yA/VNuzF_4e-0I/AAAAAAAABA8/FCAXel8PVjI/s1600/7%C2%BA%2BSIMAE.jpg"));
        eventos.add(new Evento("Filipe", "Teste", "25/10/2018", "26/10/2018", "12:00", "14:00", "DANCA", "Rua dos lirwwwwwwios", "http://2.bp.blogspot.com/-z9-2nuEr4yA/VNuzF_4e-0I/AAAAAAAABA8/FCAXel8PVjI/s1600/7%C2%BA%2BSIMAE.jpg"));
        adapter = new AdapterEvento(eventos, this);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Seu codigo aqui
                System.out.println(adapter.getItem(position).getEndereco());
                Intent it = new Intent(view.getContext(), EventoActivity.class);
                it.putExtra("evento", adapter.getItem(position));
                startActivity(it);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
            System.out.println("INICIO");
        } else if (id == R.id.nav_gallery) {
            System.out.println("meus eventos");
        } else if (id == R.id.nav_slideshow) {
            System.out.println("Perfil");
        } else if (id == R.id.nav_manage) {
            System.out.println("Sobre");
        } else if (id == R.id.nav_logout) {
            System.out.println("Logout");
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
