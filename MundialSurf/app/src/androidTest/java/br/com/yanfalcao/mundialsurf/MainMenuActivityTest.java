package br.com.yanfalcao.mundialsurf;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.yanfalcao.mundialsurf.core.main.view.MainMenuActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainMenuActivityTest {

    @Rule
    public ActivityTestRule<MainMenuActivity> activityTestRule = new ActivityTestRule<>(MainMenuActivity.class);

    @Test
    public void testClickSurferList(){
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(click());
    }

    @Test
    public void testClickHitList(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(allOf(withId(R.id.viewPager), isDisplayed())).perform(click());
    }
}
