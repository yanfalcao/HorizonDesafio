package br.com.yanfalcao.mundialsurf.model;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.hit.HitDao;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.score.ScoreDao;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;
import br.com.yanfalcao.mundialsurf.model.wave.Wave;
import br.com.yanfalcao.mundialsurf.model.wave.WaveDao;

@Database(version = 1, entities = {Hit.class, Score.class, Surfer.class, Wave.class})
public abstract class RoomData extends RoomDatabase {
    private static final String DATA_BASE = "MundialSurfDataBase";
    private static RoomData database;

    public static RoomData getInstance(Activity activity){
        if(database == null) {
            database = Room.databaseBuilder(activity, RoomData.class, DATA_BASE)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public abstract HitDao getHitDao();
    public abstract SurferDao getSurferDao();
    public abstract ScoreDao getScoreDao();
    public abstract WaveDao getWaveDao();
}
