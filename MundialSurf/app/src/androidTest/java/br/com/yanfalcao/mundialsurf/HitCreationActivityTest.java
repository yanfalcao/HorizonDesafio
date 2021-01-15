package br.com.yanfalcao.mundialsurf;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.com.yanfalcao.mundialsurf.core.hitCreation.view.HitCreationActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class HitCreationActivityTest {
    @Rule
    public ActivityTestRule<HitCreationActivity> activityTestRule = new ActivityTestRule<>(HitCreationActivity.class);

    @Test
    public void testSuccessSaveHit(){
        onView(withId(R.id.surferOne)).perform(click());
        onView(withText("Yan Falcão")).perform(click());

        onView(withId(R.id.surferTwo)).perform(click());
        onView(withText("João Hugo")).perform(click());

        onView(withId(R.id.save_hit)).perform(click());
    }

    @Test
    public void testSelectSameSurfer(){
        onView(withId(R.id.surferOne)).perform(click());
        onView(withText("Yan Falcão")).perform(click());

        onView(withId(R.id.surferTwo)).perform(click());
        onView(withText("Yan Falcão")).perform(click());

        onView(withId(R.id.save_hit)).perform(click());
        onView(withText(R.string.sameSurfers)).check(matches(isDisplayed()));
    }

    @Test
    public void testDontSelectSurfer(){
        onView(withId(R.id.save_hit)).perform(click());
        onView(withText(R.string.withoutSurfer)).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelClick(){
        onView(withId(R.id.cancel)).perform(click());
    }
}
