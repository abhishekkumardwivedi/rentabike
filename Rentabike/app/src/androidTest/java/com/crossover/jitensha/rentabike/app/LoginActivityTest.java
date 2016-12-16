package com.crossover.jitensha.rentabike.app;


import com.crossover.jitensha.rentabike.LoginActivity;
import com.crossover.jitensha.rentabike.MapsActivity;
import com.crossover.jitensha.rentabike.R;
import com.crossover.jitensha.rentabike.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.ComponentName;
import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

//    @Rule
//    public ActivityTestRule<LoginActivity> mActivityRule
//            = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public IntentsTestRule<LoginActivity> mActivity
            = new IntentsTestRule<LoginActivity>(LoginActivity.class);

    /**
     * Test for login success and transition to map activity
     */
    @Test
    public void testLoginSuccess() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_email))
                .perform(typeText("crossover@crossover.com"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password))
                .perform(typeText("crossover"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        //wait for network delay
        SystemClock.sleep(3000);

        // Check if new activity launched.
        intended(hasComponent(new ComponentName(getTargetContext(), MapsActivity.class)));
    }

    /**
     * Test for login to register
     */
    @Test
    public void testLoginToRegisterActivity() {
        onView(withId(R.id.button_register)).perform(click());
        // Check for new Activity
        intended(hasComponent(new ComponentName(getTargetContext(), RegisterActivity.class)));
    }

    /**
     * Testing for login to register and register to login
     */
    @Test
    public void testLoginToRegisterToLogin() {
        // Go to register page by clicking register button
        onView(withId(R.id.button_register)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), RegisterActivity.class)));
        // from register page press login button and come back to login page
        onView(withId(R.id.button_login)).perform(click());
        // lets check top activity
        intended(hasComponent(new ComponentName(getTargetContext(), RegisterActivity.class)));
    }

    /**
     * test google map click
     */
    @Test
    public void testMarkerClick() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_email))
                .perform(typeText("crossover@crossover.com"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password))
                .perform(typeText("crossover"), closeSoftKeyboard());
        onView(withId(R.id.button_login)).perform(click());

        //wait for network delay
        SystemClock.sleep(3000);

        // Check if new activity launched.
        intended(hasComponent(new ComponentName(getTargetContext(), MapsActivity.class)));

        onView(withContentDescription("Google Map")).perform(click());
        SystemClock.sleep(2000);
        onView(withContentDescription("Google Map")).perform(click());
    }
}