package com.example.arbitragecalculator;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArbitrageCalculatorTest {

    @Rule
    public ActivityScenarioRule<ArbitrageCalculator> mActivityScenarioRule =
            new ActivityScenarioRule<>(ArbitrageCalculator.class);

    @Test
    public void arbitrageCalculatorTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.AOutcome1),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker1_Outcome1Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("1.9"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.AOutcome1), withText("1.9"),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker1_Outcome1Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.AOutcome2),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker1_Outcome2Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("1.9"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.AOutcome2), withText("1.9"),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker1_Outcome2Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.BOutcome1),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker2_Outcome1Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("1.4"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.BOutcome1), withText("1.4"),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker2_Outcome1Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                5)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.BOutcome2),
                        childAtPosition(
                                allOf(withId(R.id.BookMarker2_Outcome2Input),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                6)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("2.5"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.CalculateButton), withText("Calculate"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Arbitrage Found"),
                        withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Arbitrage Found")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
