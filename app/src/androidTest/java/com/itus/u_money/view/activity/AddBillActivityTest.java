package com.itus.u_money.view.activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.itus.u_money.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AddBillActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testContainChooseTypeIcon() {
        onView(withId(R.id.icoType));
    }

    @Test
    public void testContainChooseTypeText() {
        onView(withId(R.id.txtType));
    }

    @Test
    public void testContainValueText() {
        onView(withText("Giá trị"));
    }

    @Test
    public void testDisplayChooseTypeText() {
        onView(withId(R.id.txtType)).check(matches(withText("Chọn nhóm")));
    }

    @Test
    public void testContainValueHintText() {
        onView(withId(R.id.edtAmount)).check(matches(withHint("0đ")));
    }

    @Test
    public void testClick() {
        onView(withId(R.id.txtType)).perform(click());
    }

    @Test
    public void testContainDateText() {
        onView(withId(R.id.txtTime));
    }
}