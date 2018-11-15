package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.dao.EventoDatabase;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

public class EventoActivity extends AppCompatActivity {
    private Evento evento;
    private ImageView imagem;
    private TextView titulo;
    private TextView data;
    private TextView local;
    private TextView descricao;
    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabRemover;
    private EventoDatabase eventoDatabase;
    private LinearLayout linearLayout;
    private boolean galeria;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        galeria = false;
        eventoDatabase = EventoDatabase.getInstance(this);
        imagem = findViewById(R.id.img_evento_selecionado);
        titulo = findViewById(R.id.tv_titulo_evento_selecionado);
        data = findViewById(R.id.tv_data_evento_selecionado);
        local = findViewById(R.id.tv_local_evento_selecionado);
        descricao = findViewById(R.id.tv_descricao_evento_selecionado);
        fabSalvar = findViewById(R.id.fab);
        fabRemover = findViewById(R.id.fab_remove);
        linearLayout = findViewById(R.id.activity_Evento);

        evento = getIntent().getExtras().getParcelable("evento");


        fabSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (eventoDatabase.daoAccess().findEventoById(evento.getImagem()) == null) {
                    eventoDatabase.daoAccess().insertEvento(evento);
                    Snackbar.make(linearLayout, "Salvo com sucesso!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(linearLayout, "Evento já cadastrado!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        fabRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(galeria)
                eventoDatabase.daoAccess().deleteByID(evento.getImagem());
                else eventoDatabase.daoAccess().deleteByID(evento.getEndereco());

                Snackbar.make(linearLayout, "Removido com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (eventoDatabase.daoAccess().findEventoById(evento.getImagem() + "") != null) {
            fabSalvar.setVisibility(View.GONE);
            fabRemover.setVisibility(View.VISIBLE);
        } else {
            fabSalvar.setVisibility(View.VISIBLE);
            fabRemover.setVisibility(View.GONE);
        }

        if (getIntent().hasExtra("galeria")) {
            fabSalvar.setVisibility(View.GONE);
            fabRemover.setVisibility(View.VISIBLE);
            galeria = true;
            preencher1();
        } else preencher();

    }

    private void preencher1() {
        Picasso.get()
                .load(evento.getImagem())
                .resize(6000, 2000)
                .onlyScaleDown()
                .into(imagem);

        titulo.setText(evento.getNome());
        local.setText(evento.getEndereco());
        data.setText(evento.getDataInicio());
        descricao.setText(evento.getDescricao());
    }

    private void preencher() {
        Picasso.get()
                .load(evento.getEndereco())
                .resize(6000, 2000)
                .onlyScaleDown()
                .into(imagem);

        titulo.setText(evento.getNome());
        local.setText(evento.getImagem());
        data.setText(evento.getDataInicio());
        descricao.setText(evento.getDescricao());
    }
}
