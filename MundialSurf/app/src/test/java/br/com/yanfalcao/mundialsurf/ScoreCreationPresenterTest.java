package br.com.yanfalcao.mundialsurf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.yanfalcao.mundialsurf.core.scoreCreation.ScoreCreationContract;
import br.com.yanfalcao.mundialsurf.core.scoreCreation.presenter.ScoreCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScoreCreationPresenterTest {

    @Mock
    private ScoreCreationContract.View view;
    private ScoreCreationPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isValidReturnFalseValidateFields(){
        RoomData roomData = Mockito.mock(RoomData.class);
        SurferDao dao = Mockito.mock(SurferDao.class);

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getById(0)).thenReturn(new Surfer());

        presenter = new ScoreCreationPresenter(roomData, view, 0, 0);

        assertFalse(presenter.validateFields(null, null, null));
        assertFalse(presenter.validateFields("null", "null", "null"));
        assertFalse(presenter.validateFields("8.f", "f.6", "f.4"));
        assertFalse(presenter.validateFields("8,5", "7,5", "5,6"));
        assertFalse(presenter.validateFields("", "", ""));
    }

    @Test
    public void isValidReturnTrueValidateFields(){
        RoomData roomData = Mockito.mock(RoomData.class);
        SurferDao dao = Mockito.mock(SurferDao.class);

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getById(0)).thenReturn(new Surfer());

        presenter = new ScoreCreationPresenter(roomData, view, 0, 0);

        assertTrue(presenter.validateFields("8.5", "8.6", "7.4"));
        assertTrue(presenter.validateFields("8", "7", "5.6"));
    }
}
