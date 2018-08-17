package xyz.appmaker.keralarescue;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import xyz.appmaker.keralarescue.Tools.PreferensHandler;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginErrorMessage() {
        PreferensHandler handler = new PreferensHandler(InstrumentationRegistry.getTargetContext().getApplicationContext());
        handler.setUserToken("");
        onView(withId(R.id.loginBtn)).perform(click());


    }

    @Test
    public void clickSignInButton_validUsernamePassword() {
        //locate and click on the login button

        onView(withId(R.id.username)).perform(clearText(), typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("test12345"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());

        onView(withText("Login successful"))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        PreferensHandler handler = new PreferensHandler(InstrumentationRegistry.getTargetContext().getApplicationContext());
        handler.setUserToken("");

    }

    @Test
    public void clickSignInButton_InvalidUsernamePassword() {
        //locate and click on the login button

        onView(withId(R.id.username)).perform(clearText(), typeText("test123"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("test12345"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());

        onView(withText("Username/Password is incorrect"))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));


    }
}
