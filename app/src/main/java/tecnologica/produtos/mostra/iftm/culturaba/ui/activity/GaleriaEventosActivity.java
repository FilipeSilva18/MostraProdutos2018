package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.dao.EventoDatabase;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;
import tecnologica.produtos.mostra.iftm.culturaba.ui.adapter.AdapterEvento;
import tecnologica.produtos.mostra.iftm.culturaba.ui.adapter.AdapterEventoSalvo;

public class GaleriaEventosActivity extends AppCompatActivity {

    private ListView lista;
    private List<Evento> eventos;
    private AdapterEventoSalvo adapter;
    private EventoDatabase eventoDatabase;
    private LinearLayout listaVazia;


    @Override
    public void onResume() {
        super.onResume();
        eventos = eventoDatabase.daoAccess().findAll();
        if (eventos.isEmpty()) {
            listaVazia.setVisibility(View.VISIBLE);
            adapter = new AdapterEventoSalvo( new ArrayList<Evento>(), this);
            lista.setAdapter(adapter);
        } else {
            listaVazia.setVisibility(View.GONE);
            adapter = new AdapterEventoSalvo(eventos, this);

        }
        lista.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_eventos);

        lista = findViewById(R.id.list_eventos_salvos);
        listaVazia = findViewById(R.id.view_empyt_list);
        eventoDatabase = EventoDatabase.getInstance(this);

        eventos = new ArrayList<>();

        eventos.addAll(eventoDatabase.daoAccess().findAll());

        adapter = new AdapterEventoSalvo(eventos, this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(view.getContext(), EventoActivity.class);
                it.putExtra("evento", adapter.getItem(position));
                it.putExtra("galeria", "galeria");
                startActivity(it);

            }
        });

    }
}
