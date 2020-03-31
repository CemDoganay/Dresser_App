package com.example.dresser_app;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
/*
    @Test
    public void checkToolbarTucked() {
        ViewInteraction mainTextView = onView(withId(R.id.toolbar_tucked));
        mainTextView.check(matches(withText("Tucked")));
    }
*/
    @Test
    public void checkWardrobeAddText(){
        ViewInteraction addButton = onView(withId(R.id.button_add));
        addButton
                .perform(swipeLeft(), (ViewAction) withId(R.layout.activity_wardrobe))
                .check(matches(withText("Add")));
    }
    @Test
    public void checkDressMeText() {
        ViewInteraction ideasButton = onView(withId(R.id.button_dressMe));
        ideasButton.check(matches(withText("Dress Me")));
    }
}
