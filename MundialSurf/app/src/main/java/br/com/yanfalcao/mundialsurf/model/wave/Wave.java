package br.com.yanfalcao.mundialsurf.model.wave;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

@Entity(tableName = "onda", foreignKeys = {
        @ForeignKey(entity = Hit.class, parentColumns = "_id", childColumns = "bateria_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Surfer.class, parentColumns = "_id", childColumns = "surfista_id", onDelete = ForeignKey.CASCADE)
})
public class Wave {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "bateria_id")
    private int idHit;

    @NonNull
    @ColumnInfo(name = "surfista_id")
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
