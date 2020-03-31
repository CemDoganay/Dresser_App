package com.example.dresser_app;

import android.support.test.rule.ActivityTestRule;

import com.example.dresser_app.fragments.DressMeFragment;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class FragmentTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(DressMeFragment.class);

    @Test
    public void checkToolbarTucked() {
        onView(withId(R.id.toolbar_tucked)).check(matches(withText("Tucked")));
    }
}
