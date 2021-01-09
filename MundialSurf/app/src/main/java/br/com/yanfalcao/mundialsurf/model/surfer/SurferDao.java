package br.com.yanfalcao.mundialsurf.model.surfer;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SurferDao {

    @Query("Select * From surfista;")
    public List<Surfer> getAll();

    @Query("Select * From surfista Where _id = :id;")
    public Surfer getById(int id);

    @Insert
    public int insert(Surfer surfer);

    @Update
    public int update(Surfer surfer);

    @Query("Delete From surfista Where _id = :id")
    public int delete(int id);
}
