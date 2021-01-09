package br.com.yanfalcao.mundialsurf.core.hit;

import java.util.List;

import br.com.yanfalcao.mundialsurf.constants.SurferEnum;
import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.score.Score;

public interface HitContract {

    interface View {

    }

    interface Presenter {
        int getHitsCount();
        String surferOneName(int position);
        String surferTwoName(int position);
        Hit getHitAt(int position);
        boolean isEmptyHits();
        List<Hit> filter(CharSequence text);
        void setHitsFilter(List<Hit> hits);
        List<Score> getScoreSurferOne(int position);
        List<Score> getScoreSurferTwo(int position);
        SurferEnum getWinner(int position);
        void restartHitList();
    }
}
