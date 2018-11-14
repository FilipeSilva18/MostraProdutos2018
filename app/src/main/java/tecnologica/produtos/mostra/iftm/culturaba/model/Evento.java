package tecnologica.produtos.mostra.iftm.culturaba.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Evento implements Parcelable {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private Integer idEvento;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String horaFim;
    private String tipo;
    private String imagem;
    private String endereco;

    @NonNull
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(@NonNull Integer idEvento) {
        this.idEvento = idEvento;
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }

    public Evento(String nome, String descricao, String dataInicio, String dataFim, String horaInicio, String horaFim, String tipo, String endereco, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tipo = tipo;
        this.endereco = endereco;
        this.imagem = imagem;
    }

    private Evento(Parcel from) {
        nome = from.readString();
        descricao = from.readString();
        dataInicio = from.readString();
        dataFim = from.readString();
        horaInicio = from.readString();
        horaFim = from.readString();
        tipo = from.readString();
        endereco = from.readString();
        imagem = from.readString();
    }

    public static final Parcelable.Creator
            CREATOR = new Parcelable.Creator() {

        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(dataInicio);
        dest.writeString(dataFim);
        dest.writeString(horaInicio);
        dest.writeString(horaFim);
        dest.writeString(tipo);
        dest.writeString(imagem);
        dest.writeString(endereco);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFim='" + horaFim + '\'' +
                ", tipo='" + tipo + '\'' +
                ", imagem='" + imagem + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
