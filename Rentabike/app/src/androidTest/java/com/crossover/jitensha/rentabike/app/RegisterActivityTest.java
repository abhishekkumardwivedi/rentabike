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
import android.util.Log;

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
public class RegisterActivityTest {

//    @Rule
//    public ActivityTestRule<LoginActivity> mActivityRule
//            = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public IntentsTestRule<RegisterActivity> mActivity
            = new IntentsTestRule<RegisterActivity>(RegisterActivity.class);

    /**
     * Test for register success. On register success, login activity gets launched
     */
    @Test
    public void testRegisterSuccess() {
        // Type text and then press the button.
        onView(withId(R.id.edit_text_email))
                .perform(typeText("crossover@crossover.com"), closeSoftKeyboard());
        onView(withId(R.id.edit_text_password))
                .perform(typeText("crossover"), closeSoftKeyboard());
        onView(withId(R.id.button_register)).perform(click());

        //wait for network delay
        SystemClock.sleep(3000);

        // Check if new activity launched.
        intended(hasComponent(new ComponentName(getTargetContext(), LoginActivity.class)));
    }

    /**
     * Test for register to login button click
     */
    @Test
    public void testRegisterToLogin() {
        onView(withId(R.id.button_login)).perform(click());
        // Check for new Activity
        intended(hasComponent(new ComponentName(getTargetContext(), LoginActivity.class)));
    }
}