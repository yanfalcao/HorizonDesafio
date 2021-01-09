package br.com.yanfalcao.mundialsurf.core.hitCreation.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.core.hitCreation.HitCreationContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class HitCreationPresenter implements HitCreationContract.presenter {
    RoomData roomData;
    HitCreationContract.view view;
    private List<Surfer> surfers;

    public HitCreationPresenter(HitCreationContract.view view, RoomData roomData){
        this.view = view;
        this.roomData = roomData;
        this.surfers = roomData.getSurferDao().getAll();
    }

    @Override
    public List<String> getSurfersName() {
        ArrayList<String> surfersName = new ArrayList<String>();

        surfersName.add("Select a new surfer...");

        if(surfers != null && !surfers.isEmpty()) {
            for (Surfer s : surfers) {
                surfersName.add(s.getName());
            }
        }

        return surfersName;
    }

    @Override
    public boolean validateFields(int positionSurfer1, int positionSurfer2) {
        if(surfers == null || surfers.isEmpty()){
            view.setErrorWithoutSurfer();
            return false;
        }else {
            if(positionSurfer1 == 0 || positionSurfer2 == 0){
                view.setErrorSelectSurfer();
                return false;
            }
            if(positionSurfer1 == positionSurfer2){
                view.setErrorSurfersSame();
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean save(int positionSurfer1, int positionSurfer2) {
        Hit hit = new Hit();

        hit.setIdSurferOne(surfers.get(--positionSurfer1).getId());
        hit.setIdSurferTwo(surfers.get(--positionSurfer2).getId());

        return roomData.getHitDao().insert(hit) != -1;
    }
}
