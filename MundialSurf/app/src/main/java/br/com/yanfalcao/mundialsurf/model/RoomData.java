package br.com.yanfalcao.mundialsurf.model;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.hit.HitDao;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.wave.Wave;

@Database(version = 2, entities = {Hit.class, Score.class, Surfer.class, Wave.class})
public abstract class RoomData extends RoomDatabase {
    private static final String DATA_BASE = "MundialSurf";

    public static RoomData getInstance(Activity activity){
        return Room.databaseBuilder(activity, RoomData.class, DATA_BASE)
                .allowMainThreadQueries()
                .build();
    }

    public abstract HitDao getHitDao();
}
