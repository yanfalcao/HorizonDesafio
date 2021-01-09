package br.com.yanfalcao.mundialsurf.model.hit;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

@Entity(tableName = "bateria", foreignKeys = {
        @ForeignKey(entity = Surfer.class, parentColumns = "_id", childColumns = "surfista1", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Surfer.class, parentColumns = "_id", childColumns = "surfista2", onDelete = ForeignKey.CASCADE)}
)
public class Hit {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "surfista1")
    private int idSurferOne;

    @NonNull
    @ColumnInfo(name = "surfista2")
    private int idSurferTwo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSurferOne() {
        return idSurferOne;
    }

    public void setIdSurferOne(int idSurferOne) {
        this.idSurferOne = idSurferOne;
    }

    public int getIdSurferTwo() {
        return idSurferTwo;
    }

    public void setIdSurferTwo(int idSurferTwo) {
        this.idSurferTwo = idSurferTwo;
    }
}
