package br.com.yanfalcao.mundialsurf.core.scoreCreation;

import java.util.List;

public interface ScoreCreationContract {

    interface View {
        void setErrorPartialScoreEmpty();
        void setErrorPartialScoreType();
    }

    interface Presenter {
        List<String> getSurfersList();
        boolean validateFields(String partial1, String partial2, String partial3);
        boolean saveWave(int idHit, int surferPosition);
        boolean saveScore(String partial1, String partial2, String partial3);
    }
}
