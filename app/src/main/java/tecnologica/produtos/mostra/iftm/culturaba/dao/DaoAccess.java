package tecnologica.produtos.mostra.iftm.culturaba.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

@Dao
public interface DaoAccess {

    @Insert
    void insertEvento(Evento evento);

    @Insert
    void insertEventos(ArrayList<Evento> eventoList);

    @Query("SELECT * FROM Evento WHERE imagem =:imagem")
    Evento findEventoById(String imagem);

    @Query("SELECT * FROM Evento")
    List<Evento> findAll();

    @Query("SELECT * FROM Evento WHERE tipo LIKE :tipoEvento")
    List<Evento> findEventoByTipo(String tipoEvento);

    @Query("DELETE FROM Evento WHERE endereco = :imagem")
    void deleteByID(String imagem);

    @Update
    void updateEvento(Evento evento);

    @Delete
    void deleteEvento(Evento evento);
}
