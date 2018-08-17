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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
        onView(withId(R.id.loginBtn)).perform(click());

    }

    @Test
    public void clickSignUpButton_validUsernamePassword() {
        //locate and click on the login button

        onView(withId(R.id.username)).perform(clearText(), typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("test12345"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());


        //check if the sign up screen is displayed by asserting that the first name edittext is displayed
//        onView(withId(R.id.edit_text_first_name)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_sign_up)), isDisplayed())));
    }
}
