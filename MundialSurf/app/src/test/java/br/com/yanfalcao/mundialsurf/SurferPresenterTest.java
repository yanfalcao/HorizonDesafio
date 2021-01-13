package br.com.yanfalcao.mundialsurf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.yanfalcao.mundialsurf.core.surfer.SurferContract;
import br.com.yanfalcao.mundialsurf.core.surfer.presenter.SurferPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;

public class SurferPresenterTest {
    @Mock
    private SurferContract.View view;
    @Mock
    private RoomData roomData;
    @Mock
    private SurferDao dao;
    private SurferPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validFailSurfersCount(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals(0, presenter.surfersCount());

        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals(0, presenter.surfersCount());
    }

    @Test
    public void validSuccessSurfersCount(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Surfer(), new Surfer())));

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals(2, presenter.surfersCount());
    }

    @Test
    public void validSuccessGetSurferNameAt(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Surfer two = new Surfer();
        two.setName("Jack Jonh");
        two.setCountry("USA");

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(one, two)));

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals(two.getName(), presenter.getSurferNameAt(1));
    }

    @Test
    public void validFailGetSurferNameAt(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.getSurferNameAt(1));

        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.getSurferNameAt(1));
    }

    @Test
    public void validSuccessGetCountryNameAt(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Surfer two = new Surfer();
        two.setName("Jack Jonh");
        two.setCountry("USA");

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(one, two)));

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals(one.getCountry(), presenter.getSurferCountryAt(0));
    }

    @Test
    public void validFailGetCountryNameAt(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.getSurferCountryAt(1));

        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new SurferPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.getSurferCountryAt(1));
    }

    @Test
    public void isValidReturnFalseIsEmpty(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());

        presenter = new SurferPresenter(roomData, view);

        Assert.assertTrue(presenter.isEmpty());

        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new SurferPresenter(roomData, view);

        Assert.assertTrue(presenter.isEmpty());
    }

    @Test
    public void isValidReturnTrueIsEmpty(){
        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Surfer(), new Surfer())));

        presenter = new SurferPresenter(roomData, view);

        Assert.assertFalse(presenter.isEmpty());
    }

    @Test
    public void validFilter(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Surfer two = new Surfer();
        two.setName("Jack Jonh");
        two.setCountry("USA");

        Mockito.when(roomData.getSurferDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(one, two)));

        presenter = new SurferPresenter(roomData, view);

        int countFilter = presenter.filter("Brazil").size();
        Assert.assertEquals(1, countFilter);

        countFilter = presenter.filter("").size();
        Assert.assertEquals(2, countFilter);

        countFilter = presenter.filter(null).size();
        Assert.assertEquals(2, countFilter);

        countFilter = presenter.filter("null").size();
        Assert.assertEquals(0, countFilter);
    }
}
