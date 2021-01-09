package br.com.yanfalcao.mundialsurf.core.surferEdition;

public interface SurferEditionContract {

    interface View {
        void setName(String name);
        void setCountry(String country);
        void setErrorFillField();
    }

    interface Presenter {
        void fillFields();
        boolean validateFields(String name, String country);
        boolean update(String name, String country);
        boolean delete();
    }
}
