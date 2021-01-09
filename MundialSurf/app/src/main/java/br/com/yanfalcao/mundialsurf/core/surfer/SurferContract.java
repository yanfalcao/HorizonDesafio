package br.com.yanfalcao.mundialsurf.core.surfer;

import java.util.List;

import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public interface SurferContract {
    interface View {

    }

    interface Presenter {
        int surfersCount();
        String getSurferNameAt(int position);
        String getSurferCountryAt(int position);
        int getSurferIdAt(int position);
        boolean isEmpty();
        List<Surfer> filter(CharSequence text);
        void setFilter(List<Surfer> surfers);
        void restartSurferList();
    }
}
