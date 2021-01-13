package br.com.yanfalcao.mundialsurf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.yanfalcao.mundialsurf.core.surferCreation.SurferCreationContract;
import br.com.yanfalcao.mundialsurf.core.surferCreation.presenter.SurferCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SurferCreationPresenterTest {

    @Mock
    private SurferCreationContract.View view;
    @Mock
    private RoomData roomData;
    private SurferCreationPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isValidReturnFalseValidateFields(){
        presenter = new SurferCreationPresenter(roomData, view);

        assertFalse(presenter.validateFields(null, null));
        assertFalse(presenter.validateFields("", ""));
        assertFalse(presenter.validateFields(null, "Brazil"));
        assertFalse(presenter.validateFields("Gabriel", null));
    }

    @Test
    public void isValidReturnTrueValidateFields(){
        presenter = new SurferCreationPresenter(roomData, view);

        assertTrue(presenter.validateFields("Gabriel Medina", "Brazil"));
    }
}
