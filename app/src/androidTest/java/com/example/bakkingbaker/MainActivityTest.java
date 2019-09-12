package com.example.bakkingbaker;




import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {

        //Recipe List Fragment
        Thread.sleep(2000);
        onView(withText("sounds Tasty! Choose a Recipe ♥")).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView_id)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.recyclerView_id)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        //Ingredient List Fragment
        Thread.sleep(1000);
        onView(withId(R.id.ingredient_rv)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.ingredient_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        //fab
        onView(withId(R.id.fab_id)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_id)).perform(click());

        //step List Fragment
        Thread.sleep(500);
        onView(withId(R.id.step_rv)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.step_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));


        //player Fragment
        onView(withId(R.id.shortDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.Description)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_next)).check(matches(isDisplayed()))
        .perform(click());
        Thread.sleep(500);

        onView(withId(R.id.shortDescription)).check(matches(withText("Add sugars to wet mixture.")));
        onView(withId(R.id.navigation_position)).check(matches(isDisplayed()));


        onView(withId(R.id.navigation_previous)).check(matches(isDisplayed()))
                .perform(click());


        Thread.sleep(500);

        onView(withId(R.id.shortDescription)).check(matches(withText(startsWith("Melt butter"))));









        //onView(withText("Brownies")).check(matches(isDisplayed()));




    }
        }


