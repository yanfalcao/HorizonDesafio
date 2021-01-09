package br.com.yanfalcao.mundialsurf.core.surferEdition.presenter;

import br.com.yanfalcao.mundialsurf.core.surferEdition.SurferEditionContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class SurferEditionPresenter implements SurferEditionContract.Presenter {
    private RoomData roomData;
    private SurferEditionContract.View view;
    private Surfer surfer;

    public SurferEditionPresenter(RoomData roomData, SurferEditionContract.View view, int idSurfer) {
        this.roomData = roomData;
        this.view = view;
        this.surfer = roomData.getSurferDao().getById(idSurfer);
    }

    @Override
    public void fillFields() {
        view.setName(surfer.getName());
        view.setCountry(surfer.getCountry());
    }

    @Override
    public boolean validateFields(String name, String country) {
        if(name == null || name.isEmpty()){
            view.setErrorFillField();
            return false;
        }
        if(country == null || country.isEmpty()){
            view.setErrorFillField();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(String name, String country) {
        surfer.setName(name);
        surfer.setCountry(country);

        return roomData.getSurferDao().update(surfer) > 0;
    }

    @Override
    public boolean delete() {
        return roomData.getSurferDao().delete(surfer.getId()) > 0;
    }
}
