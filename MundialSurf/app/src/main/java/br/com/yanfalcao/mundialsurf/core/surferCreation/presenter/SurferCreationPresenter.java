package br.com.yanfalcao.mundialsurf.core.surferCreation.presenter;

import br.com.yanfalcao.mundialsurf.core.surferCreation.SurferCreationContract;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class SurferCreationPresenter implements SurferCreationContract.Presenter {
    private RoomData roomData;
    private SurferCreationContract.View view;

    public SurferCreationPresenter(RoomData roomData, SurferCreationContract.View view) {
        this.roomData = roomData;
        this.view = view;
    }

    @Override
    public boolean validateFields(String name, String country) {
        if(name == null || name.isEmpty() || country == null || country.isEmpty()) {
            view.setErrorEmptyFields();
            return false;
        }
        return true;
    }

    @Override
    public boolean save(String name, String country) {
        Surfer surfer = new Surfer();

        surfer.setName(name);
        surfer.setCountry(country);

        return roomData.getSurferDao().insert(surfer) != -1;
    }
}
