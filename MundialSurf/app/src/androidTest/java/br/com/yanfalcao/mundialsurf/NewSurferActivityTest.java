package br.com.yanfalcao.mundialsurf;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.yanfalcao.mundialsurf.core.surferCreation.view.NewSurferActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewSurferActivityTest {

    @Rule
    public ActivityTestRule<NewSurferActivity> activityTestRule = new ActivityTestRule<>(NewSurferActivity.class);

    @Test
    public void testSuccessSaveSurfer(){
        onView(withId(R.id.name)).perform(typeText("Joao Hugo"), closeSoftKeyboard());
        onView(withId(R.id.country)).perform(typeText("Brazil"), closeSoftKeyboard());
        onView(withId(R.id.save)).perform(click());
    }

    @Test
    public void testFailSaveSurfer(){
        onView(withId(R.id.save)).perform(click());
        onView(withText(R.string.emptyFields)).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelClick(){
        onView(withId(R.id.cancel)).perform(click());
    }
}
