package tecnologica.produtos.mostra.iftm.culturaba.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import tecnologica.produtos.mostra.iftm.culturaba.model.Evento;

@Database(entities = {Evento.class}, version = 1, exportSchema = false)
public abstract class EventoDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    private static EventoDatabase eventoDatabase;

    public static EventoDatabase getInstance(Context context) {
        if (eventoDatabase == null) {
            eventoDatabase = Room.databaseBuilder(context.getApplicationContext(), EventoDatabase.class, "Evento")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return eventoDatabase;
    }

}

