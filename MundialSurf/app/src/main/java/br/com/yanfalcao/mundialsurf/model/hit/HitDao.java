package br.com.yanfalcao.mundialsurf.model.hit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HitDao {

    @Query("Select * From bateria;")
    public List<Hit> getAll();

    @Insert
    public long insert(Hit hit);
}
