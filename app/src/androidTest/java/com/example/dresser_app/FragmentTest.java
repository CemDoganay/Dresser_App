package com.example.dresser_app;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.dresser_app.fragments.WardrobeFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FragmentTest {


    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(WardrobeFragment.class);

    @Before
    public void swiping() {
        mActivityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }
    @Test
    public void checkWardrobeAddText(){
        ViewInteraction addButton = onView(withId(R.id.button_add));
        addButton.check(matches(withText("Add")));
    }
    @Test
    public void checkDressMeText() {
        ViewInteraction ideasButton = onView(withId(R.id.button_dressMe));
        ideasButton.check(matches(withText("Dress Me")));
    }
}
