package br.com.yanfalcao.mundialsurf.core.hitCreation;

import java.util.List;

public interface HitCreationContract {

    interface view {
        void setErrorWithoutSurfer();
        void setErrorSurfersSame();
        void setErrorSelectSurfer();
    }

    interface presenter {
        List<String> getSurfersName();
        boolean validateFields(int positionSurfer1, int positionSurfer2);
        boolean save(int positionSurfer1, int positionSurfer2);
    }
}
