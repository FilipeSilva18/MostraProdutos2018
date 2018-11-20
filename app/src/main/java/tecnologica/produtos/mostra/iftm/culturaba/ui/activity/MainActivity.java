package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;
import tecnologica.produtos.mostra.iftm.culturaba.ui.adapter.AdapterEvento;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnEventoListenner {
    private ListView lista;
    public static List<Evento> eventos;
    private AdapterEvento adapter;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button btn;
    private DatabaseReference myRef;
    private OnEventoListenner onEventoListenner;


    @Override
    public void onResume() {
        super.onResume();

        myRef.child("evento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventos = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("DEU CERTO");
                    Evento e = new Evento(noteDataSnapshot.child("nome").getValue(String.class), noteDataSnapshot.child("descricao").getValue(String.class), noteDataSnapshot.child("dataInicio").getValue(String.class),
                            noteDataSnapshot.child("dataFim").getValue(String.class), noteDataSnapshot.child("horaInicio").getValue(String.class), noteDataSnapshot.child("horaFim").getValue(String.class),
                            noteDataSnapshot.child("tipo").getValue(String.class), noteDataSnapshot.child("endereco").getValue(String.class), noteDataSnapshot.child("imagem").getValue(String.class));
                    eventos.add(e);

                }
                onEventoListenner.onEventoListener();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = findViewById(R.id.list_eventos);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        btn = findViewById(R.id.btn_cadastrar_evento);
        onEventoListenner = this;
        myRef = FirebaseDatabase.getInstance().getReference();
        eventos = new ArrayList<>();

        if (currentUser.getEmail().equals("joao_junior_14@hotmail.com"))
            btn.setVisibility(View.VISIBLE);
        else
            btn.setVisibility(View.GONE);


        myRef.child("evento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventos = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("DEU CERTO");
                    Evento e = new Evento(noteDataSnapshot.child("nome").getValue(String.class), noteDataSnapshot.child("descricao").getValue(String.class), noteDataSnapshot.child("dataInicio").getValue(String.class),
                            noteDataSnapshot.child("dataFim").getValue(String.class), noteDataSnapshot.child("horaInicio").getValue(String.class), noteDataSnapshot.child("horaFim").getValue(String.class),
                            noteDataSnapshot.child("tipo").getValue(String.class), noteDataSnapshot.child("endereco").getValue(String.class), noteDataSnapshot.child("imagem").getValue(String.class));
                    eventos.add(e);

                }
                onEventoListenner.onEventoListener();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(view.getContext(), EventoActivity.class);
                it.putExtra("evento", adapter.getItem(position));
                it.putExtra("salvar", "salvar");
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
            startActivity(new Intent(this, GaleriaEventosActivity.class));
        } else if (id == R.id.nav_slideshow) {
            System.out.println("Perfil");
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, SobreActivity.class));
        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cadastrarEvento(View view) {
        startActivity(new Intent(this, CadastroEventoActivity.class));
    }

    @Override
    public void onEventoListener() {
        adapter = new AdapterEvento(eventos, this);
        lista.setAdapter(adapter);

    }


}

interface OnEventoListenner {
    void onEventoListener();
}