package br.com.yanfalcao.mundialsurf.model.score;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {

    @Insert
    long insert(Score score);

    @Query("SELECT n.* " +
            "FROM bateria b, onda o, nota n " +
            "WHERE b._id = :id " +
            "AND b._id = o.bateria_id " +
            "AND o.surfista_id = :surferId " +
            "AND o._id = n.onda_id " +
            "ORDER BY n.media DESC")
    List<Score> getAllByHit(int id, int surferId);
}
