package br.com.yanfalcao.mundialsurf.core.surfer.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.core.surfer.SurferContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class SurferPresenter implements SurferContract.Presenter {
    private RoomData roomData;
    private SurferContract.View view;
    private List<Surfer> surferList;
    private List<Surfer> surfersFilter;

    public SurferPresenter(RoomData roomData, SurferContract.View view) {
        this.roomData = roomData;
        this.view = view;
        this.surferList = roomData.getSurferDao().getAll();
        this.surferList = surferList == null ? new ArrayList<>() : surferList;
        this.surfersFilter = surferList.isEmpty() ? new ArrayList<>() : new ArrayList<>(surferList);

    }

    @Override
    public int surfersCount() {
        return !surfersFilter.isEmpty() ? surfersFilter.size() : 0;
    }

    @Override
    public String getSurferNameAt(int position) {
        return surfersFilter.isEmpty() ? "Empty" : surfersFilter.get(position).getName();
    }

    @Override
    public String getSurferCountryAt(int position) {
        return surfersFilter.isEmpty() ? "Empty" : surfersFilter.get(position).getCountry();
    }

    @Override
    public int getSurferIdAt(int position) {
        return surfersFilter.get(position).getId();
    }

    @Override
    public boolean isEmpty() {
        return surfersFilter == null || surfersFilter.isEmpty();
    }

    @Override
    public List<Surfer> filter(CharSequence text) {
        List<Surfer> filteredList = new ArrayList<>();

        if(text == null || text.length() == 0)
            filteredList.addAll(surferList);
        else{
            String filter = text.toString().toLowerCase().trim();

            if(surferList != null && !surferList.isEmpty()) {
                for (Surfer surfer : surferList) {
                    if (surfer.getCountry().toLowerCase().contains(filter) || surfer.getName().toLowerCase().contains(filter)) {
                        filteredList.add(surfer);
                    }
                }
            }
        }

        return filteredList;
    }

    @Override
    public void setFilter(List<Surfer> surfers) {
        surfersFilter.clear();
        surfersFilter.addAll(surfers);
    }

    @Override
    public void restartSurferList() {
        this.surferList = roomData.getSurferDao().getAll();
        this.surferList = surferList == null ? new ArrayList<>() : surferList;
        this.surfersFilter = surferList.isEmpty() ? new ArrayList<>() : new ArrayList<>(surferList);
    }
}
