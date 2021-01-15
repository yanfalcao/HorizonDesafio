package br.com.yanfalcao.mundialsurf;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.com.yanfalcao.mundialsurf.core.hit.view.HitActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static br.com.yanfalcao.mundialsurf.ChildViewAction.clickChildViewWithId;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class ScoreCreationActivityTest {
    @Rule
    public ActivityTestRule<HitActivity> activityTestRule = new ActivityTestRule<>(HitActivity.class);

    @Test
    public void testFailSaveScore(){
        ViewAction viewAction = RecyclerViewActions
                .actionOnItemAtPosition(0, clickChildViewWithId(R.id.info_icon));
        onView(withId(R.id.recycler_view_layout)).perform(viewAction);

        pauseTestFor(1000);

        onView(withText(R.string.new_score)).perform(click());

        onView(withId(R.id.save)).perform(click());
        onView(withText(R.string.emptyFields)).check(matches(isDisplayed()));
    }

    @Test
    public void testSuccessSaveScore(){
        ViewAction viewAction = RecyclerViewActions
                .actionOnItemAtPosition(0, clickChildViewWithId(R.id.info_icon));
        onView(withId(R.id.recycler_view_layout)).perform(viewAction);

        pauseTestFor(1000);

        onView(withId(R.id.new_score_buttom)).perform(click());

        onView(withId(R.id.noteOneEditText))
                .perform(typeText("5.0"), closeSoftKeyboard());
        onView(withId(R.id.noteTwoEditText))
                .perform(typeText("7.4"), closeSoftKeyboard());
        onView(withId(R.id.noteThreeEditText))
                .perform(typeText("8.0"), closeSoftKeyboard());

        onView(withId(R.id.save)).perform(click());
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
