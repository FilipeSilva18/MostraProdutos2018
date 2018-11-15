package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    EditText nome;
    EditText tipo;
    EditText descricao;
    EditText horaIni;
    EditText horaFim;
    EditText dataIni;
    EditText dataFim;
    EditText imagem;
    EditText endereco;
    Evento evento;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        nome = findViewById(R.id.tv_nome_evento);
        tipo = findViewById(R.id.tv_tipo_evento);
        descricao = findViewById(R.id.tv_descricao_evento);
        horaIni = findViewById(R.id.tv_hora_inicio_evento);
        horaFim = findViewById(R.id.tv_hora_final_evento);
        dataIni = findViewById(R.id.tv_data_inicio_evento);
        dataFim = findViewById(R.id.tv_data_final_evento);
        imagem = findViewById(R.id.tv_url_evento);
        endereco = findViewById(R.id.tv_local_evento);
        myRef =  FirebaseDatabase.getInstance().getReference();

    }

    public void cadastrarEvento(View view) {
        evento = new Evento(nome.getText().toString(), descricao.getText().toString(), dataIni.getText().toString(), dataFim.getText().toString(),
                horaIni.getText().toString(), horaFim.getText().toString(), tipo.getText().toString(), endereco.getText().toString(), imagem.getText().toString());
        myRef.child("evento").push().setValue(evento);
        MainActivity.eventos.add(evento);
    }
}