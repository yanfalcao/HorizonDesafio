package br.com.yanfalcao.mundialsurf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.yanfalcao.mundialsurf.core.surferEdition.SurferEditionContract;
import br.com.yanfalcao.mundialsurf.core.surferEdition.presenter.SurferEditionPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SurferEditionPresenterTest {
    @Mock
    private SurferEditionContract.View view;
    @Mock
    private RoomData roomData;
    @Mock
    private SurferDao dao;
    private SurferEditionPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isValidReturnFalseValidateFields(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getById(0)).thenReturn(new Surfer());

        presenter = new SurferEditionPresenter(roomData, view, 0);

        assertFalse(presenter.validateFields(null, null));
        assertFalse(presenter.validateFields("", ""));
        assertFalse(presenter.validateFields(null, "Brazil"));
        assertFalse(presenter.validateFields("Gabriel", null));
    }

    @Test
    public void isValidReturnTrueValidateFields(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getById(0)).thenReturn(new Surfer());

        presenter = new SurferEditionPresenter(roomData, view, 0);

        assertTrue(presenter.validateFields("Gabriel Medina", "Brazil"));
    }
}
