package br.com.yanfalcao.mundialsurf.model.score;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import br.com.yanfalcao.mundialsurf.model.wave.Wave;

@Entity(tableName = "nota",
        foreignKeys = @ForeignKey(entity = Wave.class, parentColumns = "_id", childColumns = "onda_id", onDelete = ForeignKey.CASCADE)
)
public class Score {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "onda_id")
    private int idWave;

    @NonNull
    @ColumnInfo(name = "nota1")
    private double partialScoreOne;

    @NonNull
    @ColumnInfo(name = "nota2")
    private double partialScoreTwo;

    @NonNull
    @ColumnInfo(name = "nota3")
    private double partialScoreThree;

    @NonNull
    @ColumnInfo(name = "media")
    private double average;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWave() {
        return idWave;
    }

    public void setIdWave(int idWave) {
        this.idWave = idWave;
    }

    public double getPartialScoreOne() {
        return partialScoreOne;
    }

    public void setPartialScoreOne(double partialScoreOne) {
        this.partialScoreOne = partialScoreOne;
    }

    public double getPartialScoreTwo() {
        return partialScoreTwo;
    }

    public void setPartialScoreTwo(double partialScoreTwo) {
        this.partialScoreTwo = partialScoreTwo;
    }

    public double getPartialScoreThree() {
        return partialScoreThree;
    }

    public void setPartialScoreThree(double partialScoreThree) {
        this.partialScoreThree = partialScoreThree;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
