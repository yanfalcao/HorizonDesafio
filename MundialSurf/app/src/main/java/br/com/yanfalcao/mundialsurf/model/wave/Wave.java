package br.com.yanfalcao.mundialsurf.model.wave;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

@Entity(tableName = "onda")
public class Wave {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "bateria_id")
    @ForeignKey(entity = Hit.class,
            parentColumns = "_id",
            childColumns = "bateria_id",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    private int idHit;

    @ColumnInfo(name = "surfista_id")
    @ForeignKey(entity = Surfer.class,
            parentColumns = "_id",
            childColumns = "surfista_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
    private int idSurfer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHit() {
        return idHit;
    }

    public void setIdHit(int idHit) {
        this.idHit = idHit;
    }

    public int getIdSurfer() {
        return idSurfer;
    }

    public void setIdSurfer(int idSurfer) {
        this.idSurfer = idSurfer;
    }
}
