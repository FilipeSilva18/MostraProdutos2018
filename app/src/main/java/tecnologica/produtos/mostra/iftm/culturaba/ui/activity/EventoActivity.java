package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.dao.DaoAccess;
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
    private EventoDatabase eventoDatabase ;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        evento = getIntent().getExtras().getParcelable("evento");

        eventoDatabase = EventoDatabase.getInstance(this);
        imagem = findViewById(R.id.img_evento_selecionado);
        titulo = findViewById(R.id.tv_titulo_evento_selecionado);
        data = findViewById(R.id.tv_data_evento_selecionado);
        local = findViewById(R.id.tv_local_evento_selecionado);
        descricao = findViewById(R.id.tv_descricao_evento_selecionado);
        fabSalvar = findViewById(R.id.fab);
        linearLayout = findViewById(R.id.activity_Evento);

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventoDatabase.daoAccess().insertEvento(evento);
                Snackbar.make(linearLayout, "Salvo com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                List<Evento> eventoList = eventoDatabase.daoAccess().findAll();
                System.out.println("EVENTOS: " + eventoList.get(0).getNome());
                System.out.println("EVENTOS: " + eventoList.get(1).getNome());

            }
        });

        preencher();

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
