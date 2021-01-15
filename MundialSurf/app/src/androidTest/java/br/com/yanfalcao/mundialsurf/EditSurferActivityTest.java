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

import br.com.yanfalcao.mundialsurf.core.surfer.view.SurfersListActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
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
public class EditSurferActivityTest {
    @Rule
    public ActivityTestRule<SurfersListActivity> activityTestRule = new ActivityTestRule<>(SurfersListActivity.class);

    @Test
    public void testSuccessSaveSurfer(){
        ViewAction viewAction = RecyclerViewActions
                .actionOnItemAtPosition(0, clickChildViewWithId(R.id.setting));
        onView(withId(R.id.recycler_view_layout)).perform(viewAction);

        onView(withId(R.id.name))
                .perform(clearText())
                .perform(typeText("Victor Mendes"), closeSoftKeyboard());
        onView(withId(R.id.country))
                .perform(clearText())
                .perform(typeText("Brazil"), closeSoftKeyboard());
        onView(withId(R.id.save)).perform(click());
        onView(withText("Victor Mendes")).check(matches(isDisplayed()));
    }

    @Test
    public void testFailSaveSurfer(){
        ViewAction viewAction = RecyclerViewActions
                .actionOnItemAtPosition(0, clickChildViewWithId(R.id.setting));
        onView(withId(R.id.recycler_view_layout)).perform(viewAction);

        onView(withId(R.id.name)).perform(clearText());
        pauseTestFor(500);
        onView(withId(R.id.country)).perform(clearText());
        onView(withId(R.id.save)).perform(click());
        onView(withText("Please fill in the fields.")).check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteClick(){
        ViewAction viewAction = RecyclerViewActions
                .actionOnItemAtPosition(0, clickChildViewWithId(R.id.setting));
        onView(withId(R.id.recycler_view_layout)).perform(viewAction);

        onView(withId(R.id.delete)).perform(click());
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
