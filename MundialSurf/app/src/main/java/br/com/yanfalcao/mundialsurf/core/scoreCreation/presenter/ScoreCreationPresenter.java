package br.com.yanfalcao.mundialsurf.core.scoreCreation.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import br.com.yanfalcao.mundialsurf.core.scoreCreation.ScoreCreationContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.wave.Wave;

public class ScoreCreationPresenter implements ScoreCreationContract.Presenter {
    private RoomData roomData;
    private ScoreCreationContract.View view;
    private Surfer surfer1, surfer2;

    public ScoreCreationPresenter(RoomData roomData, ScoreCreationContract.View view, int idSurfer1, int idSurfer2) {
        this.roomData = roomData;
        this.view = view;
        this.surfer1 = roomData.getSurferDao().getById(idSurfer1);
        this.surfer2 = roomData.getSurferDao().getById(idSurfer2);
    }

    @Override
    public List<String> getSurfersList() {
        ArrayList<String> names = new ArrayList<>();

        names.add(surfer1.getName());
        names.add(surfer2.getName());

        return names;
    }

    @Override
    public boolean validateFields(String partial1, String partial2, String partial3) {
        if(partial1 == null || partial2 == null || partial3 == null){
            view.setErrorPartialScoreEmpty();
            return false;
        }else if(partial1.isEmpty() || partial2.isEmpty() || partial3.isEmpty()){
            view.setErrorPartialScoreEmpty();
            return false;
        }else if(!isNumeric(partial1) || !isNumeric(partial2) || !isNumeric(partial3)){
            view.setErrorPartialScoreType();
            return false;
        }

        return true;
    }

    @Override
    public boolean saveWave(int idHit, int surferPosition) {
        Wave wave = new Wave();

        wave.setIdHit(idHit);
        wave.setIdSurfer(surferPosition == 0 ? surfer1.getId() : surfer2.getId());

        return roomData.getWaveDao().insert(wave) != -1;
    }

    @Override
    public boolean saveScore(String partial1, String partial2, String partial3) {
        Score score = new Score();

        score.setIdWave(roomData.getWaveDao().getLastWaveId());
        score.setPartialScoreOne(Double.parseDouble(partial1));
        score.setPartialScoreTwo(Double.parseDouble(partial2));
        score.setPartialScoreThree(Double.parseDouble(partial3));

        double average = (score.getPartialScoreOne() + score.getPartialScoreTwo() + score.getPartialScoreThree())/3;
        score.setAverage(average);

        return roomData.getScoreDao().insert(score) != -1;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
