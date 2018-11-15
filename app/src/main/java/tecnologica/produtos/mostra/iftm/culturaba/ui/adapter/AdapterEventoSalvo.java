package tecnologica.produtos.mostra.iftm.culturaba.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.R;
import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

public class AdapterEventoSalvo extends BaseAdapter {

    private final List<Evento> eventos;
    private final Activity act;

    public AdapterEventoSalvo(List<Evento> eventos, Activity act) {
        this.eventos = eventos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return this.eventos.size();
    }

    @Override
    public Evento getItem(int position) {
        return this.eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.item_list_evento, parent, false);

        ImageView imagem = view.findViewById(R.id.img_evento);
        TextView tituloEvento = view.findViewById(R.id.tv_titulo_evento);
        TextView localEvento = view.findViewById(R.id.tv_local);
        TextView horarioEvento = view.findViewById(R.id.tv_horario);

        Picasso.get()
                .load(eventos.get(position).getEndereco())
                .resize(300, 300)
                .centerCrop()
                .into(imagem);

        tituloEvento.setText(eventos.get(position).getNome());
        localEvento.setText(eventos.get(position).getImagem()   );
        horarioEvento.setText(eventos.get(position).getHoraInicio());



        return view;
    }
}
