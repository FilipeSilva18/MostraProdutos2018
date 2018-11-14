package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

public class EventoActivity extends AppCompatActivity {
    Evento evento;
    ImageView imagem;
    TextView titulo;
    TextView data;
    TextView local;
    TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        evento = getIntent().getExtras().getParcelable("evento");

        imagem = findViewById(R.id.img_evento_selecionado);
        titulo = findViewById(R.id.tv_titulo_evento_selecionado);
        data = findViewById(R.id.tv_data_evento_selecionado);
        local = findViewById(R.id.tv_local_evento_selecionado);
        descricao = findViewById(R.id.tv_descricao_evento_selecionado);

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
