package br.com.yanfalcao.mundialsurf.core.hit.presenter;


import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.constants.SurferEnum;
import br.com.yanfalcao.mundialsurf.core.hit.HitContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class HitPresenter implements HitContract.Presenter {
    private RoomData roomData;
    private HitContract.View view;
    private List<Hit> hitsFilter;
    private List<Hit> fullHits;

    public HitPresenter(RoomData roomData, HitContract.View view) {
        this.roomData = roomData;
        this.view = view;
        this.hitsFilter = roomData.getHitDao().getAll();
        this.hitsFilter = hitsFilter == null ? new ArrayList<>() : hitsFilter;
        this.fullHits = hitsFilter.isEmpty() ? new ArrayList<>() : new ArrayList<>(hitsFilter);
    }

    @Override
    public int getHitsCount() {
        return hitsFilter != null && !hitsFilter.isEmpty() ? hitsFilter.size() : 0;
    }

    @Override
    public String surferOneName(int position) {
        if(hitsFilter == null || hitsFilter.isEmpty()){
            return "Empty";
        }else {
            Surfer surfer = roomData.getSurferDao().getById(hitsFilter.get(position).getIdSurferOne());
            return surfer == null ? "Empty" : surfer.getName();
        }
    }

    @Override
    public String surferTwoName(int position) {
        if(hitsFilter == null || hitsFilter.isEmpty()){
            return "Empty";
        }else {
            Surfer surfer = roomData.getSurferDao().getById(hitsFilter.get(position).getIdSurferTwo());
            return surfer == null ? "Empty" : surfer.getName();
        }
    }

    @Override
    public Hit getHitAt(int position) {
        return hitsFilter.get(position);
    }

    @Override
    public boolean isEmptyHits() {
        return hitsFilter == null || hitsFilter.isEmpty();
    }

    @Override
    public List<Hit> filter(CharSequence text) {
        List<Hit> filteredList = new ArrayList<>();

        if(text == null || text.length() == 0)
            filteredList.addAll(fullHits);
        else{
            String filter = text.toString().toLowerCase().trim();

            if(fullHits != null && !fullHits.isEmpty()) {
                for (Hit hit : fullHits) {
                    Surfer surfer1 = roomData.getSurferDao().getById(hit.getIdSurferOne());
                    Surfer surfer2 = roomData.getSurferDao().getById(hit.getIdSurferTwo());

                    if (surfer1 != null && surfer1.getName().toLowerCase().contains(filter)) {
                        filteredList.add(hit);
                    }else if (surfer2 != null && surfer2.getName().toLowerCase().contains(filter)) {
                        filteredList.add(hit);
                    }
                }
            }
        }

        return filteredList;
    }

    @Override
    public void setHitsFilter(List<Hit> hits) {
        hitsFilter.clear();
        hitsFilter.addAll(hits);
    }

    @Override
    public List<Score> getScoreSurferOne(int position) {
        Hit hit = hitsFilter.get(position);

        return roomData.getScoreDao().getAllByHit(hit.getId(), hit.getIdSurferOne());
    }

    @Override
    public List<Score> getScoreSurferTwo(int position) {
        Hit hit = hitsFilter.get(position);

        return roomData.getScoreDao().getAllByHit(hit.getId(), hit.getIdSurferTwo());
    }

    @Override
    public SurferEnum getWinner(int position) {
        List<Score> scoreListS1 = getScoreSurferOne(position);
        List<Score> scoreListS2 = getScoreSurferTwo(position);
        Double averageS1 = 0.0, averageS2 = 0.0;

        if(scoreListS1 != null && !scoreListS1.isEmpty()){
            averageS1 += scoreListS1.get(0).getAverage();

            if(scoreListS1.size() > 1){
                averageS1 += scoreListS1.get(1).getAverage();
            }else {
                return SurferEnum.NOBODY;
            }
        }else {
            return SurferEnum.NOBODY;
        }

        if(scoreListS2 != null && !scoreListS2.isEmpty()){
            averageS2 += scoreListS2.get(0).getAverage();

            if(scoreListS2.size() > 1){
                averageS2 += scoreListS1.get(1).getAverage();
            }else {
                return SurferEnum.NOBODY;
            }
        }else {
            return SurferEnum.NOBODY;
        }

        if(averageS1 > averageS2){
            return SurferEnum.SURFER_ONE;
        }else if(averageS2 > averageS1){
            return SurferEnum.SURFER_TWO;
        }
        return SurferEnum.NOBODY;
    }

    @Override
    public void restartHitList() {
        this.hitsFilter = roomData.getHitDao().getAll();
        this.hitsFilter = hitsFilter == null ? new ArrayList<>() : hitsFilter;
        this.fullHits = hitsFilter.isEmpty() ? new ArrayList<>() : new ArrayList<>(hitsFilter);
    }
}
