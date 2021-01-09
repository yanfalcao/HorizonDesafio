package br.com.yanfalcao.mundialsurf.model.score;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import br.com.yanfalcao.mundialsurf.model.wave.Wave;

@Entity(tableName = "nota")
public class Score {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "onda_id")
    @ForeignKey(entity = Wave.class,
            parentColumns = "_id",
            childColumns = "onda_id",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    private int idWave;

    private double partialScoreOne;

    private double partialScoreTwo;

    private double partialScoreThree;

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
}
