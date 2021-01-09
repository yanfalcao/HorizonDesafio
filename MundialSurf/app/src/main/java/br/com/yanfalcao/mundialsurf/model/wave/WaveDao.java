package br.com.yanfalcao.mundialsurf.model.wave;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WaveDao {

    @Insert
    long insert(Wave wave);

    @Query("SELECT _id FROM onda ORDER BY _id desc")
    int getLastWaveId();
}
