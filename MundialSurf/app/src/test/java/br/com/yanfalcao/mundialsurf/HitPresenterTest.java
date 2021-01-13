package br.com.yanfalcao.mundialsurf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.yanfalcao.mundialsurf.constants.SurferEnum;
import br.com.yanfalcao.mundialsurf.core.hit.HitContract;
import br.com.yanfalcao.mundialsurf.core.hit.presenter.HitPresenter;
import br.com.yanfalcao.mundialsurf.model.RoomData;
import br.com.yanfalcao.mundialsurf.model.hit.Hit;
import br.com.yanfalcao.mundialsurf.model.hit.HitDao;
import br.com.yanfalcao.mundialsurf.model.score.Score;
import br.com.yanfalcao.mundialsurf.model.score.ScoreDao;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;
import br.com.yanfalcao.mundialsurf.model.surfer.SurferDao;

public class HitPresenterTest {
    @Mock
    private HitContract.View view;
    @Mock
    private RoomData roomData;
    @Mock
    private HitDao dao;
    @Mock
    private SurferDao surferDao;
    private HitPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validGetHitsCount(){
        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(null);
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(0, presenter.getHitsCount());

        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(0, presenter.getHitsCount());

        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Hit(), new Hit())));
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(2, presenter.getHitsCount());
    }

    @Test
    public void validSurferOneName(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(roomData.getSurferDao()).thenReturn(surferDao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Hit(), new Hit())));
        Mockito.when(surferDao.getById(0)).thenReturn(one);
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(one.getName(), presenter.surferOneName(0));

        Mockito.when(roomData.getSurferDao().getById(0)).thenReturn(null);

        Assert.assertEquals("Empty", presenter.surferOneName(0));

        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.surferOneName(0));

        Mockito.when(dao.getAll()).thenReturn(null);
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.surferOneName(0));
    }

    @Test
    public void validSurferTwoName(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(roomData.getSurferDao()).thenReturn(surferDao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Hit(), new Hit())));
        Mockito.when(surferDao.getById(0)).thenReturn(one);
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(one.getName(), presenter.surferTwoName(0));

        Mockito.when(roomData.getSurferDao().getById(0)).thenReturn(null);

        Assert.assertEquals("Empty", presenter.surferTwoName(0));

        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.surferTwoName(0));

        Mockito.when(dao.getAll()).thenReturn(null);
        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals("Empty", presenter.surferTwoName(0));
    }

    @Test
    public void isValidReturnFalseIsEmptyHits(){
        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>());

        presenter = new HitPresenter(roomData, view);

        Assert.assertTrue(presenter.isEmptyHits());

        Mockito.when(dao.getAll()).thenReturn(null);

        presenter = new HitPresenter(roomData, view);

        Assert.assertTrue(presenter.isEmptyHits());
    }

    @Test
    public void isValidReturnTrueIsEmptyHits(){
        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(new Hit(), new Hit())));

        presenter = new HitPresenter(roomData, view);

        Assert.assertFalse(presenter.isEmptyHits());
    }

    @Test
    public void validFilter(){
        Surfer one = new Surfer();
        one.setName("Gabriel Medina");
        one.setCountry("Brazil");

        Surfer two = new Surfer();
        two.setName("Jack Jonh");
        two.setCountry("USA");

        Surfer three = new Surfer();
        three.setName("Joao Guedes");
        three.setCountry("Brazil");

        Surfer four = new Surfer();
        four.setName("John Bravo");
        four.setCountry("Australia");

        Hit hit1 = new Hit();
        hit1.setIdSurferOne(1);
        hit1.setIdSurferTwo(2);

        Hit hit2 = new Hit();
        hit2.setIdSurferOne(2);
        hit2.setIdSurferTwo(1);

        Hit hit3 = new Hit();
        hit3.setIdSurferOne(3);
        hit3.setIdSurferTwo(4);

        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(roomData.getSurferDao()).thenReturn(surferDao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(hit1, hit2, hit3)));
        Mockito.when(surferDao.getById(1)).thenReturn(one);
        Mockito.when(surferDao.getById(2)).thenReturn(two);
        Mockito.when(surferDao.getById(3)).thenReturn(three);
        Mockito.when(surferDao.getById(4)).thenReturn(four);

        presenter = new HitPresenter(roomData, view);

        int countFilter = presenter.filter("Gabriel").size();
        Assert.assertEquals(2, countFilter);

        countFilter = presenter.filter("").size();
        Assert.assertEquals(3, countFilter);

        countFilter = presenter.filter(null).size();
        Assert.assertEquals(3, countFilter);

        countFilter = presenter.filter("null").size();
        Assert.assertEquals(0, countFilter);
    }

    @Test
    public void validGetWinner(){
        ScoreDao scoreDao = Mockito.mock(ScoreDao.class);

        Hit hit1 = new Hit();
        hit1.setId(1);
        hit1.setIdSurferOne(1);
        hit1.setIdSurferTwo(2);

        Score score1 = new Score();
        score1.setPartialScoreOne(9.0);
        score1.setPartialScoreTwo(9.0);
        score1.setPartialScoreThree(9.0);
        score1.setAverage(9.0);

        Score score2 = new Score();
        score2.setPartialScoreOne(9.5);
        score2.setPartialScoreTwo(9.5);
        score2.setPartialScoreThree(9.5);
        score2.setAverage(9.5);

        Score score3 = new Score();
        score3.setPartialScoreOne(8.0);
        score3.setPartialScoreTwo(8.0);
        score3.setPartialScoreThree(8.0);
        score3.setAverage(8.0);

        Score score4 = new Score();
        score4.setPartialScoreOne(7.5);
        score4.setPartialScoreTwo(7.5);
        score4.setPartialScoreThree(7.5);
        score4.setAverage(7.5);

        Mockito.when(roomData.getHitDao()).thenReturn(dao);
        Mockito.when(roomData.getSurferDao()).thenReturn(surferDao);
        Mockito.when(dao.getAll()).thenReturn(new ArrayList<>(Arrays.asList(hit1)));
        Mockito.when(roomData.getScoreDao()).thenReturn(scoreDao);
        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(Arrays.asList(score1, score2));
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(Arrays.asList(score3, score4));

        presenter = new HitPresenter(roomData, view);

        Assert.assertEquals(SurferEnum.SURFER_ONE, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(null);
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(Arrays.asList(score3, score4));

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(Arrays.asList(score1, score2));
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(null);

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(null);
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(null);

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(new ArrayList<>());
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(Arrays.asList(score3, score4));

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(Arrays.asList(score1, score2));
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(new ArrayList<>());

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(new ArrayList<>());
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(new ArrayList<>());

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(Arrays.asList(score1));
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(Arrays.asList(score3, score4));

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));

        Mockito.when(scoreDao.getAllByHit(1,1)).thenReturn(Arrays.asList(score1,score2));
        Mockito.when(scoreDao.getAllByHit(1,2)).thenReturn(Arrays.asList(score3));

        Assert.assertEquals(SurferEnum.NOBODY, presenter.getWinner(0));
    }
}
