package br.com.yanfalcao.mundialsurf.core.surferCreation;

public interface SurferCreationContract {

    interface View{
        void setErrorEmptyFields();
    }

    interface Presenter{
        boolean validateFields(String name, String country);
        boolean save(String name, String country);
    }
}
