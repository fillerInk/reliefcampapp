package xyz.appmaker.keralarescue;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import org.junit.After;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import xyz.appmaker.keralarescue.Tools.Config;
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
    private static MockWebServer server;

    @BeforeClass
    public static void setUp() throws Exception {

        server = new MockWebServer();
        server.start();
        Config.BASE_URL = server.url("/").toString();
    }


    @AfterClass
    public static void tearDown() throws Exception {
        server.shutdown();
    }


    @Test
    public void clickSignInButton_validUsernamePassword() {
        //locate and click on the login button
        server.enqueue(new MockResponse().setResponseCode(200).setBody("{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyLCJ1c2VybmFtZSI6InRlc3QiLCJleHAiOjE1MzYzMTA5MzMsImVtYWlsIjoiIn0.NNjtJj3yh_bdUVNlh2L1ATeetPtK87BGJa6nS_Oamw4\",\"user\":{\"pk\":2,\"username\":\"test\",\"email\":\"\",\"first_name\":\"\",\"last_name\":\"\"}}"));

        onView(withId(R.id.username)).perform(clearText(), typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("test12345"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());

        onView(withText("Login successful"))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

//        PreferensHandler handler = new PreferensHandler(InstrumentationRegistry.getTargetContext().getApplicationContext());
//        handler.setUserToken("");

    }

    @Test
    public void clickSignInButton_InvalidUsernamePassword() {

        onView(withId(R.id.loginBtn)).perform(click());


        onView(withText("Please enter Username\nPlease enter password"))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        //locate and click on the login button
        server.enqueue(new MockResponse().setResponseCode(400).setBody("{\"non_field_errors\":[\"Unable to log in with provided credentials.\"]}"));

        onView(withId(R.id.username)).perform(clearText(), typeText("test123"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText(), typeText("test12345"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());

        onView(withText("Username/Password is incorrect"))
                .inRoot(withDecorView(not(is(rule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));


    }
}
