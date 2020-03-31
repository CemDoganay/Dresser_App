package com.example.dresser_app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void activityLaunch() { /*ViewInteraction mainTextView = onView(withId(R.id.textView));
        mainTextView.check(matches(withText("Welcome to Tucked!")));

        ViewInteraction ideasButton = onView(withId(R.id.ideas_button));
        ideasButton.check(matches(withText("Give Me Ideas")));

        ViewInteraction addButton = onView(withId(R.id.Add_button));
        addButton.check(matches(withText("Open Camera")));

        ViewInteraction createButton = onView(withId(R.id.Create_button));
        createButton.check(matches(withText("Create a Combination")));

        //onView(withId(R.id.ideas_button)).perform(click());
        */
    }
}
