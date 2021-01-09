package br.com.yanfalcao.mundialsurf.model.score;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ScoreDao {

    @Insert
    long insert(Score score);
}
