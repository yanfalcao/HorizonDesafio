package br.com.yanfalcao.mundialsurf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.yanfalcao.mundialsurf.core.hitCreation.HitCreationContract;
import br.com.yanfalcao.mundialsurf.core.hitCreation.presenter.HitCreationPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HitCreationPresenterTest {

    @Mock
    private HitCreationContract.view view;
    private HitCreationPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isValidReturnFalseValidateFields(){
        // Surfer list empty
        RoomData roomData = Mockito.mock(RoomData.class);
        SurferDao dao = Mockito.mock(SurferDao.class);

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<Surfer>());

        HitCreationPresenter presenter = new HitCreationPresenter(view, roomData);

        assertFalse(presenter.validateFields(1, 2));

        // Surfer list null
        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new HitCreationPresenter(view, roomData);

        assertFalse(presenter.validateFields(1, 2));

        // Surfers equals
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Surfer(), new Surfer())));

        presenter = new HitCreationPresenter(view, roomData);

        assertFalse(presenter.validateFields(1, 1));

        // Surfers dont selected
        assertFalse(presenter.validateFields(0, 0));
    }

    @Test
    public void isValidReturnTrueValidateFields(){
        RoomData roomData = Mockito.mock(RoomData.class);
        SurferDao dao = Mockito.mock(SurferDao.class);

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Surfer(), new Surfer())));

        presenter = new HitCreationPresenter(view, roomData);

        assertTrue(presenter.validateFields(1, 2));
    }
}
